package christmas.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.repository.MemoryOrderRepository;
import christmas.repository.OrderRepository;
import christmas.service.OrderService;
import christmas.service.OrderServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderRepository = new MemoryOrderRepository();
        orderService = new OrderServiceImpl(orderRepository);
    }

    @DisplayName("주문 서비스 기록 테스트")
    @Test
    public void testRecordOrder() {
        Order order1 = new Order("티본스테이크", 1);
        orderService.recordOrder(order1);
        List<Order> orderServiceOrders = orderService.getAllOrderRecord();

        List<Order> orders = orderRepository.getOrderedList();

        assertThat(orders).isEqualTo(orderServiceOrders);
    }
}
