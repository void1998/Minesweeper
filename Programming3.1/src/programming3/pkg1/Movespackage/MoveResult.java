package programming3.pkg1.Movespackage;

import programming3.pkg1.SquareStatus;

public class MoveResult {

    private int scoreChange;
    
    protected int totalScore;

    private SquareStatus newStatus;

    public MoveResult() {
        newStatus = new SquareStatus();
    }

    public MoveResult(int scoreChange, SquareStatus newStatus) {
        this.scoreChange = scoreChange;
        this.newStatus = newStatus;
    }

    public int getScoreChange() {
        return scoreChange;
    }

    public void setScoreChange(int scoreChange) {
        this.scoreChange = scoreChange;
    }

    public SquareStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(SquareStatus newStatus) {
        this.newStatus = newStatus;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
    
}
