package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Reservation;
import christmas.domain.VisitingDate;
import christmas.service.ChristmasService;
import christmas.util.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    private ChristmasController(InputView inputView, OutputView outputView, ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
    }

    public static ChristmasController setUp() {
        return new ChristmasController(new InputView(), new OutputView(), new ChristmasService());
    }

    public void run() {
        VisitingDate date = createDate();
        Order order = createOrder(date);
        Reservation reservation = christmasService.createReservation(order);
        createBills(order, reservation);
    }

    private VisitingDate createDate() {
        return InputHandler.handle(() -> {
            int date = inputView.requestDate();
            return VisitingDate.from(date);
        });
    }

    private Order createOrder(VisitingDate date) {
        return InputHandler.handle(() -> {
            String order = inputView.requestOrder();
            return Order.from(date, Parser.createOrderItems(order));
        });
    }

    private void createBills(Order order, Reservation reservation) {
        outputView.printOrderAndQuantities(order);
        outputView.printBeforeDiscountPrice(order);
        outputView.printGivenGift(reservation);
        outputView.printBenefitHistory(reservation, order);
        outputView.printBenefitPrices(reservation);
        outputView.printAfterDiscountPrice(reservation);
        outputView.printGivenBadge(reservation);
    }
}
