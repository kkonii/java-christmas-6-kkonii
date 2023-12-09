package christmas.domain;

import christmas.domain.menu.Menu;

public class OrderItem {
    private final Menu menu;
    private final int quantity;

    private OrderItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderItem of(String name, int quantity) {
        return new OrderItem(Menu.findMatchMenu(name), quantity);
    }
}
