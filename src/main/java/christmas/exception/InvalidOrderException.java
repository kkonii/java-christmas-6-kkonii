package christmas.exception;

public class InvalidOrderException extends IllegalArgumentException {
    private static final String INVALID_ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private InvalidOrderException(String message) {
        super(message);
    }

    public static InvalidOrderException of() {
        return new InvalidOrderException(INVALID_ORDER_ERROR_MESSAGE);
    }
}
