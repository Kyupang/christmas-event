package christmas.discountPolicyTest;

import christmas.discount.SpecialDiscountPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialDiscountPolicyTest {
    private SpecialDiscountPolicy discountPolicy;

    @BeforeEach
    public void setUp() {
        discountPolicy = new SpecialDiscountPolicy();
    }

    @DisplayName("특별 할인 정책이 적용 가능한 경우 (일요일)")
    @Test
    public void testIsApplicableSunday() {
        LocalDate applicableDate = LocalDate.of(2023, Month.DECEMBER, 10);
        boolean isApplicable = discountPolicy.isApplicable(applicableDate);

        assertThat(isApplicable).isTrue();
    }

    @DisplayName("특별 할인 정책이 적용 가능한 경우 (25일)")
    @Test
    public void testIsApplicable25th() {
        LocalDate applicableDate = LocalDate.of(2023, Month.DECEMBER, 25);
        boolean isApplicable = discountPolicy.isApplicable(applicableDate);

        assertThat(isApplicable).isTrue();
    }

    @DisplayName("특별 할인 정책이 적용 불가능한 경우")
    @Test
    public void testIsNotApplicable() {
        LocalDate notApplicableDate = LocalDate.of(2023, Month.DECEMBER, 11);
        boolean isApplicable = discountPolicy.isApplicable(notApplicableDate);

        assertThat(isApplicable).isFalse();
    }

    @DisplayName("특별 할인 정책 할인 금액 확인")
    @Test
    public void testDiscountAmount() {
        LocalDate currentDate = LocalDate.of(2023, Month.DECEMBER, 10);
        int discountAmount = discountPolicy.discount(currentDate, List.of());

        assertThat(discountAmount).isEqualTo(1000);
    }
}