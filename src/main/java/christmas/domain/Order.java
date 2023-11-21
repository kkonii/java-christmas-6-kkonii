package christmas.domain;

import christmas.domain.promotion.Events;
import christmas.exception.InvalidOrderException;
import christmas.global.Const;
import christmas.global.Format;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private static final int LIMIT_QUANTITY = 20;
    private final Date date;
    private final Map<Menu, Integer> order;

    private Order(Date date, Map<Menu, Integer> order) {
        validateQuantities(order);
        validateMenuType(order);
        this.date = date;
        this.order = order;
    }

    public static Order of(Date date, Map<String, Integer> userOrder) {
        Map<Menu, Integer> order = userOrder.entrySet().stream()
                .collect(Collectors.toMap(
                        userMenu -> Menu.findMatchMenu(userMenu.getKey()),
                        Map.Entry::getValue
                ));

        return new Order(date, order);
    }

    private void validateQuantities(Map<Menu, Integer> order) {
        int totalQuantities = calculateTotalQuantity(order);

        if (isOverLimit(totalQuantities)) {
            throw InvalidOrderException.of();
        }
    }

    private int calculateTotalQuantity(Map<Menu, Integer> order) {
        return order.values().stream().mapToInt(Integer::intValue).sum();
    }

    private boolean isOverLimit(int quantities) {
        return quantities > LIMIT_QUANTITY;
    }

    private void validateMenuType(Map<Menu, Integer> order) {
        if (isOnlyDrinkType(order)) {
            throw InvalidOrderException.of();
        }
    }

    private boolean isOnlyDrinkType(Map<Menu, Integer> order) {
        return order.isEmpty() || order.keySet().stream()
                .allMatch(menu -> menu.getType() == Type.DRINKS);
    }

    public Integer getDate() {
        return date.getDate();
    }

    public Map<Menu, Integer> getOrder() {
        return Collections.unmodifiableMap(this.order);
    }

    public String processTotalOrders() {
        return order.entrySet().stream()
                .map(entry -> String.format(Format.ORDER_COUNT, entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.joining(Const.LINE_SEPARATOR));
    }

    public Bill createBill() {
        return new Bill(
                calculateTotalPrice(), processAppliedEvents()
        );
    }

    public int calculateTotalPrice() {
        return this.order.entrySet().stream()
                .mapToInt(entry ->
                        entry.getKey().getPrice() * entry.getValue()
                )
                .sum();
    }

    private Map<Events, Integer> processAppliedEvents() {
        return countAppliedEvents().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getKey().countDiscountEvent(this)
                ));
    }

    private Map<Events, Long> countAppliedEvents() {
        return Events.findMatchEvents(
                date.getDate(), calculateTotalPrice()
        );
    }
}
