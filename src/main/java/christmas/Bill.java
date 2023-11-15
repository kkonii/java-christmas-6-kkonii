package christmas;

import java.util.Map;
import java.util.stream.Collectors;

public record Bill(int totalPrice, Map<DiscountEvents, Integer> orders) {
    public String processTotalBenefits() {
        if (hasNoneEvent()) {
            return DiscountEvents.NONE_DISCOUNT.getTitle();
        }

        return orders.entrySet().stream()
                .filter(order -> order.getValue() != 0)
                .map(event -> String.format(
                        "%s: -%,d원%n",
                        event.getKey().getTitle(),
                        event.getValue()
                ))
                .collect(Collectors.joining());
    }

    private boolean hasNoneEvent() {
        return orders.containsKey(DiscountEvents.NONE_DISCOUNT);
    }

    public int processBenefitPrice() {
        return orders.entrySet().stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int processExpectedPrice() {
        int priceAfterDiscount = totalPrice - processBenefitPrice();

        if (EventCondition.GIFT_DAY.findMatchDay(0, totalPrice)) {
            priceAfterDiscount += Menu.CHAMPAGNE.getPrice();
        }

        return priceAfterDiscount;
    }
}
