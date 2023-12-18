package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.exception.InvalidOrderException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Order {
    private final VisitingDate visitingDate;
    private final List<OrderItem> orderItems;

    private Order(VisitingDate visitingDate, List<OrderItem> orderItems) {
        validateDuplication(orderItems);
        validateOnlyDrinks(orderItems);
        validateQuantities(orderItems);
        this.visitingDate = visitingDate;
        this.orderItems = orderItems;
    }

    public static Order from(VisitingDate date, List<OrderItem> orderItems) {
        return new Order(date, orderItems);
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

    public VisitingDate getVisitingDate() {
        return visitingDate;
    }

    public List<OrderItem> getOrderItems() {
        return List.copyOf(orderItems);
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
