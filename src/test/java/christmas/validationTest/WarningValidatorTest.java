package christmas.validationTest;

import christmas.validation.WarningValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class WarningValidatorTest {

    private static final String MENU_ONLY_DRINK_EXCEPTION_MESSAGE = "음료로만 주문할 수 없습니다.";
    private static final String OVER_TWENTY_MENU_EXCEPTION_MESSAGE = "총 주문 개수를 20개 이하로 주문해주세요";

    @DisplayName("음료만 주문된 경우 예외 처리")
    @Test
    public void testDrinkOnlyOrder() {
        String drinkOnlyOrder = "제로콜라-2,레드와인-3";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> WarningValidator.validateWarning(drinkOnlyOrder))
                .withMessage(MENU_ONLY_DRINK_EXCEPTION_MESSAGE);
    }

    @DisplayName("총 주문 개수가 20개를 초과하는 경우 예외 처리")
    @Test
    public void testOverTwentyMenu() {
        String overTwentyMenu = "제로콜라-5,레드와인-3,양송이수프-4,티본스테이크-10";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> WarningValidator.validateWarning(overTwentyMenu))
                .withMessage(OVER_TWENTY_MENU_EXCEPTION_MESSAGE);
    }

    @DisplayName("이벤트 적용 가능한 경우")
    @Test
    public void testEventApplicability() {
        String eventApplicableOrder = "제로콜라-5,레드와인-3,양송이수프-2,티본스테이크-1";

        boolean isApplicable = WarningValidator.EventApplicability(eventApplicableOrder);

        assertThat(isApplicable).isTrue();
    }

    @DisplayName("이벤트 적용 불가능한 경우")
    @Test
    public void testEventNonApplicability() {
        String eventNonApplicableOrder = "제로콜라-1,타파스-1";

        boolean isApplicable = WarningValidator.EventApplicability(eventNonApplicableOrder);

        assertThat(isApplicable).isFalse();
    }
}