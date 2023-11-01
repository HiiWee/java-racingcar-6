package racingcar.domain;

import java.util.Optional;

public class CarsRepository {

    private Cars storedCars;

    public void save(final Cars cars) {
        storedCars = cars;
    }

    public Optional<Cars> findCars() {
        return Optional.ofNullable(storedCars);
    }
}
