package racingcar.utility;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidatorTest {
  @Nested
  class 사용자_입력_자동차_이름_검증 {

    @Test
    public void 중복된_차량_이름이_있으면_예외_발생() {
      String carNames = "A-1, A-2, A-1";

      assertThatThrownBy(() -> {
        Validator.validateCarNames(carNames);
      }).isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class 사용자_입력_시도_횟수_검증 {

    @Test
    public void 입력하지_않은_경우_예외_발생() {
      String repeat = "";

      assertThatThrownBy(() -> {
        Validator.validateRepeat(repeat);
      }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 글자가_포함되어_있는_경우_예외_발생() {
      String[] inputs = {"a", "0x12", "0b0101", "-1", "1.2"};

      for (String repeat : inputs) {
        assertThatThrownBy(() -> {
          Validator.validateRepeat(repeat);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }

    @Test
    public void 숫자가_0인_경우_예외_발생() {
      String repeat = "0";

      assertThatThrownBy(() -> {
        Validator.validateRepeat(repeat);
      }).isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class 자동차_검증 {

    @Test
    public void 입력값이_없는_경우_예외_발생() {
      String name = "";

      assertThatThrownBy(() -> {
        Validator.validateCar(name);
      }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 이름_5글자가_넘는_경우_예외_발생() {
      String[] inputs = {"A-1234", "B-012345", "C-112201"};

      for (String name : inputs) {
        assertThatThrownBy(() -> {
          Validator.validateCar(name);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }
  }
}
