package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @Test
    @DisplayName("::존재하지 않는 메뉴가 포함되어 있을 경우 예외가 발생합니다")
    public void Should_ThrowException_When_MenuDoesNotExist() {
        Map<String, Integer> userOrder = new HashMap<>();

        userOrder.put("망고아이스크림", 2);    //doesn't exist menu
        userOrder.put("티본스테이크", 3);
        userOrder.put("크리스마스파스타", 1);

        assertThatThrownBy(() -> Order.from(userOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
