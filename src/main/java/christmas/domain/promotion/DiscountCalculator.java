package christmas.domain.promotion;

import christmas.domain.Order;
import christmas.global.Const;

public class DiscountCalculator {
    // for d-day event, special event, giving-gift event
    public static int calculateSingleDiscount(Order order, Events event) {
        return order.getOrder().entrySet().stream()
                .findFirst()
                .map(entry -> event.getDiscountAmount(order.getDate(), entry.getKey()))
                .orElse(Const.EMPTY_VALUE);
    }

    // for weekday event, weekend event
    public static int calculateDuplicateDiscount(Order order, Events event) {
        return order.getOrder().entrySet().stream()
                .mapToInt(entry -> event.getDiscountAmount(order.getDate(), entry.getKey()) * entry.getValue())
                .sum();
    }
}