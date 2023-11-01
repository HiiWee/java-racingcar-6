package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Cars;

public record MoveResults(List<MoveResult> results) {

    public static MoveResults createFrom(final Cars cars) {
        return new MoveResults(cars.cars()
                .stream()
                .map(MoveResult::createResultFrom)
                .toList());
    }

    public String createResultsMessage() {
        return results.stream()
                .map(MoveResult::createResultMessage)
                .collect(Collectors.joining());
    }
}
