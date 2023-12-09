package christmas.domain;

import christmas.exception.InvalidOrderException;
import java.util.List;

public class Order {
    private final List<OrderItem> orderItems;

    private Order(List<OrderItem> orderItems) {
        validateQuantities(orderItems);
        this.orderItems = orderItems;
    }

    public static Order from(List<OrderItem> orderItems) {
        return new Order(orderItems);
    }

    private void validateQuantities(List<OrderItem> orderItems) {
        if (calculateQuantities(orderItems) > 20) {
            throw InvalidOrderException.of();
        }
    }

    private int calculateQuantities(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(OrderItem::getEachQuantity)
                .sum();
    }
}
