package programming3.pkg1.Mainpackage;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import programming3.pkg1.Game;
import programming3.pkg1.Grid;
import programming3.pkg1.NormalGame;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.UtilPackage.Constants;
import programming3.pkg1.UtilPackage.GridInterface;
import programming3.pkg1.Timerhelperspackage.TimerHelper;
import programming3.pkg1.Viewpackage.ConsoleView;
import programming3.pkg1.Viewpackage.GUIView;
import programming3.pkg1.Viewpackage.View;

public class Programming31 extends Application{

    /**
     * @param args the command line arguments
     */
   static Grid myGrid;
    public static void main(String[] args)
    {
        
        Game myGame = new NormalGame();
        myGame.initGame(1,1);
        myGrid = new Grid(19,19,myGame);
        View myView = new ConsoleView();
        myView.Draw(myGrid.getSquares());
        PlayerMove temp;
        temp =  myGrid.getCurrentGame().getCurrentPlayer().GetPlayerMove();
        myGrid.initGrid(temp.getSquare());
        myGrid.AcceptMove(temp);
        for(int i=1;i<20;i++)
        {
            for(int j=1;j<20;j++)
            {
                System.out.print(myGrid.getSquares()[i][j].getSquareStatus().getValue());
            }
            System.out.println("");
        }
        System.out.println("");
        myView.Draw(myGrid.getSquares());
        
        // object for timer
         Timer timer=new Timer();
         GridInterface gi=new GridInterface() {
            @Override
            public Grid getGrid() {
                 return myGrid;//To change body of generated methods, choose Tools | Templates.
            }
        };
         TimerTask task = new TimerHelper((gi));
        while(myGrid.getCurrentGame().checkGame(myGrid).equals(Constants.ON_GOING))
        {
           System.out.printf("Player: %s",myGame.getCurrentPlayer().getName());
           System.out.println("");
           System.out.printf("The score is:%d", myGame.getCurrentPlayer().getCurrentScore().getRealScore());
           System.out.println("");
           timer.schedule(task, 0, 8000);
          /* temp =  myGrid.getCurrentGame().getCurrentPlayer().GetPlayerMove();
           myGrid.AcceptMove(temp);*/
           myView.Draw(myGrid.getSquares());
        }
        if(myGrid.getCurrentGame().checkGame(myGrid).equals(Constants.LOSER))
        {
            System.out.println("Game Over");
            System.out.println("The scores:");
            for(int i=0;i<myGame.players.size();i++)
            {
                System.out.print(myGame.players.get(i).getName());
                System.out.print(": ");
                System.out.println(myGame.players.get(i).getCurrentScore().getRealScore());
            }
        }
        else if(myGrid.getCurrentGame().checkGame(myGrid).equals(Constants.WINNER))
        {
            System.out.println("You finished the grid.");
            System.out.println("The scores:");
            for(int i=0;i<myGame.players.size();i++)
            {
                System.out.print(myGame.players.get(i).getName());
                System.out.print(": ");
                System.out.println(myGame.players.get(i).getCurrentScore().getRealScore());
            }
        }
      //  launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //inti window
         GUIView.initWindow();
         GUIView.initGraphics(GUIView.initMenu());
        //
        

        
    }
    
    }
    

