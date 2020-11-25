package lotto.domain.winning;

import lotto.domain.LottoNumberBoard;
import lotto.domain.game.LottoNumber;

/**
 * Created By mand2 on 2020-11-26.
 */
public class BonusBall {

    private final int bonusBall ;

    public static final String MESSAGE_BONUS_BALL_NUMBER = "보너스 볼은 숫자형입니다.";

    public BonusBall(int bonusBall) {
        this.bonusBall = bonusBall;
    }

    public static BonusBall from(String before) {
        int bonusBall = getNumber(before);
        checkRange(bonusBall);

        return new BonusBall(bonusBall);
    }

    public int value() {
        return bonusBall;
    }

    private static int getNumber(String before) {
        try {
            return Integer.valueOf(before);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MESSAGE_BONUS_BALL_NUMBER);
        }
    }

    private static void checkRange(int bonusBall) {
        final int min = LottoNumberBoard.VALID_MIN_NUMBER;
        final int max = LottoNumberBoard.VALID_MAX_NUMBER;

        if ( bonusBall < min || bonusBall > max) {
            throw new IllegalArgumentException(String.format(LottoNumber.MESSAGE_VALID_NUMBER, min, max));
        }
    }


}