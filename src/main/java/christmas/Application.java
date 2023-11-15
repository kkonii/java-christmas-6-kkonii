package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        Planner eventPlaner = new Planner();
        eventPlaner.run();
        Console.readLine();
    }
}
