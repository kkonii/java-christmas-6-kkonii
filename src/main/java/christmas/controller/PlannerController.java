package christmas.controller;

import christmas.domain.Bill;
import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.service.OrderService;
import christmas.util.Converter;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class PlannerController {
    private final OrderService orderService;

    public PlannerController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void run() {
        Date date = requestUserDate();
        Order order = requestUserMenu(date);
        Bill bill = order.createBill();

        OutputView.printPreviewBanner(
                date.getDate()
        );

        printBill(order, bill);
    }

    private Date requestUserDate() {
        return InputHandler.handle(() -> {
            String userDate = InputView.requestDate();
            return Date.from(Converter.parseToInt(userDate));
        });
    }

    private Order requestUserMenu(Date date) {
        return InputHandler.handle(() -> {
            String userOrder = InputView.requestMenu();
            List<OrderItem> orderItems = orderService.splitMenus(userOrder);
            return Order.of(date, orderItems);
        });
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
