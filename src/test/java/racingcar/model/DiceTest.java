package racingcar.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiceTest {
  @Test
  public void 주사위를_호출하면_범위_숫자_반환() {
    assertThat(Dice.roll()).isBetween(1, 6);
  }
}
