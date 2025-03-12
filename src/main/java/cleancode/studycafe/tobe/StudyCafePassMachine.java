package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafeConfig;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import cleancode.studycafe.tobe.model.StudyCafePasses;
import java.util.List;

public class StudyCafePassMachine {

    private final ConsoleInputHandler inputHandler;
    private final ConsoleOutputHandler outputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;

    public StudyCafePassMachine(final StudyCafeConfig studyCafeConfig) {
        this.inputHandler = studyCafeConfig.getInputHandler();
        this.outputHandler = studyCafeConfig.getOutputHandler();
        this.studyCafeFileHandler = studyCafeConfig.getStudyCafeFileHandler();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            StudyCafePasses studyCafePasses = new StudyCafePasses(studyCafeFileHandler.readStudyCafePasses());

            if (doesUserChooseHourly(studyCafePassType)) {
                permit(studyCafePasses, StudyCafePassType.HOURLY);
            }
            if (doesUserChooseWeekly(studyCafePassType)) {
                permit(studyCafePasses, StudyCafePassType.WEEKLY);
            }
            if (doesUserChooseFixed(studyCafePassType)) {
                permit(studyCafePasses, StudyCafePassType.FIXED);
            }
        } catch (AppException e) {
            outputHandler.showExceptionMessage(e);
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void permit(StudyCafePasses studyCafePasses, StudyCafePassType studyCafePassType) {
        StudyCafePasses passes = studyCafePasses.getStudyCafePassesByPassType(studyCafePassType);
        outputHandler.showPassListSelection(passes);
        StudyCafePass selectedPass = inputHandler.getSelectPass(passes);
        if (studyCafePassType == StudyCafePassType.FIXED) {
            checkLockerPass(selectedPass);
            return;
        }
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    private void checkLockerPass(StudyCafePass selectedPass) {
        StudyCafeLockerPasses lockerPasses = new StudyCafeLockerPasses(studyCafeFileHandler.readLockerPasses());
        StudyCafeLockerPass lockerPass = lockerPasses.getLockerPassByCafePassTypeAndDuration(selectedPass);

        outputHandler.askLockerPass(lockerPass);
        boolean lockerSelection = inputHandler.getLockerSelection();

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
            return;
        }
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    private boolean doesUserChooseFixed(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.FIXED;
    }

    private boolean doesUserChooseWeekly(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.WEEKLY;
    }

    private boolean doesUserChooseHourly(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.HOURLY;
    }

}
