package christmas.service;

import christmas.domain.AppliedDiscount;
import christmas.domain.Order;
import christmas.domain.Reservation;
import christmas.domain.promotion.Discount;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class ChristmasService {
    public Reservation createReservation(Order order) {
        AppliedDiscount appliedDiscounts = AppliedDiscount.from(new EnumMap<>(Discount.class));

        if (order.calculatePrice() >= 1_000) {
            appliedDiscounts = createFrom(order);
        }
        return Reservation.createFrom(order, appliedDiscounts);
    }

    private AppliedDiscount createFrom(Order order) {
        EnumMap<Discount, Integer> discount = new EnumMap<>(Discount.class);
        List<Discount> appliedDiscounts = findApplicableDiscounts(order);

        appliedDiscounts.forEach(
                discountItem -> discount.merge(discountItem, 1, Integer::sum)
        );

        return AppliedDiscount.from(discount);
    }

    private List<Discount> findApplicableDiscounts(Order order) {
        return order.getOrderItems().stream()
                .flatMap(orderItem -> Arrays.stream(Discount.values())
                        .filter(discount -> discount.isApplicable(order.getVisitingDate(), orderItem)))
                .toList();
    }
}
