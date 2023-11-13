package christmas;

import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private Map<Menu, Integer> order;

    private Order(Map<Menu, Integer> order) {
        this.order = order;
    }

    public static Order from(Map<String, Integer> userOrder) {
        Map<Menu, Integer> order = userOrder.entrySet().stream()
                .collect(Collectors.toMap(
                        userMenu -> Menu.findMatchMenu(userMenu.getKey()),
                        Map.Entry::getValue
                ));

        return new Order(order);
    }
}
