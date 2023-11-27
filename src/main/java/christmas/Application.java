package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.PlannerController;
import christmas.service.OrderService;

public class Application {
    public static void main(String[] args) {
        PlannerController eventPlaner = new PlannerController(new OrderService());
        eventPlaner.run();
        Console.close();
    }
}
