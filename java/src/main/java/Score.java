
public abstract class Score {
    public final static int MINIMUM_WIN_SCORE = 4;

    public static Score create(int score) {
        if(score < 0) {
            throw new IllegalArgumentException("score cannot be negative");
        }

        switch(score) {
        case 0:
            return new LoveScore();

        case 1:
            return new FifteenScore();

        case 2:
            return new ThirtyScore();

        default:
            return new FortyScore(score);
        }
    }

    public static Score increaseByOne(Score original) {
        return Score.create(original.getRawScore() + 1);
    }

    public abstract int getRawScore();
    public abstract String getScoreText();

    @Override
    public int hashCode() {
        return getRawScore();
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Score)) {
            return false;
        }

        Score otherScore = (Score) other;

        return getRawScore() == otherScore.getRawScore();
    }

    public boolean isElegibleForVictory() {
        return getRawScore() >= MINIMUM_WIN_SCORE;
    }

    public boolean isElegibleForDeuce() {
        return getRawScore() >= MINIMUM_WIN_SCORE - 1;
    }
}
