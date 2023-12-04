package christmas.validation;

import christmas.domain.Menu;
import java.util.Arrays;
import java.util.List;

public class WarningValidator {
    private static final int EVENT_THRESHOLD = 10000;
    private static final int MAX_MENU_COUNT = 20;
    private static final String MENU_ONLY_DRINK_EXCEPTION_MESSAGE = "음료로만 주문할 수 없습니다.";
    private static final String OVER_TWENTY_MENU_EXCEPTION_MESSAGE = "총 주문 개수를 20개 이하로 주문해주세요";
    private static final String DRINK = "음료";

    public static void validateWarning(String menuAndQuantity) {
        List<String> menuAndQuantityList = Arrays.stream(menuAndQuantity.split(","))
                .toList();

        checkDrinkOnlyOrder(menuAndQuantityList);
        checkMenuCount(menuAndQuantityList);
    }

    public static boolean EventApplicability(String menuAndQuantity) {
        List<String> menuAndQuantityList = Arrays.stream(menuAndQuantity.split(","))
                .toList();
        return checkEventApplicability(menuAndQuantityList);
    }

    private static void checkDrinkOnlyOrder(List<String> menuAndQuantityList) {
        if (menuAndQuantityList.stream()
                .allMatch(menuAndQuantity -> Menu.valueOf(menuAndQuantity.split("-")[0])
                        .getCategory()
                        .equals(DRINK))) {
            throw new IllegalArgumentException(MENU_ONLY_DRINK_EXCEPTION_MESSAGE);
        }
    }

    private static void checkMenuCount(List<String> menuAndQuantityList) {
        int totalQuantity = 0;
        for (String menuAndQuantity : menuAndQuantityList) {
            totalQuantity += Integer.parseInt(menuAndQuantity.split("-")[1]);
        }

        if (totalQuantity > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(OVER_TWENTY_MENU_EXCEPTION_MESSAGE);
        }
    }

    private static boolean checkEventApplicability(List<String> menuAndQuantityList) {
        int totalAmount = 0;

        for (String menuAndQuantity : menuAndQuantityList) {
            String[] parts = menuAndQuantity.split("-");
            if (parts.length == 2) {
                totalAmount += Menu.valueOf(parts[0].trim()).getPrice() * Integer.parseInt(parts[1].trim());
            }
        }

        return totalAmount >= EVENT_THRESHOLD;
    }
}
