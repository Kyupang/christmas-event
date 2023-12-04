package christmas.domain;

import java.util.Arrays;

public enum EventBadge {
    산타(20000),
    트리(10000),
    별(5000),
    없음(0);

    private final int threshold;

    EventBadge(int threshold) {
        this.threshold = threshold;
    }

    public static EventBadge calculateEventBadge(int totalBenefitAmount) {
        return Arrays.stream(values())
                .filter(eventBadge -> totalBenefitAmount >= eventBadge.threshold)
                .findFirst()
                .orElse(없음);
    }
}

