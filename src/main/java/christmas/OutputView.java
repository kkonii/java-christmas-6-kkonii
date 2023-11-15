package christmas;

public class OutputView {
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
}
