package christmas.exception;

public class InvalidDateException extends IllegalArgumentException {
    private static final String INVALID_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private InvalidDateException(String message) {
        super(message);
    }

    public static InvalidDateException of() {
        return new InvalidDateException(INVALID_DATE_ERROR_MESSAGE);
    }
}
