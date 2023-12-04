package christmas.repository;

import christmas.domain.Order;
import java.util.List;

public interface OrderRepository {
    void save(Order order);

    List<Order> getOrderedList();
}
