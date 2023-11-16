package christmas.exception;

public class InvalidDateException extends ChristmasException {
    InvalidDateException(String message) {
        super(message);
    }

    public static InvalidDateException of(ErrorMessage error) {
        return new InvalidDateException(error.getMessage());
    }
}
