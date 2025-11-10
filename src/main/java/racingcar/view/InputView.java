package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
  public static String readLine (String prompt) {
    System.out.println(prompt);
    return Console.readLine();
  }
}
