package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    @DisplayName("::이벤트 기간 내의 숫자를 입력하면 정상적으로 객체를 생성합니다")
    public void When_DateIsInRange_Expect_ExceptionDoesNotThrown() {
        //given
        int validDate = 15;

        //when
        Date date = Date.from(validDate);

        //then
        assertNotNull(date);
        assertEquals(validDate, date.getDate().intValue());
    }

    @Test
    @DisplayName("::이벤트 기간이 아닌 숫자를 입력하면 예외가 발생합니다")
    public void testInvalidDateCreation() {
        //given
        int invalidDate = 32;

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Date.from(invalidDate));
        assertEquals(exception.getMessage(), "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
