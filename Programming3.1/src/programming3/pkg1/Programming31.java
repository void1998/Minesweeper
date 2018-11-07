package programming3.pkg1;

import javafx.application.Application;
import javafx.stage.Stage;

public class Programming31 extends Application  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Game myGame = new NormalGame();
        myGame.initGame(2);
        Grid myGrid = new Grid(19,19,myGame);
        View myView = new ConsoleView();
        myView.Draw(myGrid.getSquares());
        PlayerMove temp = new PlayerMove();
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
        while(!myGrid.checkGame())
        {
           System.out.println("");
           System.out.printf("The score is:%d", myGame.getCurrentPlayer().getCurrentScore().getRealScore());
           System.out.println("");
           temp =  myGrid.getCurrentGame().getCurrentPlayer().GetPlayerMove();
           myGrid.AcceptMove(temp);
           myView.Draw(myGrid.getSquares());
      
        }
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
