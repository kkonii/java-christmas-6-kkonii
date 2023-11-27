package christmas.domain;

public class OrderItem {
    private final Menu menu;
    private final int count;

    private OrderItem(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public static OrderItem of(String name, int count) {
        Menu menu = Menu.findMatchMenu(name);
        return new OrderItem(menu, count);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }
}
