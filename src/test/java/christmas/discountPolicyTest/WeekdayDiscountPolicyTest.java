package christmas.discountPolicyTest;

import christmas.discount.WeekdayDiscountPolicy;
import christmas.domain.Order;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeekdayDiscountPolicyTest {
    private WeekdayDiscountPolicy discountPolicy;

    @BeforeEach
    public void setUp() {
        discountPolicy = new WeekdayDiscountPolicy();
    }

    @DisplayName("평일 할인 정책이 적용 가능한 경우 (월요일)")
    @Test
    public void testIsApplicableWeekday() {
        LocalDate applicableDate = LocalDate.of(2023, Month.DECEMBER, 11);
        boolean isApplicable = discountPolicy.isApplicable(applicableDate);

        assertThat(isApplicable).isTrue();
    }

    @DisplayName("평일 할인 정책이 적용 불가능한 경우 (금요일)")
    @Test
    public void testIsNotApplicableFriday() {
        LocalDate notApplicableDate = LocalDate.of(2023, Month.DECEMBER, 15);
        boolean isApplicable = discountPolicy.isApplicable(notApplicableDate);

        assertThat(isApplicable).isFalse();
    }

    @DisplayName("평일 할인 정책이 적용 불가능한 경우 (토요일)")
    @Test
    public void testIsNotApplicableSaturday() {
        LocalDate notApplicableDate = LocalDate.of(2023, Month.DECEMBER, 16);
        boolean isApplicable = discountPolicy.isApplicable(notApplicableDate);

        assertThat(isApplicable).isFalse();
    }

    @DisplayName("평일 할인 정책 할인 금액 확인")
    @Test
    public void testDiscountAmount() {
        List<Order> orderList = Arrays.asList(
                new Order("초코케이크", 3),
                new Order("아이스크림", 2),
                new Order("제로콜라", 1)
        );

        int discountAmount = discountPolicy.discount(LocalDate.of(2023, Month.DECEMBER, 11), orderList);

        int expectedDiscountAmount = 2023 * (3 + 2);

        assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
    }

    @DisplayName("평일 할인 정책 할인 금액 - 적용 대상이 없는 경우")
    @Test
    public void testDiscountAmountNoApplicableItem() {
        List<Order> orderList = Arrays.asList(
                new Order("제로콜라", 1),
                new Order("레드와인", 2)
        );

        int discountAmount = discountPolicy.discount(LocalDate.of(2023, Month.DECEMBER, 11), orderList);

        assertThat(discountAmount).isEqualTo(0);
    }
}