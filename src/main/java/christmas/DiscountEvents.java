package christmas;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DiscountEvents {
    D_DAY_DISCOUNT("크리스마스 디데이 할인", EventCondition.D_DAY::findMatchDay),
    WEEKDAY_DISCOUNT("평일 할인", EventCondition.WEEKDAY::findMatchDay),
    WEEKEND_DISCOUNT("주말 할인", EventCondition.WEEKEND::findMatchDay),

    SPECIAL_DISCOUNT("특별 할인", EventCondition.SPECIAL_DAY::findMatchDay),
    GIVING_GIFT("증정 이벤트", EventCondition.GIFT_DAY::findMatchDay),
    NONE_DISCOUNT("없음", EventCondition.NONE::findMatchDay);

    private final String title;
    private final BiFunction<Integer, Integer, ?> condition;

    DiscountEvents(String title, BiFunction<Integer, Integer, ?> condition) {
        this.title = title;
        this.condition = condition;
    }

    public static Map<DiscountEvents, Long> findMatchEvents(int date, int totalPrice) {
        return Arrays.stream(DiscountEvents.values())
                .filter(event -> (boolean) event.condition.apply(date, totalPrice))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
