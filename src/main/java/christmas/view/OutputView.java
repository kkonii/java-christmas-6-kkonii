package christmas.view;

import christmas.domain.Badge;

public class OutputView {
    public void printOrders(String order) {
        printWhiteSpace();
        System.out.println("<주문 메뉴>");
        System.out.println(order);
    }

    private void printWhiteSpace() {
        System.out.println();
    }

    public void printTotalPrice(Integer price) {
        printWhiteSpace();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", price));
    }

    public void printPreviewBanner(int date) {
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printBenefits(String benefits) {
        printWhiteSpace();
        System.out.println("<혜택 내역>");
        System.out.println(benefits);
    }

    public void printPromotion(String promotion) {
        printWhiteSpace();
        System.out.println("<증정 메뉴>");
        System.out.println(promotion);
    }


    public void printTotalBenefitPrice(int price) {
        printWhiteSpace();
        System.out.println("<총혜택 금액>");
        System.out.println(
                String.format("-%,d원", price)
        );
    }

    public void printExpectedPrice(int price) {
        printWhiteSpace();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(
                String.format("%,d원", price)
        );
    }

    public void printEventBadge(Badge badge) {
        printWhiteSpace();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getTitle());
    }
}
