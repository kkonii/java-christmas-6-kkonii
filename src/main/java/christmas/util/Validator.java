package christmas.util;

import christmas.exception.InvalidOrderException;

public class Validator {
    public static <T> T validateDuplication() {
        throw InvalidOrderException.of();
    }
}
