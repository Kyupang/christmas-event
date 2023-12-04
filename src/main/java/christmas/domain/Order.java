package christmas.domain;

public class Order {
    private String menuName;
    private int quantity;

    public Order(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}
