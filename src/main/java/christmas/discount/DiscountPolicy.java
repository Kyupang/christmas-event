package christmas.discount;

import christmas.domain.Order;
import java.time.LocalDate;
import java.util.List;

public interface DiscountPolicy {
    int discount(LocalDate currentDate, List<Order> orderList);

    boolean isApplicable(LocalDate currentDate);

    String getDiscountName();
}

