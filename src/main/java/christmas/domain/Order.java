package christmas.domain;

import christmas.domain.promotion.Events;
import christmas.exception.InvalidOrderException;
import christmas.global.Const;
import christmas.global.Format;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private static final int LIMIT_QUANTITY = 20;
    private final Date date;
    private final List<OrderItem> orderItems;

    /* 온리음료, 같은 메뉴 주문, 20개 초과 검증*/
    private Order(Date date, List<OrderItem> orderItems) {
        validateQuantities(orderItems);
        validateMenuType(orderItems);
        this.date = date;
        this.orderItems = orderItems;
    }

    public static Order of(Date date, List<OrderItem> orderItems) {
        return new Order(date, orderItems);
    }

    private void validateQuantities(List<OrderItem> orderItems) {
        int totalQuantities = calculateTotalQuantity(orderItems);

        if (isOverLimit(totalQuantities)) {
            throw InvalidOrderException.of();
        }
    }

    private int calculateTotalQuantity(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(OrderItem::getCount)
                .sum();
    }

    private boolean isOverLimit(int quantities) {
        return quantities > LIMIT_QUANTITY;
    }

    private void validateMenuType(List<OrderItem> orderItems) {
        if (isOnlyDrinkType(orderItems)) {
            throw InvalidOrderException.of();
        }
    }

    private boolean isOnlyDrinkType(List<OrderItem> orderItems) {
        return orderItems.stream()
                .allMatch(orderItem -> orderItem.getMenu().getType() == Type.DRINKS);
    }

    public Integer getDate() {
        return date.getDate();
    }

    public List<OrderItem> getOrder() {
        return List.copyOf(this.orderItems);
    }

    // is service Logic
    public String processTotalOrders() {
        return orderItems.stream()
                .map(orderItem -> String.format(Format.ORDER_COUNT, orderItem.getMenu().getName(),
                        orderItem.getCount()))
                .collect(Collectors.joining(Const.LINE_SEPARATOR));
    }

    public Bill createBill() {
        return new Bill(
                calculateTotalPrice(), processAppliedEvents()
        );
    }

    public int calculateTotalPrice() {
        return orderItems.stream()
                .mapToInt(orderItem -> orderItem.getMenu().getPrice() * orderItem.getCount())
                .sum();
    }

    private Map<Events, Integer> processAppliedEvents() {
        return countAppliedEvents().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getKey().countDiscountEvent(this)
                ));
    }

    // cast to Integer
    private Map<Events, Long> countAppliedEvents() {
        return Events.findMatchEvents(
                date.getDate(), calculateTotalPrice()
        );
    }
}
