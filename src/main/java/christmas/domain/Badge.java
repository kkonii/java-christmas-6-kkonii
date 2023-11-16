package christmas.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Badge {
    NONE_BADGE("없음", amount -> amount < 5_000),
    LOW_LEVEL_BADGE("별", amount -> 5_000 <= amount && amount < 10_000),
    MIDDLE_LEVEL_BADGE("트리", amount -> 10_000 <= amount && amount < 20_000),
    HIGH_LEVEL_BADGE("산타", amount -> amount >= 20_000);

    private final String title;
    private final Predicate<Integer> condition;

    Badge(String title, Predicate<Integer> condition) {
        this.title = title;
        this.condition = condition;
    }

    public static String findMatchBadge(Integer amount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.condition.test(amount))
                .findFirst()
                .map(Badge::getTitle)
                .orElse(NONE_BADGE.getTitle());
    }

    private String getTitle() {
        return title;
    }
}
