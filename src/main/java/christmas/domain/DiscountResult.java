package christmas.domain;

public class DiscountResult {
    private String discountName;
    private int discountAmount;

    public DiscountResult(String discountName, int discountAmount) {
        this.discountName = discountName;
        this.discountAmount = discountAmount;
    }

    public String getDiscountName() {
        return discountName;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
