package christmas.domain.promotion;

import christmas.domain.menu.Menu;

/**
 * 프로모션 진행중인 상품
 */
public enum PromotionItem {
    GIFT("증정 이벤트", Menu.CHAMPAGNE, 1);

    private final String title;
    private final Menu menu;
    private final int quantity;

    PromotionItem(String title, Menu menu, int quantity) {
        this.title = title;
        this.menu = menu;
        this.quantity = quantity;
    }

    public String getPromotionTitle() {
        return title;
    }

    public Menu getGift() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
