package christmas.domain;

import christmas.exception.InvalidDateException;

public class Date {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 31;
    private final Integer date;

    private Date(int date) {
        validateRange(date);
        this.date = date;
    }

    public static Date from(int date) {
        return new Date(date);
    }

    private void validateRange(int date) {
        if (isNotRequiredRange(date)) {
            throw InvalidDateException.of();
        }
    }

    private boolean isNotRequiredRange(int date) {
        return date < MIN_RANGE || date > MAX_RANGE;
    }

    public Integer getDate() {
        return date;
    }
}
