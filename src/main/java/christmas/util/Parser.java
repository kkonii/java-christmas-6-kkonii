package christmas.util;

import christmas.exception.InvalidDateException;

public class Parser {
    public static int parseToInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw InvalidDateException.of();
        }
    }
}
