package christmas;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),

    STEAK("티본스테이크", 55_000),
    BARBECUE("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    SPECIAL_PASTA("크리스마스파스타", 25_000),

    CHOCOLATE_CAKES("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),

    ZERO_COKE("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);


    private final String name;
    private final int price;

    Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static Menu findMatchMenu(String inputName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(inputName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }
}


