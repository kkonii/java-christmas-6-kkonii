package christmas;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidDateException;

public class Date {
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
            throw InvalidDateException.of(ErrorMessage.NOT_REQUIRED_RANGE);
        }
    }

    private boolean isNotRequiredRange(int date) {
        return date < 1 || date > 31;
    }

    public Integer getDate() {
        return date;
    }
}
