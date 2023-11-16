package christmas.exception;

public enum ErrorMessage {
    CAPTION("[ERROR] "),
    NON_NUMERIC_VALUE("숫자 이외의 값은 입력할 수 없습니다."),
    NOT_EXIST_VALUE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_REQUIRED_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    OVER_LIMIT("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."),
    ALREADY_EXISTED_VALUE("유효하지 않은 주문입니다. 다시 입력해 주세요.");


    private final String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return CAPTION.message + message;
    }
}
