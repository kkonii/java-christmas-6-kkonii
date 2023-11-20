package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.PlannerController;

public class Application {
    public static void main(String[] args) {
        PlannerController eventPlaner = new PlannerController();
        eventPlaner.run();
        Console.close();
    }
}
