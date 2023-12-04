package christmas.validation;

public class VisitDayInputValidator {
    private static final String NON_DIGIT_EXCEPTION_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String OUT_OF_RANGE_EXCEPTION_MESSAGE = "입력값이 1부터 31 사이의 숫자가 아닙니다.";


    public static void validate(String expectedVisitDate) {
        checkInputDigit(expectedVisitDate);
        checkInputRange(expectedVisitDate);
    }

    private static void checkInputDigit(String expectedVisitDate) {
        for (int i = 0; i < expectedVisitDate.length(); i++) {
            if (!Character.isDigit(expectedVisitDate.charAt(i))) {
                throw new IllegalArgumentException(NON_DIGIT_EXCEPTION_MESSAGE);
            }
        }
    }

    private static void checkInputRange(String expectedVisitDate) {
        int number = Integer.parseInt(expectedVisitDate);
        if (number < 1 || number > 31) {
            throw new IllegalArgumentException(OUT_OF_RANGE_EXCEPTION_MESSAGE);
        }
    }
}
