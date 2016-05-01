public class TennisGame1 implements TennisGame {
    private Player player1;
    private Player player2;

    public TennisGame1(String player1Name, String player2Name) {
        player1 = new Player(player1Name, Score.create(0));
        player2 = new Player(player2Name, Score.create(0));
    }

    public void wonPoint(String playerName) {
        if (player1.hasSameName(playerName)) {
            player1.increaseScoreByOne();
        } else if (player2.hasSameName(playerName)) {
            player2.increaseScoreByOne();
        } else {
            throw new IllegalArgumentException(
                    String.format("Player named %s does not exit", playerName));
        }
    }

    public String getScore() {
        String scoreText;

        if (isDraw()) {
            scoreText = getDrawScore(player1.getScore());
        } else if (player1.getScore().isElegibleForVictory()
                || player2.getScore().isElegibleForVictory()) {
            scoreText = getAdvantagedScore();
        } else {
            scoreText =
                    String.format(
                            "%s-%s",
                            player1.getScore().getScoreText(),
                            player2.getScore().getScoreText());
        }

        return scoreText;
    }

    private boolean isDraw() {
        return player1.getScore().equals(player2.getScore());
    }

    private String getDrawScore(Score score) {
        assert isDraw()
        : String.format(
                "Player 1: %d. Player 2: %d",
                player1.getScore().getRawScore(),
                player2.getScore().getRawScore());

        if(score.isElegibleForDeuce()) {
            return "Deuce";
        } else {
            return score.getScoreText() + "-All";
        }
    }

    private String getAdvantagedScore() {
        String score;
        assert getScoresDifference() != 0 : getScoresDifference();

        if (getScoresDifference() == 1) {
            score = "Advantage player1";
        } else if (getScoresDifference() == -1) {
            score = "Advantage player2";
        } else if (getScoresDifference() >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }

        return score;
    }

    private int getScoresDifference() {
        return player1.getScore().getRawScore() - player2.getScore().getRawScore();
    }
}
