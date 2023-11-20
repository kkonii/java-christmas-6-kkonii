package christmas.domain;

import christmas.exception.InvalidOrderException;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, Type.APPETIZER),
    TAPAS("타파스", 5_500, Type.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, Type.APPETIZER),

    STEAK("티본스테이크", 55_000, Type.MAIN_COURSE),
    BARBECUE("바비큐립", 54_000, Type.MAIN_COURSE),
    SEAFOOD_PASTA("해산물파스타", 35_000, Type.MAIN_COURSE),
    SPECIAL_PASTA("크리스마스파스타", 25_000, Type.MAIN_COURSE),

    CHOCOLATE_CAKES("초코케이크", 15_000, Type.DESSERTS),
    ICE_CREAM("아이스크림", 5_00, Type.DESSERTS),

    ZERO_COKE("제로콜라", 3_000, Type.DRINKS),
    RED_WINE("레드와인", 60_000, Type.DRINKS),
    CHAMPAGNE("샴페인", 25_000, Type.DRINKS),
    PROMOTION("샴페인 1개", 25_000, Type.PROMOTION);

    private final String name;
    private final int price;
    private final Type type;

    Menu(String name, int price, Type type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public static Menu findMatchMenu(String inputName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(inputName))
                .findFirst()
                .orElseThrow(() -> InvalidOrderException.of());
    }
}


