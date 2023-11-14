package christmas;

import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private Date date;
    private Map<Menu, Integer> order;

    private Order(Date date, Map<Menu, Integer> order) {
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

    public Integer calculateTotalPrice() {
        return this.order.entrySet().stream()
                .mapToInt(entry ->
                        entry.getKey().getPrice() * entry.getValue()
                )
                .sum();
    }
}
