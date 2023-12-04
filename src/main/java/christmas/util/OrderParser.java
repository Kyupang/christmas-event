package christmas.util;

import christmas.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderParser {
    public static List<Order> parseOrders(String menuAndQuantityInput) {
        List<Order> orders = new ArrayList<>();

        for (String orderToken : menuAndQuantityInput.split(",")) {
            String[] parts = orderToken.trim().split("-");
            if (parts.length == 2) {
                orders.add(new Order(parts[0].trim(), Integer.parseInt(parts[1].trim())));
            }
        }

        return orders;
    }
}
