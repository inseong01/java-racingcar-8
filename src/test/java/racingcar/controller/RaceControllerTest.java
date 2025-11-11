package racingcar.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import racingcar.model.Dice;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import static org.assertj.core.api.Assertions.*;

public class RaceControllerTest {
  String ENTER_CAR_NAMES_PROMPT = "자동차 이름을 입력해주세요. (, 구분)";
  String ENTER_REPEAT_NUMBER_PROMPT = "시도 횟수를 입력해주세요.";

  @Mock
  InputView inputView;

  @BeforeEach
  public void setup() {
    inputView = Mockito.mock(InputView.class);
  }

  @Nested
  class 자동차_입력 {
    OutputView outputView = new OutputView();


    @Test
    void 정상_호출() {
      String input = " car1 , car2 \n";
      Mockito.when(inputView.readLine(ENTER_CAR_NAMES_PROMPT))
              .thenReturn(input);

      RaceController controller = new RaceController(inputView, outputView);

      assertThatCode(controller::setCarNames).doesNotThrowAnyException();
      Mockito.verify(inputView).readLine(ENTER_CAR_NAMES_PROMPT);
    }

    @Test
    void 실패_호출() {
      String input = "A-12345, A-12345\n";
      Mockito.when(inputView.readLine(ENTER_CAR_NAMES_PROMPT))
              .thenReturn(input);

      RaceController controller = new RaceController(inputView, outputView);

      assertThatThrownBy(controller::setCarNames)
              .isInstanceOf(IllegalArgumentException.class);
      Mockito.verify(inputView).readLine(ENTER_CAR_NAMES_PROMPT);
    }
  }

  @Nested
  class 시도_횟수_입력 {
    OutputView outputView = new OutputView();

    @Test
    void 성공_호출() {
      String input = "3";
      Mockito.when(inputView.readLine(ENTER_REPEAT_NUMBER_PROMPT))
              .thenReturn(input);

      RaceController controller = new RaceController(inputView, outputView);

      assertThatCode(controller::setRepeatNumber)
              .doesNotThrowAnyException();
      Mockito.verify(inputView).readLine(ENTER_REPEAT_NUMBER_PROMPT);
    }

    @Test
    void 실패_호출() {
      String input = "a";
      Mockito.when(inputView.readLine(ENTER_REPEAT_NUMBER_PROMPT)).thenReturn(input);

      RaceController controller = new RaceController(inputView, outputView);

      assertThatThrownBy(controller::setRepeatNumber)
              .isInstanceOf(IllegalArgumentException.class);
      Mockito.verify(inputView).readLine(ENTER_REPEAT_NUMBER_PROMPT);
    }
  }

  @Nested
  class 게임_통합 {

    @Mock
    MockedStatic<Dice> dice;
    OutputView outputView;

    @BeforeEach
    void setup() {
      dice = Mockito.mockStatic(Dice.class);
      outputView = Mockito.mock(OutputView.class);

      int ABLE_MOVE_AMOUNT = 6;
      int UNABLE_MOVE_AMOUNT = 3;

      String CAR_NAMES_INPUT = "   A-1, B-1, C-1 \n";
      String REPEAT_INPUT = "1";

      Mockito.when(inputView.readLine(ENTER_CAR_NAMES_PROMPT))
              .thenReturn(CAR_NAMES_INPUT);
      Mockito.when(inputView.readLine(ENTER_REPEAT_NUMBER_PROMPT))
              .thenReturn(REPEAT_INPUT);
      Mockito.when(Dice.roll())
              .thenReturn(ABLE_MOVE_AMOUNT)
              .thenReturn(UNABLE_MOVE_AMOUNT)
              .thenReturn(ABLE_MOVE_AMOUNT);
    }

    @AfterEach
    void tearDown() {
      dice.close();
    }

    @Test
    void 게임_실행_성공_호출() {
      String[] logs = {
              "A-1 : -",
              "B-1 : ",
              "C-1 : -"
      };

      RaceController controller = new RaceController(inputView, outputView);
      controller.setCarNames();
      controller.setRepeatNumber();

      assertThatCode(controller::playGame)
              .doesNotThrowAnyException();
      for (String log : logs) {
        Mockito.verify(outputView).print(log);
      }
    }

    @Test
    void 시합_결과_성공_흐름() {
      String[] logs = {"최종 우승자 : A-1, C-1"};

      RaceController controller = new RaceController(inputView, outputView);
      controller.setCarNames();
      controller.setRepeatNumber();
      controller.playGame();

      assertThatCode(controller::resultRace).doesNotThrowAnyException();
      for (String log : logs) {
        Mockito.verify(outputView).print(log);
      }
    }
  }
}
