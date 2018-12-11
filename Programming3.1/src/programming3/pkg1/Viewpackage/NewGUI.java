/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Viewpackage;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import programming3.pkg1.Game;
import programming3.pkg1.Grid;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.ScoreGame;
import programming3.pkg1.Square;
import programming3.pkg1.Timerhelperspackage.MoveTimer;
import programming3.pkg1.UtilPackage.Constants;
//import static programming3.pkg1.Viewpackage.GUIView.Time;

/**
 *
 * @author void
 */
public class NewGUI {
    static public  MoveTimer Time = new MoveTimer();
    //Game Logic initializing method
    
    public Grid InitGridLogicGUI()
    {
        //TODO maitain to take the first square then make the initializing process
        //init game
        Game myGame = new ScoreGame();
        myGame.initGame(1, 0, 2);
        Grid grid = new Grid(20, 20, myGame, 10);
        PlayerMove temp = new PlayerMove();
        grid.initGrid(temp.getSquare());
        return grid;
    }
    
    //Init Grid Cells
    public Button[][] initCells()
    {
        Button button[][] = new Button[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                button[i][j] = new Button();
                GridPane.setConstraints(button[i][j], i + 1, j + 1);
                button[i][j].getStyleClass().add("empty-closed");
                button[i][j].setMinWidth(30);
                button[i][j].setMinHeight(30);
                button[i][j].setMaxWidth(30);
                button[i][j].setMaxHeight(30);
            }
        }
        return button;
    }
    
    //Init the GridPane of the minesweeper
    public GridPane initGridPane()
    {
        GridPane Grid = new GridPane();
        Button[][] button = initCells();
        Grid grid = InitGridLogicGUI();
        CellsActions(button, grid);
        Grid.setPadding(new Insets(0, 0, 0, 0));
        Grid.setVgap(0);
        Grid.setHgap(0);
        Grid.setAlignment(Pos.CENTER);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Grid.getChildren().add(button[i][j]);
            }
        }
        return Grid;
    }
    //Updating cells grphics depending on the square status
    public static void updateCells(Button[][] myButton,Square squares[][],int height,int width){
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
            {
        myButton[i][j].getStyleClass().clear();
        if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.CLOSED))
        {
           myButton[i][j].getStyleClass().add("empty-closed");
        }
        else if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.MARKED))
        {
            myButton[i][j].getStyleClass().add("marked-closed");
        }
        else if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.OPENED_EMPTY))
        {
            myButton[i][j].getStyleClass().add("opened");
        }
        else if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.OPENED_NUMBER))
        {
            myButton[i][j].getStyleClass().add("opened");
            Integer temp = squares[i+1][j+1].getSquareStatus().getValue();
            String url = "'programming3/pkg1/Mainpackage/"+temp+".png'";
            myButton[i][j].setStyle("-fx-background-image: url("+url+");-fx-background-size:30px 30px;");
            
        }
        else if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.OPENED_MINE))
        {
            myButton[i][j].getStyleClass().add("mine-opened");
        }
            }
    }
    //Cells Events Listener
    public void CellsActions(Button myButtons[][],Grid grid)
    {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                final int x;
                x = i + 1;
                final int y;
                y = j + 1;

                myButtons[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        PlayerMove temp2 = new PlayerMove();
                        if (event.getButton() == MouseButton.PRIMARY) {
                            temp2.getMove().setType(Constants.REVEAL);
                            temp2.getSquare().setX(x);
                            temp2.getSquare().setY(y);
                            temp2.setPlayer(grid.getCurrentGame().getCurrentPlayer());
                            grid.AcceptMove(temp2);
                            grid.getCurrentGame().getCurrentRules().DecideNextPlayer(grid.getCurrentGame()
                                    .getCurrentPlayer());
                            Time.stop();

                            Time = new MoveTimer() {

                                @Override
                                public void check() {
                                    grid.ss();
                                    //System.out.println(""+grid.getCurrentGame().getCurrentPlayer().getName());
                                    //System.out.println("SCORE:"+grid.getCurrentGame().getCurrentPlayer().getCurrentScore().getRealScore());
                                    //System.out.println("Shields number:"+grid.getCurrentGame().getCurrentPlayer().getShields().size());
                                }
                            };
                            Time.start();
                            updateCells(myButtons,grid.getSquares(),20,20);
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            temp2.getMove().setType(Constants.MARK);
                            temp2.getSquare().setX(x);
                            temp2.getSquare().setY(y);
                            temp2.setPlayer(grid.getCurrentGame().getCurrentPlayer());
                            grid.AcceptMove(temp2);
                            updateCells(myButtons,grid.getSquares(),20,20);

                        }
                    }
                });

            }
        }
    }
    
}
