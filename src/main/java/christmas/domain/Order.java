package christmas.domain;

import java.util.List;

public class Order {
    private final List<OrderItem> orderItems;

    private Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static Order from(List<OrderItem> orderItems) {
        return new Order(orderItems);
    }
}
