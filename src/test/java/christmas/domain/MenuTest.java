package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {
    private final String inputMenu = "시저샐러드";

    @Test
    @DisplayName("::정상적으로 메뉴 객체를 반환합니다")
    public void Should_ReturnMenu_When_InputExistMenu() {
        assertNotNull(Menu.findMatchMenu(inputMenu));
    }

    @Test
    @DisplayName("::입력된 메뉴와 반환된 객체의 이름이 일치합니다")
    public void When_InputMenu_Expect_NameMatches() {
        assertEquals(inputMenu, Menu.findMatchMenu(inputMenu).getName());
    }
}
