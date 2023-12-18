package christmas.util;

import christmas.domain.OrderItem;
import christmas.domain.VisitingDate;
import christmas.exception.InvalidDateException;
import christmas.exception.InvalidOrderException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static int parseToInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw InvalidDateException.of();
        }
    }

    public static List<OrderItem> createOrderItems(String order) {
        return Arrays.stream(order.split(","))
                .map(orderItem -> validateOrderItemQuantity(orderItem.split("-")))
                .map(split -> OrderItem.of(split[0], parseToInt(split[1])))
                .toList();
    }

    public static String[] validateOrderItemQuantity(String[] orderAndQuantity) {
        if (orderAndQuantity.length != 2) {
            throw InvalidOrderException.of();
        }
        return orderAndQuantity;
    }

    public static DayOfWeek parseToDate(VisitingDate date) {
        int year = 2023;
        int month = 12;

        LocalDate localDate = LocalDate.of(year, month, date.getDay());
        return localDate.getDayOfWeek();
    }
}
