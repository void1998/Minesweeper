package programming3.pkg1;
public abstract class Player {

    protected String name;
    protected Score currentScore;
    
    Player()
    {
        this.name = "Player";
        this.currentScore = new NumiricScore();
    }

    public Score getCurrentScore() {
        return currentScore;
    }
    
    public abstract PlayerMove GetPlayerMove();
    
}

