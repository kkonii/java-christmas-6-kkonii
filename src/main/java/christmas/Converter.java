package christmas;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidDateException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {
    public static Map<String, Integer> convertToPair(String userMenus) {
        Map<String, Integer> resultMap = Arrays.stream(userMenus.split(","))
                .map(Converter::removeWhiteSpace)
                .map(menu -> menu.split("-"))
                .collect(Collectors.toMap(
                        pair -> pair[0],
                        pair -> parseToInt(pair[1]),
                        (existedInput, reInput) -> Validator.validateDuplication(existedInput)
                ));
        return resultMap;
    }

    private static String removeWhiteSpace(String userInput) {
        return userInput.replaceAll("\\s+", "");
    }

    public static Integer parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw InvalidDateException.of(ErrorMessage.NON_NUMERIC_VALUE);
        }
    }

    public static String convertToOrder(Map<String, Integer> menuPair) {
        return menuPair.entrySet()
                .stream()
                .map(entry -> String.format("%s %d개", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
