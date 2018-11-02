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
        this.Score = new NormalScore();
    }
    public abstract PlayerMove GetPlayerMove();
    
}

public class ConsolePlayer extends Player {
    public PlayerMove GetPlayerMove()
    {
        PlayerMove currentMove = new PlayerMove;
        Square square = new Square();
        MoveType currentType = new MoveType();
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        try {
            System.out.println("to mark square please enter 1, to reveal square please enter 2 ");
            String Insert = reader.readLine();
            int insert = Integer.parseInt(Insert);
            currentType.setMoveType(insert);
        } catch (IOException ex) {
            Logger.getLogger(ConsolePlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("Please enter the letter of the column");
            String Insert = reader.readLine();
            int insert = Integer.parseInt(Insert);
            square.setX(insert);
        } catch (IOException ex) {
            Logger.getLogger(ConsolePlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("please enter the number of the row");
            String Insert = reader.readLine();
            int insert = Integer.parseInt(Insert);
            square.setY(insert);
            } 
        catch (IOException ex) {
             Logger.getLogger(ConsolePlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        currentMove.setPlayer(this.name);
        currentMove.setSquare(square);
        currentMove.setMoveType(currentType);
        return currentMove;
    }
}
