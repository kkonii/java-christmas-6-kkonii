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
}
