package christmas;

import christmas.discount.ChristmasDdayDiscountPolicy;
import christmas.discount.DiscountPolicy;
import christmas.discount.SpecialDiscountPolicy;
import christmas.discount.WeekdayDiscountPolicy;
import christmas.discount.WeekendDiscountPolicy;
import christmas.repository.MemoryOrderRepository;
import christmas.repository.OrderRepository;
import christmas.service.DiscountCalculatorService;
import christmas.service.DiscountCalculatorServiceImpl;
import christmas.service.OrderService;
import christmas.service.OrderServiceImpl;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        OrderRepository orderRepository = new MemoryOrderRepository();
        OrderService orderService = new OrderServiceImpl(orderRepository);
        List<DiscountPolicy> discountPolicies = new ArrayList<>();
        discountPolicies.add(new ChristmasDdayDiscountPolicy());
        discountPolicies.add(new WeekdayDiscountPolicy());
        discountPolicies.add(new WeekendDiscountPolicy());
        discountPolicies.add(new SpecialDiscountPolicy());

        DiscountCalculatorService discountCalculatorService = new DiscountCalculatorServiceImpl(discountPolicies,
                orderRepository);
        DecemberEventPlanner decemberEventPlanner = new DecemberEventPlanner(orderService, discountCalculatorService);
        decemberEventPlanner.run();
    }
}
