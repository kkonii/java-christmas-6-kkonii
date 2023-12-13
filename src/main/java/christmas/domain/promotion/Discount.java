package christmas.domain.promotion;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.VisitingDate;
import christmas.domain.VisitingDateChecker;
import java.util.function.Function;
import java.util.function.Predicate;

public enum Discount {
    DAILY("크리스마스 디데이 할인", DailyCalculator::calculateDiscountPrice, VisitingDateChecker::isDaily, orderItem -> true),
    WEEKDAYS("평일 할인", WeekdayCalculator::calculateDiscountPrice, VisitingDateChecker::isWeekday,
            OrderItem::isWeekdayMenu),
    WEEKENDS("주말 할인", WeekendCalculator::calculateDiscountPrice, VisitingDateChecker::isWeekend,
            OrderItem::isWeekendMenu),
    SPECIAL("특별 할인", SpecialDayCalculator::calculateDiscountPrice, VisitingDateChecker::isSpecialDay,
            orderItem -> true);

    private final String title;
    private final Function<Order, Integer> discount;
    private final Predicate<VisitingDate> dayCondition;
    private final Predicate<OrderItem> menuCondition;

    Discount(String title, Function<Order, Integer> discount, Predicate<VisitingDate> dayCondition,
             Predicate<OrderItem> menuCondition) {
        this.title = title;
        this.discount = discount;
        this.dayCondition = dayCondition;
        this.menuCondition = menuCondition;
    }

    public boolean isApplicable(VisitingDate date, OrderItem orderItem) {
        return dayCondition.test(date) && menuCondition.test(orderItem);
    }

    public int getDiscountPrice(Order order) {
        return discount.apply(order);
    }

    public String getTitle() {
        return title;
    }
}
