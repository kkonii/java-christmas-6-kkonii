package christmas.domain.promotion;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Events {
    D_DAY_DISCOUNT("크리스마스 디데이 할인", (date, menu) -> 1000 + 100 * (date - 1),
            EventCondition.D_DAY::findMatchDay,
            DiscountCalculator::calculateSingleDiscount),
    WEEKDAY_DISCOUNT("평일 할인",
            (date, menu) -> {
                if (menu.getType().equals(Type.Desserts)) {
                    return 2_023;
                }
                return 0;
            }, EventCondition.WEEKDAY::findMatchDay, DiscountCalculator::calculateDuplicateDiscount),
    WEEKEND_DISCOUNT("주말 할인",
            (date, menu) -> {
                if (menu.getType().equals(Type.MainCourse)) {
                    return 2_023;
                }
                return 0;
            }, EventCondition.WEEKEND::findMatchDay, DiscountCalculator::calculateDuplicateDiscount),
    SPECIAL_DISCOUNT("특별 할인", (date, menu) -> 1_000, EventCondition.SPECIAL_DAY::findMatchDay,
            DiscountCalculator::calculateSingleDiscount),
    GIVING_GIFT("증정 이벤트", (date, menu) -> 25_000, EventCondition.GIFT_DAY::findMatchDay,
            DiscountCalculator::calculateSingleDiscount),
    NONE_DISCOUNT("없음", (date, menu) -> 0, EventCondition.NONE::findMatchDay,
            DiscountCalculator::calculateSingleDiscount);

    private final String title;
    private final BiFunction<Integer, Menu, Integer> discountAmount;
    private final BiPredicate<Integer, Integer> condition;
    private final BiFunction<Order, Events, Integer> discountCalculator;

    Events(String title, BiFunction<Integer, Menu, Integer> discountAmount,
           BiPredicate<Integer, Integer> condition,
           BiFunction<Order, Events, Integer> discountCalculator) {
        this.title = title;
        this.discountAmount = discountAmount;
        this.condition = condition;
        this.discountCalculator = discountCalculator;
    }

    public static Map<Events, Long> findMatchEvents(int date, int totalPrice) {
        return Arrays.stream(Events.values())
                .filter(event -> event.condition.test(date, totalPrice))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public String getTitle() {
        return title;
    }

    public Integer getDiscountAmount(Integer date, Menu menu) {
        return discountAmount.apply(date, menu);
    }

    public int countDiscountEvent(Order order) {
        return this.discountCalculator.apply(order, this);
    }
}
