package programming3.pkg1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author void
 */
public abstract class Player {

    protected String name;
    protected Score currentScore;
    
    Player()
    {
        this.name = "Player";
        this.currentScore = new NumiricScore();
    }
    public abstract PlayerMove GetPlayerMove();
    
}

