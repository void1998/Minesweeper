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
       while(true)
           {
             try {
                  System.out.println("to mark square please enter 1, to reveal square please enter 2 ");
                  String Insert = reader.readLine();
                  if(Insert.equals("1") && Insert.equals("2"))
                    throw new IllegalGameMove("illegal move!");
                  currentType.setType(Insert);
                  break;
            } catch (IllegalGameMove ex) {
            System.out.println(ex);
        }   catch (IOException ex) {
                Logger.getLogger(ConsolPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
       
       
       while(true)
       {
        try {
            System.out.println("Please enter the letter of the column");
            String Insert = reader.readLine();
            int insert = Integer.parseInt(Insert);
             if(insert>19 && insert<1)
                throw new IllegalSquareName("illegal square name!");
            square.setX(insert);
            break;
        }  
        catch (IllegalSquareName ex) {
             System.out.println(ex);  
        }catch (IOException ex) {
            Logger.getLogger(ConsolePlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       
       while(true)
       {
        try {
            System.out.println("please enter the number of the row");
            String Insert = reader.readLine();
            int insert = Integer.parseInt(Insert);
            if(insert>19 && insert<1)
                throw new IllegalSquareName("illegal square name!");
            square.setY(insert);
            break;
            } 
        catch (IllegalSquareName ex) {
             System.out.println(ex);            }
        catch (IOException ex) {
             Logger.getLogger(ConsolePlayer.class.getName()).log(Level.SEVERE, null, ex);
            } 
       }
        currentMove.setPlayer(this);
        currentMove.setSquare(square);
        currentMove.setMove(currentType);
        return currentMove;
    }
}
