
public class FortyScore extends Score {
    private int score;

    public FortyScore(int score) {
        this.score = score;
    }

    @Override
    public int getRawScore() {
        return score;
    }

    @Override
    public String getScoreText() {
        return "Forty";
    }
}
