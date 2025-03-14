package cleancode.studycafe.tobe.model;

import cleancode.studycafe.asis.exception.AppException;
import java.util.Arrays;

public enum StudyCafePassType {

    HOURLY("1", "시간 단위 이용권"),
    WEEKLY("2", "주 단위 이용권"),
    FIXED("3", "1인 고정석");

    private final String commandNumber;
    private final String description;

    StudyCafePassType(String commandNumber, String description) {
        this.commandNumber = commandNumber;
        this.description = description;
    }

    public static StudyCafePassType from(String userInput) {
        return Arrays.stream(StudyCafePassType.values())
                .filter(studyCafePassType -> studyCafePassType.commandNumber.equals(userInput))
                .findAny()
                .orElseThrow(() -> new AppException("잘못된 커맨드 입력입니다."));
    }
}
