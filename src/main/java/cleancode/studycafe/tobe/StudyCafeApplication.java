package cleancode.studycafe.tobe;


import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.config.StudyCafeConfig;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafeConfig studyCafeConfig = new StudyCafeConfig(
                new ConsoleInputHandler(),
                new ConsoleOutputHandler(),
                new StudyCafeFileHandler()
        );
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(studyCafeConfig);
        studyCafePassMachine.run();
    }

}
