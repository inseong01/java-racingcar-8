package racingcar.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CarTest {
  @Nested
  class 인스턴스_생성 {

    @Test
    public void 이름_잘못_입력한_경우_예외_발생() {
      String emptyName = "";
      String overLengthName = "A12345";
      String whitespaceName = "    ";

      String[] inputs = {emptyName, overLengthName, whitespaceName};


      for (String name : inputs) {
        assertThatThrownBy(() -> {
          new Car(name);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }

    @Test
    public void 공백제외_글자수가_5_미만인_경우_통과() {
      String name = "  A-15  ";

      assertThatCode(() -> {
        new Car(name);
      }).doesNotThrowAnyException();
    }
  }

  @Nested
  class 자동자_기본_정보 {

    @Test
    public void 메서드를_통해_이름_반환() {
      String carName = "TEST";

      Car testCar = new Car(carName);

      assertThat(testCar.getName()).isEqualTo(carName);
    }

    @Test
    public void 메서드를_통해_이동거리_반환() {
      String carName = "TEST";
      int initMovement = 0;

      Car testCar = new Car(carName);

      assertThat(testCar.getMovement()).isEqualTo(initMovement);
    }
  }

  @Nested
  class 움직임_메서드 {

    @Test
    public void amount_4_이상인_경우_이동_증가() {
      int[] inputs = {4, 5, 6};
      int[] outputs = {1, 2, 3};

      Car testCar = new Car("TEST");

      for (int i = 0; i < inputs.length; i++) {
        testCar.move(inputs[i]);

        assertThat(testCar.getMovement()).isEqualTo(outputs[i]);
      }
    }

    @Test
    public void amount_4_미만인_경우_이동_금지() {
      int[] inputs = {1, 2, 3};
      int output = 0;

      Car testCar = new Car("TEST");

      for (int input : inputs) {
        testCar.move(input);

        assertThat(testCar.getMovement()).isEqualTo(output);
      }
    }
  }
}
