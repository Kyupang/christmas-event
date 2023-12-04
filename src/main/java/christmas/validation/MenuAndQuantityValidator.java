package christmas.validation;

import christmas.domain.Menu;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class MenuAndQuantityValidator {
    private static final String INVALID_FORMAT_EXCEPTION_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String MENU_BOARD_EXCEPTION_MESSAGE = "존재하지 않는 메뉴명이 포함되어 있습니다.";
    private static final String MENU_QUANTITY_EXCEPTION_MESSAGE = "메뉴 개수는 0보다 커야 합니다.";
    private static final String DUPLICATE_MENU_EXCEPTION_MESSAGE = "중복된 메뉴명이 존재합니다.";


    public static void validate(String menuAndQuantity) {
        if (!menuAndQuantity.contains(",")) {
            checkInputPattern(List.of(menuAndQuantity));
            menuAndQuantity = menuAndQuantity + ",";
        }

        List<String> menuAndQuantityList = Arrays.stream(menuAndQuantity.split(","))
                .toList();

        checkInputPattern(menuAndQuantityList);
        checkMenuExactlyOnMenuBoard(menuAndQuantityList);
        checkMenuQuantity(menuAndQuantityList);
        checkMenuDuplication(menuAndQuantityList);
    }


    private static void checkInputPattern(List<String> menuAndQuantityList) {
        Pattern pattern = Pattern.compile("^[가-힣]+-[0-9]{1,2}$");

        if (!menuAndQuantityList.stream().allMatch(item -> pattern.matcher(item).matches())) {
            throw new IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private static void checkMenuExactlyOnMenuBoard(List<String> menuAndQuantityList) {
        List<String> menuNames = Arrays.stream(Menu.values())
                .map(Enum::name)
                .toList();

        for (String menuAndQuantity : menuAndQuantityList) {
            String menuName = menuAndQuantity.split("-")[0];
            if (!menuNames.contains(menuName)) {
                throw new IllegalArgumentException(MENU_BOARD_EXCEPTION_MESSAGE);
            }
        }
    }

    private static void checkMenuQuantity(List<String> menuAndQuantityList) {
        for (String menuAndQuantity : menuAndQuantityList) {
            int quantity = Integer.parseInt(menuAndQuantity.split("-")[1]);
            if (quantity == 0) {
                throw new IllegalArgumentException(MENU_QUANTITY_EXCEPTION_MESSAGE);
            }
        }
    }

    private static void checkMenuDuplication(List<String> menuAndQuantityList) {
        Set<String> menuSet = new HashSet<>();

        for (String menuAndQuantity : menuAndQuantityList) {
            String menuName = menuAndQuantity.split("-")[0];
            if (!menuSet.add(menuName)) {
                throw new IllegalArgumentException(DUPLICATE_MENU_EXCEPTION_MESSAGE);
            }
        }
    }
}
