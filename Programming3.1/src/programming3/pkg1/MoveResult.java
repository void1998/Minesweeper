package programming3.pkg1;

public class MoveResult {

    private int scoreChange;

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
    
    
}
