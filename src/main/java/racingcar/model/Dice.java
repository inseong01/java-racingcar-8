package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;

public class Dice {
  public static int roll() {
    int START_RANGE = 0;
    int END_RANGE = 6;

    return Randoms.pickNumberInRange(START_RANGE, END_RANGE);
  }
}
