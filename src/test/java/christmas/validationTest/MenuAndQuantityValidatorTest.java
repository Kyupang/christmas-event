package christmas.validationTest;

import christmas.validation.MenuAndQuantityValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MenuAndQuantityValidatorTest {

    private static final String INVALID_FORMAT_EXCEPTION_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String MENU_BOARD_EXCEPTION_MESSAGE = "존재하지 않는 메뉴명이 포함되어 있습니다.";
    private static final String MENU_QUANTITY_EXCEPTION_MESSAGE = "메뉴 개수는 0보다 커야 합니다.";
    private static final String DUPLICATE_MENU_EXCEPTION_MESSAGE = "중복된 메뉴명이 존재합니다.";

    @DisplayName("유효한 메뉴와 수량 입력 테스트")
    @Test
    public void testValidMenuAndQuantity() {
        String validMenuAndQuantity = "제로콜라-1";
        String validMenuAndQuantity2 = "제로콜라-1,아이스크림-2";

        assertThatCode(() -> MenuAndQuantityValidator.validate(validMenuAndQuantity)).doesNotThrowAnyException();
        assertThatCode(() -> MenuAndQuantityValidator.validate(validMenuAndQuantity2)).doesNotThrowAnyException();
    }

    @DisplayName("유효하지 않은 형식의 주문에 대한 예외 처리")
    @Test
    public void testInvalidFormat() {
        String invalidFormat = "제로콜라1,아이스크림-2";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> MenuAndQuantityValidator.validate(invalidFormat))
                .withMessage(INVALID_FORMAT_EXCEPTION_MESSAGE);
    }

    @DisplayName("존재하지 않는 메뉴명이 포함된 주문에 대한 예외 처리")
    @Test
    public void testMenuNotOnMenuBoard() {
        String menuNotOnMenuBoard = "제로콜라-1,아이스크림-2,아사히-1";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> MenuAndQuantityValidator.validate(menuNotOnMenuBoard))
                .withMessage(MENU_BOARD_EXCEPTION_MESSAGE);
    }

    @DisplayName("메뉴 개수가 0인 주문에 대한 예외 처리")
    @Test
    public void testZeroMenuQuantity() {
        String zeroMenuQuantity = "제로콜라-0,아이스크림-2";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> MenuAndQuantityValidator.validate(zeroMenuQuantity))
                .withMessage(MENU_QUANTITY_EXCEPTION_MESSAGE);
    }

    @DisplayName("중복된 메뉴명이 포함된 주문에 대한 예외 처리")
    @Test
    public void testDuplicateMenu() {
        String duplicateMenu = "제로콜라-1,아이스크림-2,제로콜라-1";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> MenuAndQuantityValidator.validate(duplicateMenu))
                .withMessage(DUPLICATE_MENU_EXCEPTION_MESSAGE);
    }
}