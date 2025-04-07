package christmas.domain.menu;

import christmas.exception.InvalidOrderException;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),
    CESAR_SALAD("시저샐러드", 8_000, Category.APPETIZER),

    STEAK("티본스테이크", 55_000, Category.MAIN_DISH),
    BBQ("바비큐립", 54_000, Category.MAIN_DISH),
    SEAFOOD_PASTA("해산물파스타", 35_000, Category.MAIN_DISH),
    XMAS_PASTA("크리스마스파스타", 25_000, Category.MAIN_DISH),

    CHOCO_CAKE("초코케이크", 15_000, Category.DESSERTS),
    ICE_CREAM("아이스크림", 5_000, Category.DESSERTS),

    ZERO_COKE("제로콜라", 3_000, Category.DRINKS),
    RED_WINE("레드와인", 60_000, Category.DRINKS),
    CHAMPAGNE("샴페인", 25_000, Category.DRINKS);

    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Menu findMatchMenu(String orderedMenu) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(orderedMenu))
                .findFirst()
                .orElseThrow(InvalidOrderException::of);
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
