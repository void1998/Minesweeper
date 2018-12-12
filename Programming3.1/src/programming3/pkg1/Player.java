package programming3.pkg1;

import java.io.Serializable;
import java.util.List;
import programming3.pkg1.NumiricScore;
import programming3.pkg1.Score;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.ShieldPackage.Shield;

public abstract class Player implements Serializable{

    protected String name;
    protected Score currentScore;
    protected PlayerStatue currentStatue;
    protected List<Shield> shields;
    protected int playerNumber;
    
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

    public List<Shield> getShields() {
        return shields;
    }
    public int getPlayerNumber()
    
    {
        return playerNumber;
    }
    
    
    
    
    public abstract PlayerMove GetPlayerMove();



   
}

