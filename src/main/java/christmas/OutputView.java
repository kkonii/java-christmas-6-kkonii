package christmas;

public class OutputView {
    public static void printOrders(String order) {
        printWhiteSpace();
        printOrderHeader();
        System.out.println(order);
    }

    private static void printWhiteSpace() {
        System.out.println();
    }

    private static void printOrderHeader() {
        System.out.println("<주문 메뉴>");
    }

    public static void printTotalPrice(Integer price) {
        printWhiteSpace();
        printTotalPriceHeader();
        System.out.println(
                String.format("%,d원", price)
        );
    }

    private static void printTotalPriceHeader() {
        System.out.println("<할인 전 총주문 금액>");
    }
}
