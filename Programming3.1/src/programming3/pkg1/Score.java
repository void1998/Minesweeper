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
    /**
     * author: void;
     * Date:2/11/2018
     * controlling the way of setting the score:(numeric way, alphabetical way.....)
     */
 
    public abstract Object scoreType();
}
