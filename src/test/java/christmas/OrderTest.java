package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private Map<String, Integer> menu;

    @BeforeEach
    public void setUp() {
        menu = Map.of("티본스테이크", 24);
    }

    @Test
    @DisplayName("::주문 수량이 최대 수량을 넘어갈 경우 예외가 발생합니다")
    public void validateQuantities_ShouldThrowException_WhenOverLimit() {
        assertThatThrownBy(() -> Order.of(Date.from(2), menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }
    
    @Test
    @DisplayName("::존재하지 않는 메뉴가 포함되어 있을 경우 예외가 발생합니다")
    public void Should_ThrowException_When_MenuDoesNotExist() {
        Map<String, Integer> userOrder = new HashMap<>();

        userOrder.put("망고아이스크림", 2);    //doesn't exist menu
        userOrder.put("티본스테이크", 3);
        userOrder.put("크리스마스파스타", 1);

        assertThatThrownBy(() -> Order.of(Date.from(2), userOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
