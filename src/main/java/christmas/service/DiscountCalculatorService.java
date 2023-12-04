package christmas.service;

import christmas.domain.DiscountResult;
import christmas.domain.Order;
import java.util.List;

public interface DiscountCalculatorService {
    List<DiscountResult> calculateDiscounts(int expectedDate);

    int calculateTotalOrderAmountBeforeDiscount(List<Order> orders);

    boolean applyChampagneGift(int totalOrderAmountBeforeDiscount);

    int calculateTotalBenefitAmount(List<DiscountResult> results);
}
