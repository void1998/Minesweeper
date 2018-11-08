
package programming3.pkg1;

import java.util.List;
import java.util.regex.Pattern;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author void
 */
public class GUIView  {
    
    public static Stage window ;
    public static void initWindow( )
    {
        window = new Stage();
        window.setTitle("Minesweeper");
        window.setMinWidth(600);
        window.setMinHeight(600);
        //window.setMaxWidth(800);
        //window.setMaxHeight(800);
    }
    
    public static Button[][] initGrid(int height,int width)
    {
        Button button[][] = new Button[height][width];
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                button[i][j] = new Button();
                GridPane.setConstraints(button[i][j],i,j);
                button[i][j].setStyle("-fx-background-color: gray;-fx-border-color:black;");
                button[i][j].setMinWidth(30);
                button[i][j].setMinHeight(30);
            }
        }
        return button;
    }
    

    
    public static void interact(Button buttons[][],Square squares[][],int height,int width)
    {
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.CLOSED))
                buttons[i][j].setStyle("-fx-background-color: gray;-fx-border-color:black;");
                else if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.MARKED))
                {
                    buttons[i][j].setStyle("-fx-background-color: orange;-fx-border-color:black;");
                }
                else if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.OPENED_EMPTY))
                {
                    buttons[i][j].setStyle("-fx-background-color: white;-fx-border-color:black;");
                }
                else if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.OPENED_MINE))
                {
                    buttons[i][j].setStyle("-fx-background-color: black;-fx-border-color:black;");
                }
                else if(squares[i+1][j+1].getSquareStatus().getStatus().equals(Constants.OPENED_NUMBER))
                {
                    buttons[i][j].setStyle("-fx-background-color: red;-fx-border-color:black;");
                    Integer temp = squares[i+1][j+1].getSquareStatus().getValue();
                    String value = temp.toString();
                    buttons[i][j].setText(value);
                }

            }
        }
                
        
    }
    
        public static GridPane initLayout(Button button[][],int height,int width)
    {
        GridPane Grid = new GridPane();
        Grid.setPadding(new Insets(0,0,0,0));
        Grid.setVgap(0);
        Grid.setHgap(0);
        Grid.setAlignment(Pos.CENTER);
        //
        for(int i=0;i<width;i++)
            for(int j=0;j<height;j++)
            {
                Grid.getChildren().add(button[i][j]);
            }
                
        //
        return Grid;
        
    }
        
    static GridPane finishGame(String message) {
        GridPane Grid = new GridPane();
        Grid.setPadding(new Insets(0,0,0,0));
        Grid.setVgap(0);
        Grid.setHgap(0);
        Grid.setAlignment(Pos.CENTER);
        Label statment = new Label(message);
        //

                Grid.getChildren().add(statment);
            
                
        //
        return Grid;
    }
    
        public static GridPane initMenu()
    {
        GridPane Grid = new GridPane();
        Grid.setPadding(new Insets(10,10,10,10));
        Grid.setVgap(10);
        Grid.setHgap(10);
        Grid.setAlignment(Pos.CENTER);
        //init buttons
        Button start = new Button("Start Game");
        Button options = new Button("Options");
        Button exit = new Button("Exit");
        

        //
        Button buttons[][] = GUIView.initGrid(19,19);
        mainMenuButtonsActions(start,options,exit,window,buttons);
        Grid.getChildren().addAll(start,options,exit);
         initButton(start,0,1);
         initButton(options,0,2);
         initButton(exit,0,3);
                
        //
        return Grid;
        
    }
        public static void initButton(Button button,int x,int y)
        {
            GridPane.setConstraints(button,x,y);
            button.setStyle("-fx-background-color: blue;-fx-border-color:black;-fx-color: red;");
            button.setMinWidth(100);
            button.setMinHeight(40);
            
        }
        public static void mainMenuButtonsActions(Button start,Button options,Button exit,Stage window,Button buttons[][])
        {
            start.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    initGame(new NormalGame(),1,16,16);
                }
                
            });
            //
            options.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    initGraphics(initOptions());
                }
                
            });
            //
            exit.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    window.close();
                }
                
            });
        }
        
        public static GridPane initOptions()
        {
            GridPane Grid = new GridPane();
            Grid.setPadding(new Insets(100,200,250,250));
            Grid.setVgap(30);
            Grid.setHgap(30);
            Grid.setAlignment(Pos.CENTER);
            //init buttons
            Button submit = new Button("Apply changes and start game");
            GridPane.setConstraints(submit,0,6);
            Label playersNumLabel = new Label("Number of players");
            GridPane.setConstraints(playersNumLabel,0,0);
            TextField playersNumTextArea = new TextField();
            GridPane.setConstraints(playersNumTextArea,0,1);
            Label heightLabel = new Label("MineSweeper height");
            GridPane.setConstraints(heightLabel,0,2);
            TextField heightTextArea = new TextField();
            GridPane.setConstraints(heightTextArea,0,3);
            Label widthLabel = new Label("MineSweeper Width");
            GridPane.setConstraints(widthLabel,0,4);
            TextField widthTextArea = new TextField();
            GridPane.setConstraints(widthTextArea,0,5);
            Grid.getChildren().addAll(submit,playersNumLabel,playersNumTextArea,heightLabel,heightTextArea,widthLabel,widthTextArea);
            optionsButtonActions(submit,playersNumTextArea,heightTextArea,widthTextArea);
            return Grid;
        }
        
    private static void optionsButtonActions(Button submit,TextField playersNumTextArea,TextField heightTextArea,TextField widthTextArea) 
    {
        
        submit.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try{
                String pNum = playersNumTextArea.getText();
                String hNum = heightTextArea.getText();
                String wNum = widthTextArea.getText();
                if(Pattern.compile("[1-2]").matcher(pNum).matches()
                        &&Pattern.compile("[5-9]|1[0-9]|2[0-5]").matcher(hNum).matches()
                        &&Pattern.compile("[5-9]|1[0-9]|2[0-5]").matcher(wNum).matches())
                initGame(new NormalGame(),Integer.parseInt(pNum),Integer.parseInt(hNum),Integer.parseInt(wNum));
                throw new Exception("INVALID INPUT");
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                
                
            }

        });
        
    }
    public static void applyGUI(Button buttons[][],Game myGame,Grid grid)
    {
        for(int i=0;i<grid.getWidth(); i++)
            for(int j=0;j<grid.getHieght(); j++)
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
                            if(myGame.checkGame(grid)== Constants.WINNER)
                            {
                                
                                Scene fscene = new Scene(GUIView.finishGame("Grid finished correctly"));
                                window.setScene(fscene);
                                window.show();
                            }
                            else
                                if(myGame.checkGame(grid)== Constants.LOSER)
                                {
                                    Scene fscene = new Scene(GUIView.finishGame("game finished"));
                                    window.setScene(fscene);
                                    window.show();
                                }
                            else
                            interact(buttons, grid.getSquares(),grid.getHieght(),grid.getWidth());
                        
                        }
                        else
                        if(event.getButton() == MouseButton.SECONDARY)
                        {
                            temp2.getMove().setType(Constants.MARK);
                            temp2.getSquare().setX(x);
                            temp2.getSquare().setY(y);
                            temp2.setPlayer(myGame.getCurrentPlayer());
                            grid.AcceptMove(temp2);
                            interact(buttons, grid.getSquares(),grid.getHieght(),grid.getWidth());
                        
                        }

                    }
                });
              
                
            }
    }
    public static void initGame(Game game,int playersNum,int height,int width)
    {
         //init game
        Game myGame = game;
        myGame.initGame(playersNum);
        Grid grid = new Grid(width,height,myGame);
        
        PlayerMove temp = new PlayerMove();
        grid.initGrid(temp.getSquare()); 
        //
        Button buttons[][] = GUIView.initGrid(height,width);
        initGraphics(initLayout(buttons,height,width));
        applyGUI(buttons,myGame,grid);
    }

    public static void initGraphics(GridPane layout)
    {
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
        

}
