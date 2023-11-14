package christmas;

import java.util.function.BiFunction;

public enum EventCondition {
    D_DAY((date, totalPrice) -> date >= 1 && date <= 25),
    WEEKDAY((date, totalPrice) -> (date % 7) >= 2 && (date % 7) <= 6),
    WEEKEND((date, totalPrice) -> (date % 7) >= 0 && (date % 7) <= 1),
    SPECIAL_DAY((date, totalPrice) -> (date % 7) == 3 || date == 25),
    GIFT_DAY((date, totalPrice) -> totalPrice >= 120_000),
    NONE((date, totalPrice) -> false);

    private final BiFunction<Integer, Integer, Boolean> condition;

    EventCondition(BiFunction<Integer, Integer, Boolean> condition) {
        this.condition = condition;
    }

    public Boolean findMatchDay(Integer date, Integer totalPrice) {
        return condition.apply(date, totalPrice);
    }
}
