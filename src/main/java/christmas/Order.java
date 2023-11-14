package christmas;

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
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
        }
    }

    private int calculateTotalQuantity(Map<Menu, Integer> order) {
        return order.values().stream().mapToInt(Integer::intValue).sum();
    }

    private boolean isOverLimit(int quantities) {
        return quantities > 20;
    }

    public Integer calculateTotalPrice() {
        return this.order.entrySet().stream()
                .mapToInt(entry ->
                        entry.getKey().getPrice() * entry.getValue()
                )
                .sum();
    }
}
