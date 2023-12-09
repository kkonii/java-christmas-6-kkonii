package christmas.domain;

import christmas.exception.InvalidDateException;

public class VisitingDate {
    private final int date;

    private VisitingDate(int date) {
        validateRange(date);
        this.date = date;
    }

    public static VisitingDate from(int date) {
        return new VisitingDate(date);
    }

    private void validateRange(int date) {
        if (date < 1 || date > 31) {
            throw InvalidDateException.of();
        }
    }

    public int getDay() {
        return date;
    }
}
