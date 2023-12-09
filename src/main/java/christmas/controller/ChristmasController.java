package christmas.controller;

import christmas.domain.VisitingDate;
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
    }

    private VisitingDate createDate() {
        return InputHandler.handle(() -> {
            int date = inputView.requestDate();
            return VisitingDate.from(date);
        });
    }
}
