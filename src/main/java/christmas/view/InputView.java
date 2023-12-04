package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EXPECTED_DATE_INPUT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static String inputExpectedDate() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(EXPECTED_DATE_INPUT_MESSAGE);
        String input = Console.readLine();
        return input;
    }

    public static String inputMenuAndQuantity() {
        System.out.println(ORDER_MESSAGE);
        String input = Console.readLine();
        return input;
    }

}
