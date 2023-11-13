package christmas;

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

    private static Integer parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
