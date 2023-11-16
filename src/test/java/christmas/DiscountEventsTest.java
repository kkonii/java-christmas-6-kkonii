package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountEventsTest {
    @Test
    @DisplayName("::주어진 날짜 조건에 해당하는 이벤트를 정상적으로 반환합니다")
    public void When_InputDate_Expect_ReturnApplicableEventEnum() {
        int testDate = 10;
        int testTotalPrice = 5000;

        Map<DiscountEvents, Long> result = DiscountEvents.findMatchEvents(testDate, testTotalPrice);

        assertTrue(result.containsKey(DiscountEvents.SPECIAL_DISCOUNT));
        assertEquals(1, result.get(DiscountEvents.SPECIAL_DISCOUNT));
    }
}
