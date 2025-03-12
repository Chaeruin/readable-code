package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafePasses {

    private final List<StudyCafePass> studyCafePasses;

    public StudyCafePasses(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public StudyCafePass getStudyCafePass(int index) {
        return studyCafePasses.get(index);
    }

    public int getSize() {
        return this.studyCafePasses.size();
    }

    public StudyCafePasses getStudyCafePassesByPassType(StudyCafePassType studyCafePassType) {
        final List<StudyCafePass> passesByPassType = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isSamePassTypeWith(studyCafePassType))
                .toList();
        return new StudyCafePasses(passesByPassType);
    }
}
