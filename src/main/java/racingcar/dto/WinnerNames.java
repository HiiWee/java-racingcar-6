package racingcar.dto;

import java.util.List;
import racingcar.domain.Cars;

public record WinnerNames(List<String> names) {

    public static WinnerNames createFrom(final Cars cars) {
        return new WinnerNames(cars.findAllWinnerNames());
    }

    public String createWinnerMessage() {
        return String.join(",", names);
    }
}
