package christmas.exception;

public class InvalidDateException extends IllegalArgumentException {
    InvalidDateException(ErrorMessage message) {
        super(message.getMessage());
    }

    public static InvalidDateException of() {
        return new InvalidDateException(ErrorMessage.INVALID_DATE);
    }
}
