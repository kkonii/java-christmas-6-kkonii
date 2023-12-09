package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.exception.InvalidOrderException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Order {
    private final List<OrderItem> orderItems;

    private Order(List<OrderItem> orderItems) {
        validateDuplication(orderItems);
        validateOnlyDrinks(orderItems);
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

    private void validateDuplication(List<OrderItem> orderItems) {
        Set<Menu> orderedMenu = orderItems.stream()
                .map(OrderItem::getEachMenu).collect(Collectors.toSet());

        if (orderedMenu.size() != orderItems.size()) {
            throw InvalidOrderException.of();
        }
    }

    private void validateOnlyDrinks(List<OrderItem> orderItems) {
        if (isAllDrinks(orderItems)) {
            throw InvalidOrderException.of();
        }
    }

    private boolean isAllDrinks(List<OrderItem> orderItems) {
        return orderItems.stream().allMatch(OrderItem::isDrink);
    }

    /**
     * 총 결제 금액 (할인 전)
     */
    public int calculatePrice() {
        return orderItems.stream()
                .mapToInt(orderItem -> orderItem.getEachQuantity() * orderItem.getEachMenu().getPrice())
                .sum();
    }
}
