package christmas.domain.promotion;

import christmas.domain.Order;

public class DailyCalculator {
    public static int calculateDiscountPrice(Order order) {
        return 1000 + (order.getVisitingDate().getDay() - 1) * 100;
    }
}
