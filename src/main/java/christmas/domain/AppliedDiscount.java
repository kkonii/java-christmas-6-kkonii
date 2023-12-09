package christmas.domain;

import christmas.domain.promotion.Discount;
import java.util.EnumMap;

/**
 * 적용된 이벤트와 그 수량을 저장
 */
public class AppliedDiscount {
    private final EnumMap<Discount, Integer> discounts;

    private AppliedDiscount(EnumMap<Discount, Integer> discounts) {
        distinctMultipleApplied(discounts);
        this.discounts = discounts;
    }

    public static AppliedDiscount from(EnumMap<Discount, Integer> discounts) {
        return new AppliedDiscount(discounts);
    }

    /**
     * 디데이 할인과 특별 할인의 수량이 있는 경우 중복수량을 없앤다
     */
    private void distinctMultipleApplied(EnumMap<Discount, Integer> discounts) {
        if (discounts.containsKey(Discount.DAILY)) {
            discounts.put(Discount.DAILY, 1);
        }
        if (discounts.containsKey(Discount.SPECIAL)) {
            discounts.put(Discount.SPECIAL, 1);
        }
    }
}
