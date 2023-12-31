package christmas.controller;

import christmas.domain.Bill;
import christmas.domain.Date;
import christmas.domain.Order;
import christmas.util.Converter;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.function.Supplier;

public class Planner {
    public void run() {
        Date date = requestUserDate();
        Order order = Order.of(date, requestUserMenu());
        Bill bill = order.createBill();

        OutputView.printPreviewBanner(
                date.getDate()
        );

        printBill(order, bill);
    }

    private Date requestUserDate() {
        return requestUserInput(() -> {
            String userDate = InputView.requestDate();
            return Date.from(Converter.parseToInt(userDate));
        });
    }

    private Map<String, Integer> requestUserMenu() {
        return requestUserInput(() -> {
            String userOrder = InputView.requestMenu();
            return Converter.convertToPair(userOrder);
        });
    }

    private <T> T requestUserInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private void printBill(Order order, Bill bill) {
        OutputView.printOrders(order.processTotalOrders());

        OutputView.printTotalPrice(order.calculateTotalPrice());

        OutputView.printPromotion(bill.processPromotion());

        OutputView.printBenefits(bill.processTotalBenefits());

        OutputView.printTotalBenefitPrice(bill.processBenefitPrice());

        OutputView.printExpectedPrice(bill.processExpectedPrice());

        OutputView.printEventBadge(bill.processBadge());
    }

}
