package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.exception.AppException;
import java.util.List;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> studyCafeLockerPasses;
    public StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        this.studyCafeLockerPasses = studyCafeLockerPasses;
    }

    public StudyCafeLockerPass getLockerPassByCafePassTypeAndDuration(StudyCafePass selectedPass) {
        return studyCafeLockerPasses.stream()
                .filter(option -> option.isSamePassTypeAndDuration(selectedPass))
                .findFirst()
                .orElseThrow(() -> new AppException("조건에 맞는 사물함이 없습니다."));
    }
}
