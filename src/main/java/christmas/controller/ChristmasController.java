package christmas.controller;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.util.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;

    private ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static ChristmasController setUp() {
        return new ChristmasController(new InputView(), new OutputView());
    }

    public void run() {
        VisitingDate date = createDate();
        Order order = createOrder();
    }

    private VisitingDate createDate() {
        return InputHandler.handle(() -> {
            int date = inputView.requestDate();
            return VisitingDate.from(date);
        });
    }

    private Order createOrder() {
        return InputHandler.handle(() -> {
            String order = inputView.requestOrder();
            return Order.from(Parser.createOrderItems(order));
        });
    }
}
