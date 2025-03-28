package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePasses;

public interface OutputHandler {

    void showWelcomeMessage();

    void showAnnouncement();

    void askPassTypeSelection();

    void showPassListSelection(StudyCafePasses passes);

    void askLockerPass(StudyCafeLockerPass lockerPass);

    void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass);

    void showSimpleMessage(String message);

    void showExceptionMessage(AppException exception);

}