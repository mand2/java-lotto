package lotto.domain.winning;

import java.util.Arrays;

/**
 * Created By mand2 on 2020-11-20.
 */
public enum WinningRank {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0)
    ;

    private int matchCount;
    private int prize;

    WinningRank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static WinningRank getWinningRank(int matchCount, boolean bonusBall) {
        if (WinningRank.SECOND.matchCount == matchCount) {
            return secondAndThirdPlace(bonusBall);
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }

    private static WinningRank secondAndThirdPlace(boolean bonusBall) {
        if (bonusBall) {
            return WinningRank.SECOND;
        }
        return WinningRank.THIRD;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

}
