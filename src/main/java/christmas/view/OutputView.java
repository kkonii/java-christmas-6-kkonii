package christmas.view;

import christmas.domain.Order;
import christmas.domain.Reservation;
import christmas.domain.promotion.Badge;

public class OutputView {
    private final static String MENU_QUANTITY_FORMAT = "%s %d개";
    private static final String PRICE_FORMAT = ",%d원";

    public void printOrderAndQuantities(Order order) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        order.getOrderItems().forEach(
                orderItem -> System.out.print(String.format(
                        MENU_QUANTITY_FORMAT,
                        orderItem.getEachMenu().name(),
                        orderItem.getEachQuantity())
                ));
    }

    public void printBeforeDiscountPrice(Order order) {
        System.out.println();
        System.out.println(String.format(PRICE_FORMAT, order.calculatePrice()));
    }

    public void printGivenGift(Reservation reservation) {
        System.out.println();
        System.out.println("<증정 메뉴>");
        if (reservation.hasNoPromotion()) {
            System.out.println("없음");
        }
        if (!reservation.hasNoPromotion()) {
            reservation.getPromotionBox()
                    .forEach(promotionItem -> System.out.println(
                            String.format(MENU_QUANTITY_FORMAT, promotionItem.name(), promotionItem.getQuantity())
                    ));
        }
    }

    public void printBenefitHistory(Reservation reservation) {
        System.out.println();
        System.out.println("<혜택 내역>");
    }

    public void printBenefitPrices(Reservation reservation) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.println(String.format(PRICE_FORMAT, reservation.calculateBenefitPrices()));
    }

    public void printAfterDiscountPrice(Reservation reservation) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format(PRICE_FORMAT, reservation.calculateDiscountPrices()));
    }

    public void printGivenBadge(Reservation reservation) {
        System.out.println();
        System.out.println("12월 이벤트 배지");
        System.out.println(Badge.findMatchBadge(reservation.calculateBenefitPrices()).name());
    }
}
