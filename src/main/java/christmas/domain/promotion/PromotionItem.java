package christmas.domain.promotion;

import christmas.domain.menu.Menu;

/**
 * 프로모션 진행중인 상품
 */
public enum PromotionItem {
    GIFT(Menu.CHAMPAGNE, 1);

    private final Menu menu;
    private final int quantity;

    PromotionItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
