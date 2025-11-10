package racingcar.utility;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Validator {
  public static void validateCarNames(String carNames) {
    String[] carNameArr = Arrays.stream(carNames.split(","))
            .map(String::trim)
            .toArray(String[]::new);

    String[] distinctCarNameArr = Arrays.stream(carNameArr)
            .distinct()
            .toArray(String[]::new);

    if (carNameArr.length != distinctCarNameArr.length) {
      throw new IllegalArgumentException("[ERROR] 자동차 이름이 중복됩니다.");
    }
  }

  public static void validateRepeat(String repeat) {
    String trimRepeat = repeat.trim();

    if (trimRepeat.isEmpty()) {
      throw new IllegalArgumentException("[ERROR] 시도 횟수가 비었습니다.");
    }

    Pattern noneDigitPattern = Pattern.compile("\\D");

    if (noneDigitPattern.matcher(trimRepeat).find()) {
      throw new IllegalArgumentException("[ERROR] 숫자로 입력해주세요.");
    }

    if (trimRepeat.equals("0")) {
      throw new IllegalArgumentException("[ERROR] 숫자로 입력해주세요.");
    }
  }

  public static void validateCar(String name) {
    String trimName = name.trim();

    if (trimName.isEmpty()) {
      throw new IllegalArgumentException("[ERROR] 부여된 자동차 이름이 없습니다.");
    }

    if (trimName.length() > 4) {
      throw new IllegalArgumentException("[ERROR] 자동차 이름은 5글자 이하로 설정해야 합니다.");
    }
  }
}
