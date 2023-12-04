package christmas.view;

import christmas.domain.DiscountResult;
import christmas.domain.EventBadge;
import christmas.domain.Order;
import java.util.List;

public class OutputView {
    private static final String PREVIEW_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDERED_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_PAYMENT_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    private static final String GIFT_MENU = "샴페인 1개";
    private static final String BENEFIT_DETAIL_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFITS_AMOUNT_MESSAGE = "<총혜택 금액>";
    private static final String NOTHING_BENEFITS = "0원";
    private static final String EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String DECEMBER_EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";
    private static final String NOT_APPLICABLE_FOR_BENEFITS_MESSAGE = "없음";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    public static void printPreviewInformationalMessage() {
        System.out.println(PREVIEW_MESSAGE);
    }

    public static void printOrderedMenuAndQuantity(List<Order> orders) {
        System.out.println("\n" + ORDERED_MENU_MESSAGE);
        for (Order order : orders) {
            printOrderedMenu(order);
        }
    }

    private static void printOrderedMenu(Order order) {
        System.out.println(order.getMenuName() + " " + order.getQuantity() + "개");
    }

    public static void printTotalPaymentBeforeDiscount(int totalOrderAmountBeforeDiscount) {
        System.out.println("\n" + TOTAL_PAYMENT_BEFORE_DISCOUNT_MESSAGE);
        System.out.println(totalOrderAmountBeforeDiscount);
    }

    public static void printGiftMenu(boolean giftPossible) {
        System.out.println("\n" + GIFT_MENU_MESSAGE);

        if (!giftPossible) {
            System.out.println(NOT_APPLICABLE_FOR_BENEFITS_MESSAGE);
            return;
        }

        System.out.println(GIFT_MENU);
    }

    public static void printBenefitDetail(List<DiscountResult> results) {
        System.out.println("\n" + BENEFIT_DETAIL_MESSAGE);

        if (results.isEmpty()) {
            System.out.println(NOT_APPLICABLE_FOR_BENEFITS_MESSAGE);
            return;
        }

        results.stream()
                .map(result -> result.getDiscountName() + ": -" + result.getDiscountAmount() + "원")
                .forEach(System.out::println);
    }

    public static void printTotalBenefitsAmount(int totalBenefitAmount) {
        System.out.println("\n" + TOTAL_BENEFITS_AMOUNT_MESSAGE);

        if (totalBenefitAmount == 0) {
            System.out.println(NOTHING_BENEFITS);
            return;
        }

        System.out.println("-" + totalBenefitAmount + "원");
    }

    public static void printExpectedPaymentAfterDiscount(int totalOrderAmountAfterDiscount) {
        System.out.println("\n" + EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE);
        System.out.println(totalOrderAmountAfterDiscount + "원");
    }

    public static void printDecemberEventBadge(EventBadge eventBadge) {
        System.out.println("\n" + DECEMBER_EVENT_BADGE_MESSAGE);
        System.out.println(eventBadge);
    }

    public static void printErrorMessage(String detailErrorMessage) {
        System.out.println(ERROR_MESSAGE + detailErrorMessage);
    }
}
