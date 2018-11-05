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
        myGame.initGame(1);
        Grid myGrid = new Grid(19,19,myGame);
        View myView = new ConsoleView();
        myView.Draw(myGrid.getSquares());
        PlayerMove temp = new PlayerMove();
        temp =  myGrid.getCurrentGame().getCurrentPlayer().GetPlayerMove();
        myGrid.initGrid(temp.getSquare());
        myGrid.AcceptMove(temp);
        myView.Draw(myGrid.getSquares());
        while(!myGrid.checkGame())
        {
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
