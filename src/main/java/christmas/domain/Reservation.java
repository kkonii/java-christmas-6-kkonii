package christmas.domain;

import christmas.domain.promotion.PromotionItem;
import java.util.ArrayList;
import java.util.List;

/**
 * 예약이 완료된 내역 저장
 */
public class Reservation {
    private final Order order;
    private final AppliedDiscount appliedDiscount;
    private List<PromotionItem> promotionBox;

    private Reservation(Order order, AppliedDiscount appliedDiscount) {
        this.order = order;
        this.appliedDiscount = appliedDiscount;
        this.promotionBox = new ArrayList<>();
    }

    public static Reservation createFrom(Order order, AppliedDiscount appliedDiscount) {
        return new Reservation(order, appliedDiscount);
    }

    public VisitingDate getVisitingDate() {
        return order.getVisitingDate();
    }

    /**
     * 프로모션이 적용된 여부
     */
    public boolean hasNoPromotion() {
        return promotionBox.isEmpty();
    }

    public void addPromotion(PromotionItem promotionItem) {
        promotionBox.add(promotionItem);
    }

    /**
     * 총 할인후 결제금 계산
     */
    public int calculateDiscountPrices() {
        return order.calculatePrice() - appliedDiscount.processDiscount(this.order);
    }
}
