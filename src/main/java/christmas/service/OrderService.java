package christmas.service;

import christmas.domain.Order;
import java.util.List;

public interface OrderService {
    void recordOrder(Order order);

    List<Order> getAllOrderRecord();
}
