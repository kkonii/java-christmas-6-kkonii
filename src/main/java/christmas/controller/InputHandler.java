package christmas.controller;

import christmas.view.OutputView;
import java.util.function.Supplier;

public class InputHandler {
    public static <T> T handle(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
