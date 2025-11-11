package racingcar.controller;

import racingcar.model.Car;
import racingcar.model.Dice;
import racingcar.utility.Validator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RaceController {
  private final InputView inputView;
  private final OutputView outputView;

  private int repeat;
  private Car[] cars;

  public RaceController(final InputView inputView, final OutputView outputView) {
    this.inputView = inputView;
    this.outputView = outputView;
  }

  public void setCarNames() {
    String ENTER_CAR_NAMES_PROMPT = "자동차 이름을 입력해주세요. (, 구분)";

    String input = this.inputView.readLine(ENTER_CAR_NAMES_PROMPT);
    Validator.validateCarNames(input);

    String[] carNames = Arrays.stream(input.split(","))
            .map(String::trim)
            .toArray(String[]::new);

    setCars(carNames);
  }

  private void setCars(String[] carNames) {
    this.cars = Arrays.stream(carNames)
            .map(Car::new)
            .toArray(Car[]::new);
  }

  public void setRepeatNumber() {
    String ENTER_REPEAT_NUMBER_PROMPT = "시도 횟수를 입력해주세요.";

    String input = this.inputView.readLine(ENTER_REPEAT_NUMBER_PROMPT);
    Validator.validateRepeat(input);

    this.repeat = Integer.parseInt(input.trim());
  }

  public void playGame() {
    String[] rounds = new String[this.repeat];

    this.outputView.print("실행 결과\n");

    for (String round : rounds) {
      playByRound();
    }
  }

  private void playByRound() {
    Arrays.stream(this.cars).forEach((car) -> {
      int movement = Dice.roll();
      car.move(movement);

      String carName = car.getName();
      String carMovement = "-".repeat(car.getMovement());
      String resultByCar = String.format("%s : %s", carName, carMovement);

      this.outputView.print(resultByCar);
    });

    this.outputView.print("");
  }

  public void resultRace() {
    int[] scores = Arrays.stream(this.cars)
            .mapToInt(Car::getMovement)
            .toArray();
    int highScore = Arrays.stream(scores).max().orElse(0);

    String winners = Arrays.stream(this.cars)
            .filter((car) -> car.getMovement() == highScore)
            .map(Car::getName)
            .collect(Collectors.joining(", "));

    String result = String.format("최종 우승자 : %s", winners);
    this.outputView.print(result);
  }
}
