package racingcar.view;

public class OutputView {

    public void printExecutionMessage() {
        System.out.println("실행 결과");
    }

    public void printSingleMoveResult(final String result) {
        System.out.println(result);
    }

    public void printWinnerNames(final String names) {
        System.out.print("최종 우승자 : ");
        System.out.println(names);
    }
}
