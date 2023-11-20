package christmas.domain;

import christmas.domain.promotion.EventCondition;
import christmas.domain.promotion.Events;
import christmas.global.Const;
import java.util.Map;
import java.util.stream.Collectors;

public record Bill(int totalPrice, Map<Events, Integer> orders) {
    public String processPromotion() {
        if (EventCondition.GIFT_DAY.findMatchDay(Const.EMPTY_VALUE, totalPrice)) {
            return Menu.PROMOTION.getName();
        }
        return Events.NONE_DISCOUNT.getTitle();
    }

    public String processTotalBenefits() {
        if (hasNoneEvent()) {
            return Events.NONE_DISCOUNT.getTitle();
        }

        return orders.entrySet().stream()
                .filter(order -> order.getValue() != Const.EMPTY_VALUE)
                .map(event -> String.format(
                        "%s: -%,d원%n",
                        event.getKey().getTitle(),
                        event.getValue()
                ))
                .collect(Collectors.joining());
    }

    private boolean hasNoneEvent() {
        return orders.containsKey(Events.NONE_DISCOUNT);
    }

    public int processBenefitPrice() {
        return orders.entrySet().stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int processExpectedPrice() {
        int priceAfterDiscount = totalPrice - processBenefitPrice();

        if (EventCondition.GIFT_DAY.findMatchDay(Const.EMPTY_VALUE, totalPrice)) {
            priceAfterDiscount += Menu.PROMOTION.getPrice();
        }

        return priceAfterDiscount;
    }

    public String processBadge() {
        return Badge.findMatchBadge(
                processBenefitPrice()
        );
    }
}
