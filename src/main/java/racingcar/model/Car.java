package racingcar.model;

import racingcar.utility.Validator;

public class Car {
  private String name;
  private int movement = 0;

  public Car(String name) {
    this.validate(name);
    this.name = name;
  }

  private void validate(String name) {
    Validator.validateCar(name);
  }

  public String getName() {
    return this.name;
  }

  public int getMovement() {
    return this.movement;
  }

  public void move(int amount) {
    int AVAILABLE_MOVE_AMOUNT = 4;

    if (amount < AVAILABLE_MOVE_AMOUNT) return;
    this.movement += 1;
  }
}
