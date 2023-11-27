package christmas.service;

import christmas.domain.OrderItem;
import christmas.global.Const;
import christmas.util.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private static final String SEPARATOR = ",";
    private static final String PAIR_SEPARATOR = "-";

    public List<OrderItem> splitMenus(String userMenus) {
        return Arrays.stream(userMenus.split(SEPARATOR))
                .map(Converter::parseNoWhiteSpace)
                .map(menu -> menu.split(PAIR_SEPARATOR))
                .map(split -> OrderItem.of(split[Const.MENU_INDEX], Integer.parseInt(split[Const.QUANTITY_INDEX])))
                .collect(Collectors.toList());
    }
}
