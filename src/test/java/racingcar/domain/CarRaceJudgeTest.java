package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.MoveResult;

class CarRaceJudgeTest {

    CarRaceJudge carRaceJudge = new CarRaceJudge();
    CarRepository carRepository = CarRepository.getInstance();

    @DisplayName("심판은 레이스할 자동차를 등록합니다.")
    @Test
    void addCars() {
        // given
        List<String> names = List.of("pobi", "jason");
        List<Car> cars = List.of(Car.of("pobi", 0),
                Car.of("jason", 0));

        // when
        carRaceJudge.addCars(names);
        List<Car> findCars = carRepository.findAll();
        boolean isSaved = findCars.containsAll(cars);

        // then
        assertThat(isSaved).isTrue();
    }

    @DisplayName("자동차 이름은 중복될 수 없습니다.")
    @Test
    void addCars_exception_withDuplicateNames() {
        // given
        List<String> names = List.of("pobi", "pobi");

        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> carRaceJudge.addCars(names))
                .withMessageContaining("자동차 이름은 중복될 수 없습니다.");
    }

    @DisplayName("자동차는 4이상의 값이 입력되면 한 칸 전진한다.")
    @Test
    void moveCars() {
        // given
        List<String> names = List.of("pobi", "jason");
        carRaceJudge.addCars(names);

        // when
        carRaceJudge.moveCars(() -> 4);
        boolean isMove = carRaceJudge.createSingleMoveResults()
                .stream()
                .map(MoveResult::getPosition)
                .allMatch(number -> number == 1);

        // then
        assertThat(isMove).isTrue();
    }

    @DisplayName("자동차는 4미만의 값이 입력되면 정지한다.")
    @Test
    void moveCars_fail_notEnoughValue() {
        // given
        List<String> names = List.of("pobi", "jason");
        carRaceJudge.addCars(names);

        // when
        carRaceJudge.moveCars(() -> 3);
        boolean isStop = carRaceJudge.createSingleMoveResults()
                .stream()
                .map(MoveResult::getPosition)
                .allMatch(number -> number == 0);

        // then
        assertThat(isStop).isTrue();
    }

    @DisplayName("우승자를 찾을 수 있다.")
    @Test
    void findWinners() {
        // given
        List<String> names = List.of("pobi", "jason");
        carRaceJudge.addCars(names);

        // when
        carRaceJudge.moveCars(() -> 4);
        String winners = String.join(",", carRaceJudge.findWinners());

        // then
        assertThat(winners).isEqualTo("pobi,jason");
    }
}