package christmas.service;

import christmas.domain.Order;
import christmas.repository.OrderRepository;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void recordOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrderRecord() {
        return orderRepository.getOrderedList();
    }
}
