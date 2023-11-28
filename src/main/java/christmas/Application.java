package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.PlannerController;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PlannerController eventPlaner = new PlannerController(new InputView(), new OutputView(), new OrderService());
        eventPlaner.run();
        Console.close();
    }
}
