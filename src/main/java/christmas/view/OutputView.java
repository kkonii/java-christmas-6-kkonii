package christmas.view;

public class OutputView {

    public static void printGeneric(String message) {
        System.out.println(message);
    }

    public static void printOrders(String order) {
        printWhiteSpace();
        printOrderBanner();
        System.out.println(order);
    }

    private static void printWhiteSpace() {
        System.out.println();
    }

    private static void printOrderBanner() {
        System.out.println("<주문 메뉴>");
    }

    public static void printTotalPrice(Integer price) {
        printWhiteSpace();
        printTotalPriceBanner();
        System.out.println(
                String.format("%,d원", price)
        );
    }

    public static void printPreviewBanner(int date) {
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    private static void printTotalPriceBanner() {
        System.out.println("<할인 전 총주문 금액>");
    }

    public static void printBenefits(String benefits) {
        printWhiteSpace();
        printBenefitBanner();
        System.out.println(benefits);
    }

    private static void printBenefitBanner() {
        System.out.println("<혜택 내역>");
    }

    public static void printPromotion(String promotion) {
        printWhiteSpace();
        printPromotionBanner();
        System.out.println(promotion);
    }

    private static void printPromotionBanner() {
        System.out.println("<증정 메뉴>");
    }

    public static void printTotalBenefitPrice(int price) {
        printWhiteSpace();
        printBenefitPriceBanner();
        System.out.println(
                String.format("-%,d원", price)
        );
    }

    private static void printBenefitPriceBanner() {
        System.out.println("<총혜택 금액>");
    }

    public static void printExpectedPrice(int price) {
        printWhiteSpace();
        printExpectedPriceBanner();
        System.out.println(
                String.format("%,d원", price)
        );
    }

    private static void printExpectedPriceBanner() {
        System.out.println("<할인 후 예상 결제 금액>");
    }

    public static void printEventBadge(String badgeName) {
        printWhiteSpace();
        printBadgeBanner();
        System.out.println(badgeName);
    }

    private static void printBadgeBanner() {
        System.out.println("<12월 이벤트 배지>");
    }

    public static void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
