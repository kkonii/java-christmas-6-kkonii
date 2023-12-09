package christmas.domain;

import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.exception.InvalidOrderException;

public class OrderItem {
    private final Menu menu;
    private final int quantity;

    private OrderItem(Menu menu, int quantity) {
        validateQuantity(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderItem of(String name, int quantity) {
        return new OrderItem(Menu.findMatchMenu(name), quantity);
    }

    private void validateQuantity(int quantity) {
        if (quantity > 20) {
            throw InvalidOrderException.of();
        }
    }

    public int getEachQuantity() {
        return quantity;
    }

    public Menu getEachMenu() {
        return menu;
    }

    public boolean isDrink() {
        return menu.getCategory().equals(Category.DRINKS);
    }
}
