package christmas.serviceTest;

import christmas.discount.ChristmasDdayDiscountPolicy;
import christmas.discount.SpecialDiscountPolicy;
import christmas.discount.WeekdayDiscountPolicy;
import christmas.discount.WeekendDiscountPolicy;
import christmas.domain.DiscountResult;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.repository.MemoryOrderRepository;
import christmas.repository.OrderRepository;
import christmas.service.DiscountCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountCalculatorServiceTest {

    private DiscountCalculatorServiceImpl discountCalculatorService;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = new MemoryOrderRepository();
        discountCalculatorService = new DiscountCalculatorServiceImpl(Arrays.asList(
                new ChristmasDdayDiscountPolicy(),
                new SpecialDiscountPolicy(),
                new WeekdayDiscountPolicy(),
                new WeekendDiscountPolicy()
        ), orderRepository);
    }

    @Test
    @DisplayName("할인서비스 클래스의 discount 메서드 테스트")
    public void testCalculateDiscounts() {
        List<Order> fakeOrderList = Arrays.asList(
                new Order("바비큐립", 2),
                new Order("제로콜라", 3),
                new Order("아이스크림", 1)
        );

        for (Order order : fakeOrderList) {
            orderRepository.save(order);
        }

        List<DiscountResult> discountResults = discountCalculatorService.calculateDiscounts(5);

        List<DiscountResult> expectedResults = Arrays.asList(
                new DiscountResult("크리스마스 디데이 할인", 1400),
                new DiscountResult("평일 할인", 2023),
                new DiscountResult("증정 이벤트", 25000)
        );

        assertThat(discountResults.size()).isEqualTo(expectedResults.size());
        for (int i = 0; i < discountResults.size(); i++) {
            assertThat(discountResults.get(i).getDiscountAmount()).isEqualTo(
                    expectedResults.get(i).getDiscountAmount());
            assertThat(discountResults.get(i).getDiscountName()).isEqualTo(expectedResults.get(i).getDiscountName());
        }
    }

    @Test
    @DisplayName("할인전 총 계산금액 함수 테스트")
    public void testCalculateTotalOrderAmountBeforeDiscount() {
        List<Order> fakeOrderList = Arrays.asList(
                new Order("티본스테이크", 2),
                new Order("제로콜라", 3),
                new Order("아이스크림", 1)
        );

        for (Order order : fakeOrderList) {
            orderRepository.save(order);
        }

        int totalAmount = discountCalculatorService.calculateTotalOrderAmountBeforeDiscount(fakeOrderList);
        // 예상된 주문 총액 생성
        int expectedTotalAmount = Menu.valueOf("티본스테이크").getPrice() * 2 +
                Menu.valueOf("제로콜라").getPrice() * 3 +
                Menu.valueOf("아이스크림").getPrice() * 1;

        assertThat(totalAmount).isEqualTo(expectedTotalAmount);
    }

    @Test
    @DisplayName("증정상품 체크 함수 테스트")
    public void testApplyChampagneGift() {
        List<Order> fakeOrderList = Arrays.asList(
                new Order("티본스테이크", 3));
        int totalAmountBeforeDiscount = discountCalculatorService.calculateTotalOrderAmountBeforeDiscount(
                fakeOrderList);
        boolean isChampagneGiftApplicable = discountCalculatorService.applyChampagneGift(totalAmountBeforeDiscount);

        assertThat(isChampagneGiftApplicable).isTrue();
    }

    @Test
    @DisplayName("할인 총 금액 함수 테스트")
    public void testCalculateTotalBenefitAmount() {
        List<DiscountResult> fakeDiscountResults = Arrays.asList(
                new DiscountResult("크리스마스 디데이 할인", 1200),
                new DiscountResult("특별 할인", 1000),
                new DiscountResult("평일 할인", 0),
                new DiscountResult("주말 할인", 6069),
                new DiscountResult("증정 이벤트", 25000)
        );

        int totalBenefitAmount = discountCalculatorService.calculateTotalBenefitAmount(fakeDiscountResults);

        int expectedTotalBenefitAmount = 1200 + 1000 + 0 + 6069 + 25000;

        assertThat(totalBenefitAmount).isEqualTo(expectedTotalBenefitAmount);
    }
}
