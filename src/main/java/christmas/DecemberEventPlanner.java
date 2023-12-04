package christmas;

import christmas.domain.DiscountResult;
import christmas.domain.EventBadge;
import christmas.domain.Order;
import christmas.service.DiscountCalculatorService;
import christmas.service.OrderService;
import christmas.util.OrderParser;
import christmas.validation.MenuAndQuantityValidator;
import christmas.validation.VisitDayInputValidator;
import christmas.validation.WarningValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class DecemberEventPlanner {
    private final int CHAMPAGNE_PRICE = 25000;
    private int expectedVisitDay;
    private List<Order> orders;
    private List<DiscountResult> discountResults;
    private int totalBenefitsAmount;
    private int totalOrderAmountBeforeDiscount;
    private boolean eventPossible;

    private final OrderService orderService;
    private final DiscountCalculatorService discountCalculatorService;

    public DecemberEventPlanner(OrderService orderService, DiscountCalculatorService discountCalculatorService) {
        this.orderService = orderService;
        this.discountCalculatorService = discountCalculatorService;
    }

    public void run() {
        inputVisitDay();
        inputOrder();
        printOrders();
        calculateTotalOrderAmountBeforeDiscount();
        checkGiftMenu();
        calculateBenefitsDetails();
        printTotalBenefitsAmount();
        calculateTotalOrderAmountAfterDiscount();
        checkEventBadge();
    }

    private void inputVisitDay() {
        boolean validInput = false;
        while (!validInput) {
            try {
                String expectedVisitDateInput = InputView.inputExpectedDate();
                VisitDayInputValidator.validate(expectedVisitDateInput);
                expectedVisitDay = Integer.parseInt(expectedVisitDateInput);
                validInput = true;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void inputOrder() {
        boolean validInput = false;
        while (!validInput) {
            try {
                String menuAndQuantityInput = InputView.inputMenuAndQuantity();
                MenuAndQuantityValidator.validate(menuAndQuantityInput);
                WarningValidator.validateWarning(menuAndQuantityInput);
                eventPossible = WarningValidator.EventApplicability(menuAndQuantityInput);
                List<Order> parsedOrders = OrderParser.parseOrders(menuAndQuantityInput);

                for (Order order : parsedOrders) {
                    orderService.recordOrder(order);
                }

                validInput = true;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printOrders() {
        OutputView.printPreviewInformationalMessage();
        orders = orderService.getAllOrderRecord();
        OutputView.printOrderedMenuAndQuantity(orders);
    }

    private void calculateTotalOrderAmountBeforeDiscount() {
        orders = orderService.getAllOrderRecord();
        totalOrderAmountBeforeDiscount = discountCalculatorService.calculateTotalOrderAmountBeforeDiscount(orders);
        OutputView.printTotalPaymentBeforeDiscount(totalOrderAmountBeforeDiscount);
    }

    private void checkGiftMenu() {
        orders = orderService.getAllOrderRecord();
        OutputView.printGiftMenu(discountCalculatorService.applyChampagneGift(totalOrderAmountBeforeDiscount));
    }

    private void calculateBenefitsDetails() {
        if (!eventPossible) {
            discountResults = new ArrayList<>();
            OutputView.printBenefitDetail(discountResults);
            return;
        }

        discountResults = discountCalculatorService.calculateDiscounts(expectedVisitDay);
        OutputView.printBenefitDetail(discountResults);
    }

    private void printTotalBenefitsAmount() {
        totalBenefitsAmount = discountCalculatorService.calculateTotalBenefitAmount(discountResults);
        OutputView.printTotalBenefitsAmount(totalBenefitsAmount);
    }

    private void calculateTotalOrderAmountAfterDiscount() {
        int totalOrderAmountAfterDiscount = totalOrderAmountBeforeDiscount - totalBenefitsAmount;
        boolean possibleApplyChampagneGift = discountCalculatorService.applyChampagneGift(totalOrderAmountBeforeDiscount);
        if (possibleApplyChampagneGift) {
            totalOrderAmountAfterDiscount += CHAMPAGNE_PRICE;
        }
        OutputView.printExpectedPaymentAfterDiscount(totalOrderAmountAfterDiscount);
    }

    private void checkEventBadge() {
        EventBadge eventBadge = EventBadge.calculateEventBadge(totalBenefitsAmount);
        OutputView.printDecemberEventBadge(eventBadge);
    }


}
