package racingcar.domain;

import java.util.List;
import java.util.function.Supplier;
import racingcar.dto.MoveResult;

public class CarRaceJudge {

    private final CarRepository carRepository = CarRepository.getInstance();

    public void addCars(final List<String> carNames) {
        Cars cars = Cars.from(carNames);
        carRepository.save(cars);
    }

    public void moveCars(final Supplier<Integer> randomNumberSupplier) {
        Cars cars = carRepository.findCars();
        cars.moveForwardWithAllCars(randomNumberSupplier.get());
    }

    public List<MoveResult> createSingleMoveResults() {
        Cars cars = carRepository.findCars();
        return cars.cars()
                .stream()
                .map(MoveResult::createResultOf)
                .toList();
    }

    public List<String> findWinners() {
        Cars cars = carRepository.findCars();
        int winnerPosition = findWinnerPosition(cars);

        return cars.cars()
                .stream()
                .filter(car -> car.isWinner(winnerPosition))
                .map(Car::getName)
                .toList();
    }

    private int findWinnerPosition(final Cars cars) {
        return cars.cars()
                .stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("우승자를 찾을 수 없습니다."));
    }
}