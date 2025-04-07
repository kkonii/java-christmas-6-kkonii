package christmas.util;

import christmas.exception.InvalidOrderException;

public class InputValidator {
    public static String validateOrder(String order) {
        validateEmptyOrder(order);
        validateOrderFormat(order);
        return order;
    }

    /**
     * 입력 메뉴가 공백인 경우
     */
    private static void validateEmptyOrder(String order) {
        if (isEmpty(order)) {
            throw InvalidOrderException.of();
        }
    }

    private static boolean isEmpty(String input) {
        return input.strip().isBlank();
    }

    /**
     * 구분자 관련 검증
     */
    public static void validateOrderFormat(String order) {
        if (order.startsWith(",") || order.endsWith(",")) {
            throw InvalidOrderException.of();
        }
    }
}
