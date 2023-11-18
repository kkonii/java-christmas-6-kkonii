package christmas.exception;

public class InvalidOrderException extends IllegalArgumentException {
    private InvalidOrderException(ErrorMessage message) {
        super(message.getMessage());
    }

    public static InvalidOrderException of() {
        return new InvalidOrderException(ErrorMessage.INVALID_ORDER);
    }
}
