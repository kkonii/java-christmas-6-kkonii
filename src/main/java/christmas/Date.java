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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isNotRequiredRange(int date) {
        return date < 1 || date > 31;
    }

    public Integer getDate() {
        return date;
    }
}
