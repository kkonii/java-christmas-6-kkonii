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
    private final InputView inputView;
    private final OutputView outputView;
    private final OrderService orderService;

    public PlannerController(InputView inputView, OutputView outputView, OrderService orderService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = orderService;
    }

    public void run() {
        final Date date = requestUserDate();
        final Order order = requestUserMenu(date);
        Bill bill = order.createBill();

        outputView.printPreviewBanner(
                date.getDate()
        );

        printBill(order, bill);
    }

    private Date requestUserDate() {
        return InputHandler.handle(() -> {
            String userDate = inputView.requestDate();
            return Date.from(Converter.parseToInt(userDate));
        });
    }

    private Order requestUserMenu(Date date) {
        return InputHandler.handle(() -> {
            String userOrder = inputView.requestMenu();
            List<OrderItem> orderItems = orderService.splitMenus(userOrder);
            return Order.of(date, orderItems);
        });
    }

    private void printBill(Order order, Bill bill) {
        outputView.printOrders(order.processTotalOrders());

        outputView.printTotalPrice(order.calculateTotalPrice());

        outputView.printPromotion(bill.processPromotion());

        outputView.printBenefits(bill.processTotalBenefits());

        outputView.printTotalBenefitPrice(bill.processBenefitPrice());

        outputView.printExpectedPrice(bill.processExpectedPrice());

        outputView.printEventBadge(bill.processBadge());
    }

}
