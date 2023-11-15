package christmas;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidOrderException;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, Type.Appetizer),
    TAPAS("타파스", 5_500, Type.Appetizer),
    CAESAR_SALAD("시저샐러드", 8_000, Type.Appetizer),

    STEAK("티본스테이크", 55_000, Type.MainCourse),
    BARBECUE("바비큐립", 54_000, Type.MainCourse),
    SEAFOOD_PASTA("해산물파스타", 35_000, Type.MainCourse),
    SPECIAL_PASTA("크리스마스파스타", 25_000, Type.MainCourse),

    CHOCOLATE_CAKES("초코케이크", 15_000, Type.Desserts),
    ICE_CREAM("아이스크림", 5_00, Type.Desserts),

    ZERO_COKE("제로콜라", 3_000, Type.Drinks),
    RED_WINE("레드와인", 60_000, Type.Drinks),
    CHAMPAGNE("샴페인", 25_000, Type.Drinks),
    PROMOTION("샴페인 1개", 25_000, Type.Promotion);

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
                .orElseThrow(() -> InvalidOrderException.of(ErrorMessage.NOT_EXIST_VALUE));
    }
}


