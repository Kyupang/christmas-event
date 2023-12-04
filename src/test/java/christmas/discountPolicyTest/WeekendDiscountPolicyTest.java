package christmas.discountPolicyTest;

import christmas.discount.SpecialDiscountPolicy;
import christmas.discount.WeekendDiscountPolicy;
import christmas.domain.Order;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeekendDiscountPolicyTest {
    private WeekendDiscountPolicy discountPolicy;

    @BeforeEach
    public void setUp() {
        discountPolicy = new WeekendDiscountPolicy();
    }

    @DisplayName("주말 할인 정책이 적용 가능한 경우 (금요일)")
    @Test
    public void testIsApplicableFriday() {
        LocalDate applicableDate = LocalDate.of(2023, Month.DECEMBER, 15);
        boolean isApplicable = discountPolicy.isApplicable(applicableDate);

        assertThat(isApplicable).isTrue();
    }

    @DisplayName("주말 할인 정책이 적용 가능한 경우 (토요일)")
    @Test
    public void testIsApplicableSaturday() {
        LocalDate applicableDate = LocalDate.of(2023, Month.DECEMBER, 16);
        boolean isApplicable = discountPolicy.isApplicable(applicableDate);

        assertThat(isApplicable).isTrue();
    }

    @DisplayName("주말 할인 정책이 적용 불가능한 경우 (일요일)")
    @Test
    public void testIsNotApplicableSunday() {
        LocalDate notApplicableDate = LocalDate.of(2023, Month.DECEMBER, 17);
        boolean isApplicable = discountPolicy.isApplicable(notApplicableDate);

        assertThat(isApplicable).isFalse();
    }

    @DisplayName("주말 할인 정책 할인 금액 확인")
    @Test
    public void testDiscountAmount() {
        List<Order> orderList = Arrays.asList(
                new Order("티본스테이크", 3),
                new Order("바비큐립", 2),
                new Order("아이스크림", 1)
        );

        int discountAmount = discountPolicy.discount(LocalDate.of(2023, Month.DECEMBER, 15), orderList);

        int expectedDiscountAmount = 2023 * (3 + 2);

        assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
    }

    @DisplayName("주말 할인 정책 할인 금액 - 적용 대상이 없는 경우")
    @Test
    public void testDiscountAmountNoApplicableItem() {
        List<Order> orderList = Arrays.asList(
                new Order("초코케이크", 1),
                new Order("레드와인", 2)
        );

        int discountAmount = discountPolicy.discount(LocalDate.of(2023, Month.DECEMBER, 15), orderList);

        assertThat(discountAmount).isEqualTo(0);
    }
}