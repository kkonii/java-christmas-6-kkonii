package christmas.domain.promotion;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Badge {
    NONE("없음", price -> price < 5_000),
    STAR("별", price -> price >= 5_000 && price < 10_000),
    TREE("트리", price -> price >= 10_000 && price < 20_000),
    SANTA("산타", price -> price >= 20_000);

    private final String title;
    private final Predicate<Integer> priceCondition;

    Badge(String title, Predicate<Integer> priceCondition) {
        this.title = title;
        this.priceCondition = priceCondition;
    }

    public static Badge findMatchBadge(int price) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.priceCondition.test(price))
                .findFirst()
                .get();
    }
}
