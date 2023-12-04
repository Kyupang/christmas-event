package christmas.validationTest;

import christmas.validation.VisitDayInputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class VisitDayInputValidatorTest {

    private static final String NON_DIGIT_EXCEPTION_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String OUT_OF_RANGE_EXCEPTION_MESSAGE = "입력값이 1부터 31 사이의 숫자가 아닙니다.";

    @DisplayName("유효한 날짜 입력 테스트")
    @Test
    public void testValidVisitDate() {
        String validVisitDate = "15";

        assertThatCode(() -> VisitDayInputValidator.validate(validVisitDate)).doesNotThrowAnyException();
    }

    @DisplayName("숫자가 아닌 입력에 대한 예외 처리")
    @Test
    public void testNonDigitVisitDate() {
        String nonDigitVisitDate = "abc";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> VisitDayInputValidator.validate(nonDigitVisitDate))
                .withMessage(NON_DIGIT_EXCEPTION_MESSAGE);
    }

    @DisplayName("범위를 벗어난 날짜에 대한 예외 처리 (최소값)")
    @Test
    public void testOutOfRangeMinVisitDate() {
        String outOfRangeMinVisitDate = "0";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> VisitDayInputValidator.validate(outOfRangeMinVisitDate))
                .withMessage(OUT_OF_RANGE_EXCEPTION_MESSAGE);
    }

    @DisplayName("범위를 벗어난 날짜에 대한 예외 처리 (최대값)")
    @Test
    public void testOutOfRangeMaxVisitDate() {
        String outOfRangeMaxVisitDate = "32";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> VisitDayInputValidator.validate(outOfRangeMaxVisitDate))
                .withMessage(OUT_OF_RANGE_EXCEPTION_MESSAGE);
    }
}