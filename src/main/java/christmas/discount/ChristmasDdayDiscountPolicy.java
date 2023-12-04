package christmas.discount;

import christmas.domain.Order;
import java.time.LocalDate;
import java.util.List;

public class ChristmasDdayDiscountPolicy implements DiscountPolicy {
    private static final String POLICY_NAME = "크리스마스 디데이 할인";
    private static final int INITIAL_AMOUNT = 1000;
    private static final int DAILY_INCREMENT = 100;
    private static final LocalDate EVENT_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(2023, 12, 25);

    @Override
    public int discount(LocalDate currentDate, List<Order> orderList) {
        int daysFromStart = (int) (currentDate.toEpochDay() - EVENT_START_DATE.toEpochDay());
        return INITIAL_AMOUNT + daysFromStart * DAILY_INCREMENT;
    }

    @Override
    public boolean isApplicable(LocalDate currentDate) {
        return !currentDate.isBefore(EVENT_START_DATE) && !currentDate.isAfter(EVENT_END_DATE);
    }

    @Override
    public String getDiscountName() {
        return POLICY_NAME;
    }
}
