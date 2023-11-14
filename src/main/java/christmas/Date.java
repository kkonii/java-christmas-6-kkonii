package christmas;

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
            throw new IllegalArgumentException();
        }
    }

    private boolean isNotRequiredRange(int date) {
        return date < 1 || date > 31;
    }
}
