package christmas.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.repository.MemoryOrderRepository;
import christmas.repository.OrderRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class OrderRepositoryTest {
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new MemoryOrderRepository();
    }

    @DisplayName("메모리 저장소에 저장하고 불러오는 테스트")
    @Test
    void saveAndGetOrderList() {
        Order order1 = new Order("티본스테이크", 1);
        Order order2 = new Order("해산물파스타", 2);

        orderRepository.save(order1);
        orderRepository.save(order2);
        List<Order> orderList = orderRepository.getOrderedList();

        assertThat(orderList).contains(order1,order2);
    }
}
