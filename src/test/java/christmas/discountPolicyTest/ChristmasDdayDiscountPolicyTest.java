package christmas.discountPolicyTest;

import christmas.discount.ChristmasDdayDiscountPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChristmasDdayDiscountPolicyTest {
    private ChristmasDdayDiscountPolicy discountPolicy;

    @BeforeEach
    public void setUp() {
        discountPolicy = new ChristmasDdayDiscountPolicy();
    }
    @DisplayName("크리스마스 디데이 할인 정책 적용 가능한 경우")
    @Test
    public void testIsApplicable() {
        LocalDate applicableDate = LocalDate.of(2023, 12, 10);
        boolean isApplicable = discountPolicy.isApplicable(applicableDate);

        assertThat(isApplicable).isTrue();
    }

    @DisplayName("크리스마스 디데이 할인 정책 적용 불가능한 경우")
    @Test
    public void testIsNotApplicable() {
        LocalDate notApplicableDate = LocalDate.of(2023, 11, 25);
        boolean isApplicable = discountPolicy.isApplicable(notApplicableDate);

        assertThat(isApplicable).isFalse();
    }

    @DisplayName("크리스마스 디데이 할인 정책 할인 금액 확인")
    @Test
    public void testDiscountAmount() {
        LocalDate currentDate = LocalDate.of(2023, 12, 10);
        int discountAmount = discountPolicy.discount(currentDate, List.of());
        int expectedDiscountAmount = 1000 + (int) (currentDate.toEpochDay() - LocalDate.of(2023, 12, 1).toEpochDay()) * 100;

        assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
    }

}