package christmas.exception;

public class InvalidOrderException extends ChristmasException {
    private InvalidOrderException(String message) {
        super(message);
    }

    public static InvalidOrderException of(ErrorMessage error) {
        return new InvalidOrderException(error.getMessage());
    }
}
