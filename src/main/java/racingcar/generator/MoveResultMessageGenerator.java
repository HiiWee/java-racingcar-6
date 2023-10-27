package racingcar.generator;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.MoveResult;

public class MoveResultMessageGenerator {

    private static final String RESULT_FORMAT = "%s : %s\n";
    private static final String MOVEMENT_BAR = "-";

    public static String generateMoveResultsMessage(final List<MoveResult> moveResults) {
        return moveResults.stream()
                .map(MoveResultMessageGenerator::createSingleMessage)
                .collect(Collectors.joining());
    }

    private static String createSingleMessage(final MoveResult moveResult) {
        return String.format(RESULT_FORMAT, moveResult.getName(), MOVEMENT_BAR.repeat(moveResult.getPosition()));
    }
}