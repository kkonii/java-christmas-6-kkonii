package christmas;

public class DiscountCalculator {
    // for d-day event, special event, giving-gift event
    public static int calculateSingleDiscount(Order order, DiscountEvents event) {
        return order.getOrder().entrySet().stream()
                .findFirst()
                .map(entry -> event.getDiscountAmount(order.getDate(), entry.getKey()))
                .orElse(0);
    }

    // for weekday event, weekend event
    public static int calculateDuplicateDiscount(Order order, DiscountEvents event) {
        return order.getOrder().entrySet().stream()
                .mapToInt(entry -> event.getDiscountAmount(order.getDate(), entry.getKey()) * entry.getValue())
                .sum();
    }
}