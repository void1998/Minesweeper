package programming3.pkg1;

import java.io.Serializable;

public abstract class Score implements Serializable{
    int realScore;
    Object score;
    Score()
    {
        this.realScore = 0;
        this.score = 0;
    }
    public int getRealScore() {
        return realScore;
    }
    public void setRealScore(int realScore) {
        this.realScore = realScore;
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
