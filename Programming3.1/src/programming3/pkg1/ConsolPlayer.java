package programming3.pkg1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConsolPlayer extends Player {
    @Override
    public PlayerMove GetPlayerMove()
    {
        PlayerMove currentMove = new PlayerMove();
        Square square = new Square();
        MoveType currentType = new MoveType();
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        try {
            System.out.println("to mark square please enter 1, to reveal square please enter 2 ");
            String Insert = reader.readLine();
            currentType.setType(Insert);
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
        currentMove.setPlayer(this);
        currentMove.setSquare(square);
        currentMove.setMove(currentType);
        return currentMove;
    }
}
