package christmas;

import java.util.Map;
import java.util.function.Supplier;

public class Planner {
    public void run() {
        Date date = requestUserDate();
        Order order = Order.of(date, requestUserMenu());
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
}
