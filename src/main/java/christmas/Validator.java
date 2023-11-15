package christmas;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidOrderException;

public class Validator {
    public static <T> T validateDuplication(T existedMenu) {
        throw InvalidOrderException.of(ErrorMessage.ALREADY_EXISTED_VALUE);
    }
}
