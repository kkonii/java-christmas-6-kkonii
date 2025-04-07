package christmas.domain;

import christmas.util.Parser;
import java.time.DayOfWeek;

public class VisitingDateChecker {
    public static boolean isDaily(VisitingDate date) {
        return date.getDay() >= 1 && date.getDay() <= 25;
    }

    public static boolean isWeekday(VisitingDate date) {
        DayOfWeek dayOfWeek = Parser.parseToDate(date);

        return dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.MONDAY
                || dayOfWeek == DayOfWeek.TUESDAY || dayOfWeek == DayOfWeek.WEDNESDAY
                || dayOfWeek == DayOfWeek.THURSDAY;
    }

    public static boolean isWeekend(VisitingDate date) {
        return !isWeekday(date);
    }

    public static boolean isSpecialDay(VisitingDate date) {
        DayOfWeek dayOfWeek = Parser.parseToDate(date);
        return dayOfWeek == DayOfWeek.SUNDAY || date.getDay() == 25;
    }
}
