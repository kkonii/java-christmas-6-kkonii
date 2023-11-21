package christmas.util;

import christmas.exception.InvalidDateException;
import christmas.global.Const;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {
    private static final String SEPARATOR = ",";
    private static final String PAIR_SEPARATOR = "-";

    public static Map<String, Integer> convertToPair(String userMenus) {
        Map<String, Integer> resultMap = Arrays.stream(userMenus.split(SEPARATOR))
                .map(Converter::removeWhiteSpace)
                .map(menu -> menu.split(PAIR_SEPARATOR))
                .collect(Collectors.toMap(
                        pair -> pair[Const.START_VALUE],
                        pair -> parseToInt(pair[Const.INCREMENT]),
                        (existedInput, reInput) -> Validator.validateDuplication()
                ));
        return resultMap;
    }

    private static String removeWhiteSpace(String userInput) {
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
