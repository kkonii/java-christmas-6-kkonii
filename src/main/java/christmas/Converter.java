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
                        pair -> Integer.parseInt(pair[1])
                ));

        return resultMap;
    }

    private static String removeWhiteSpace(String userInput) {
        return userInput.replaceAll("\\s+", "");
    }
}
