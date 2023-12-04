package christmas.discount;

import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private static final String POLICY_NAME = "특별 할인";
    private static final int DISCOUNT_AMOUNT = 1000;
    private static final int CHRISTMAS = 25;

    @Override
    public int discount(LocalDate currentDate, List<Order> orderList) {
        return DISCOUNT_AMOUNT;
    }

    @Override
    public boolean isApplicable(LocalDate currentDate) {
        return currentDate.getDayOfWeek() == DayOfWeek.SUNDAY || currentDate.getDayOfMonth() == CHRISTMAS;
    }

    @Override
    public String getDiscountName() {
        return POLICY_NAME;
    }
}
