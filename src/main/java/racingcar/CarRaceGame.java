package racingcar;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import racingcar.domain.RaceJudge;
import racingcar.dto.MoveResults;
import racingcar.dto.WinnerNames;
import racingcar.generator.RandomNumberGenerator;
import racingcar.validator.InputMoveCountValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class CarRaceGame {

    private final InputView inputView;
    private final OutputView outputView;
    private final RaceJudge raceJudge;

    public CarRaceGame(final InputView inputView, final OutputView outputView, final RaceJudge raceJudge) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.raceJudge = raceJudge;
    }

    public void startRace() {
        addRaceCars();
        int moveCount = inputMoveCount();
        startMoveCars(moveCount);
        printWinner();
    }

    private void addRaceCars() {
        List<String> carNames = inputView.inputCarNames();
        raceJudge.addCars(carNames);
    }

    private int inputMoveCount() {
        String inputMoveCount = inputView.inputMoveCount();
        InputMoveCountValidator.validateCount(inputMoveCount);
        return Integer.parseInt(inputMoveCount);
    }

    private void startMoveCars(final int moveCount) {
        outputView.printExecutionMessage();
        for (int count = 1; count <= moveCount; count++) {
            raceJudge.moveCars(RandomNumberGenerator.getGenerateSupplier());
            printSingleMoveResult();
        }
    }

    private void printWinner() {
        WinnerNames names = raceJudge.findAllWinnerNames();
        outputView.printWinnerNames(names.createWinnerMessage());
    }

    private void printSingleMoveResult() {
        MoveResults singleMoveResults = raceJudge.createSingleMoveResults();
        outputView.printSingleMoveResult(singleMoveResults.createResultsMessage());
    }
}
