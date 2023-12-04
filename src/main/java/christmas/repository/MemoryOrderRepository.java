package christmas.repository;

import christmas.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class MemoryOrderRepository implements OrderRepository {
    private static List<Order> store = new ArrayList<>();

    @Override
    public void save(Order order) {
        store.add(order);
    }

    @Override
    public List<Order> getOrderedList() {
        return store;
    }
}
