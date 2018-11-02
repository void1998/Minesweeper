package programming3.pkg1;

public abstract class Score {
    Object score;
    Score()
    {
        
    }
    public Object getScore()
    {
        return this.score;
    }
    public void setScore(Object score)
    {
        this.score = score;
    }
    public abstract Object scoreType();
}
