package programming3.pkg1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Programming31 extends Application  {

    /**
     * @param args the command line arguments
     */
    public void applyGUI(Stage window,Button buttons[][],Game myGame,Grid grid)
    {
        for(int i=0;i<19; i++)
            for(int j=0;j<19; j++)
            {
                final int x;
                x = i+1;
                final int y;
                y = j+1;
                 
                buttons[i][j].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                       PlayerMove temp2 = new PlayerMove();
                        if(event.getButton() == MouseButton.PRIMARY)
                        {
                            
                            temp2.getMove().setType(Constants.REVEAL);
                            temp2.getSquare().setX(x);
                            temp2.getSquare().setY(y);
                            temp2.setPlayer(myGame.getCurrentPlayer());
                            grid.AcceptMove(temp2);
                            if(grid.checkGame())
                            {
                                
                                Scene fscene = new Scene(GUIView.finishGame());
                                window.setScene(fscene);
                                window.show();
                            }
                            else
                            GUIView.interact(buttons, grid.getSquares());
                        
                        }
                        else
                        if(event.getButton() == MouseButton.SECONDARY)
                        {
                            temp2.getMove().setType(Constants.MARK);
                            temp2.getSquare().setX(x);
                            temp2.getSquare().setY(y);
                            temp2.setPlayer(myGame.getCurrentPlayer());
                            grid.AcceptMove(temp2);
                            GUIView.interact(buttons, grid.getSquares());
                        
                        }

                    }
                });
              
                
            }
    }
    public void init(Stage window,Game game,int playersNum,int height,int width)
    {
         //init game
        Game myGame = game;
        myGame.initGame(playersNum);
        Grid grid = new Grid(width,height,myGame);
        
        PlayerMove temp = new PlayerMove();
        grid.initGrid(temp.getSquare()); 
        //
        Button buttons[][] = GUIView.initGrid(height,width);
        Scene qscene = new Scene(GUIView.initLayout(buttons,height,width));
        window.setScene(qscene);
        applyGUI(window,buttons,myGame,grid);
    }
    public static void main(String[] args)
    {
        /*Game myGame = new NormalGame();
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
        */
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //inti window
        Stage window = GUIView.initWindow(primaryStage);
        //
        
        //main menu
        Scene ascene = new Scene(GUIView.initMenu(window));
        window.setScene(ascene);
        window.show();
        //
        
    }
    
    }
    

