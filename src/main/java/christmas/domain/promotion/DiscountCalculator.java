package christmas.domain.promotion;

import christmas.domain.Order;
import christmas.global.Const;

public class DiscountCalculator {
    // for d-day event, special event, giving-gift event
    public static int calculateSingleDiscount(Order order, Events event) {
        return order.getOrder().stream()
                .findFirst()
                .map(orderItem -> event.getDiscountAmount(order.getDate(), orderItem.getMenu()))
                .orElse(Const.EMPTY_VALUE);
    }

    // for weekday event, weekend event
    public static int calculateDuplicateDiscount(Order order, Events event) {
        return order.getOrder().stream()
                .mapToInt(orderItem -> event.getDiscountAmount(order.getDate(), orderItem.getMenu()))
                .sum();
    }
}