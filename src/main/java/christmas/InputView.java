package christmas;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String requestDate() {
        welcome();
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)");

        return requestInput();
    }

    private static void welcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    private static String requestInput() {
        return Console.readLine();
    }
}
