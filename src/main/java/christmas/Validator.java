package christmas;

public class Validator {
    public static <T> T validateDuplication(T existedMenu) {
        throw new IllegalArgumentException("[ERROR] 같은 메뉴는 중복해서 입력할 수 없습니다.");
    }
}
