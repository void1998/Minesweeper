package programming3.pkg1;
public abstract class Player {

    private String name;
    private Score currentScore;
    private PlayerStatue currentStatue;
    
    Player()
    {
        this.name = "Player";
        this.currentScore = new NumiricScore();
        this.currentScore = new NumiricScore();
        this.currentStatue = new PlayerStatue();
    }
    Player(Score score,PlayerStatue statue)
    {
        this.currentScore = score;
        this.currentStatue = statue;
    }

    public Score getCurrentScore() {
        return currentScore;
    }
    public void SetCurrentScore(Score score) {
        this.currentScore = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerStatue getCurrentStatue() {
        return currentStatue;
    }

    public void setCurrentStatue(PlayerStatue currentStatue) {
        this.currentStatue = currentStatue;
    }
    
    public abstract PlayerMove GetPlayerMove();
    
}

