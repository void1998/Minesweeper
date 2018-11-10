/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1;

import programming3.pkg1.UtilPackage.Constants;
import programming3.pkg1.Score;
import programming3.pkg1.ExceptionPackage.IllegalSquareName;
import programming3.pkg1.ExceptionPackage.IllegalGameMove;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import programming3.pkg1.Movespackage.MoveType;
import programming3.pkg1.PlayerMove;

/**
 *
 * @author void
 */
public class ConsolePlayer extends Player {

    ConsolePlayer(Score score, PlayerStatue playerStatue, String name) {
        this.name = name;
        this.currentScore = score;
        this.currentStatue = playerStatue;
    }
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
            switch (Insert) {
                case "1":
                    currentType.setType(Constants.MARK);
                    break;
                case "2":
                    currentType.setType(Constants.REVEAL);
                    break;
                default:
                    throw new IllegalGameMove("not available please try again");
            }
            break;
        }
         catch (IllegalGameMove ex) {
            System.out.println(ex);
        }  
        catch (IOException ex) {
            Logger.getLogger(ConsolePlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        while(true)
        {
        try {
            System.out.println("Please enter the letter of the column");
            String Insert = reader.readLine();
           // int insert = Integer.parseInt(Insert);
             // if(insert<20 && insert>0)
             if(Pattern.compile("[0-9]|1[0-9]").matcher(Insert).matches())
              { 
                  int insert = Integer.parseInt(Insert);
                  square.setX(insert);
                  break;
              }
            throw new IllegalSquareName("illegal square name!");
        }
        catch (IllegalSquareName ex) {
             System.out.println(ex);  
                    
        }
        catch (IOException ex) {
            Logger.getLogger(ConsolePlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        while(true)
        {
        try {
            System.out.println("please enter the number of the row");
            String Insert = reader.readLine();
            //int insert = Integer.parseInt(Insert);
            //if(insert<20 && insert>0)
            if(Pattern.compile("[0-9]|1[0-9]").matcher(Insert).matches())
            {
            int insert = Integer.parseInt(Insert);
                square.setY(insert);
                break;
            }
            throw new IllegalSquareName("illegal square name!");
            } 
        catch (IllegalSquareName ex) {
             System.out.println(ex);     
        }
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


