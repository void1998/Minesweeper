package programming3.pkg1;

import programming3.pkg1.NumiricScore;
import programming3.pkg1.Score;
import programming3.pkg1.PlayerMove;

public abstract class Player {

    protected String name;
    protected Score currentScore;
    protected PlayerStatue currentStatue;
    
    Player()
    {
        this.name = "Player";
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

