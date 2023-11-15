package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidOrderException;
import christmas.global.Const;
import christmas.global.Format;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private Date date;
    private Map<Menu, Integer> order;

    private Order(Date date, Map<Menu, Integer> order) {
        validateQuantities(order);
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
            throw InvalidOrderException.of(ErrorMessage.OVER_LIMIT);
        }
    }

    private int calculateTotalQuantity(Map<Menu, Integer> order) {
        return order.values().stream().mapToInt(Integer::intValue).sum();
    }

    private boolean isOverLimit(int quantities) {
        return quantities > Const.LIMIT_QUANTITY;
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
                .collect(Collectors.joining(Const.ENTER));
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
