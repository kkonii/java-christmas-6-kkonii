package christmas.util;

import christmas.exception.InvalidDateException;
import christmas.global.Const;

public class Converter {
    public static String parseNoWhiteSpace(String userInput) {
        return userInput.replaceAll(Const.SPACE_BAR, Const.REMOVE);
    }

    public static Integer parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw InvalidDateException.of();
        }
    }
}
