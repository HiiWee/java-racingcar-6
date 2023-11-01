package racingcar;

import racingcar.domain.CarsRepository;
import racingcar.domain.RaceJudge;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        CarRaceGame carRaceGame = new CarRaceGame(
                new InputView(),
                new OutputView(),
                new RaceJudge(new CarsRepository()));
        carRaceGame.startRace();
    }
}
