
package programming3.pkg1;

import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author void
 */
public class GUIView  {
    
    
    public static Stage initWindow(Stage primaryStage)
    {
        Stage window;
        window = primaryStage;
        window.setTitle("Minesweeper");
        window.setMinWidth(600);
        window.setMinHeight(600);
        //window.setMaxWidth(800);
        //window.setMaxHeight(800);
        return window;
    }
    
    public static Button[][] initGrid(int height,int width)
    {
        Button button[][] = new Button[height][width];
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                button[i][j] = new Button();
                GridPane.setConstraints(button[i][j],i,j);
                button[i][j].setStyle("-fx-background-color: gray;-fx-border-color:black;");
                button[i][j].setMinWidth(40);
                button[i][j].setMinHeight(40);
            }
        }
        return button;
    }
    

    
    public static void interact(Button buttons[][],Square squares[][])
    {
        for(int i=0;i<19;i++)
        {
            for(int j=0;j<19;j++)
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
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
            {
                Grid.getChildren().add(button[i][j]);
            }
                
        //
        return Grid;
        
    }
        
    static GridPane finishGame() {
        GridPane Grid = new GridPane();
        Grid.setPadding(new Insets(0,0,0,0));
        Grid.setVgap(0);
        Grid.setHgap(0);
        Grid.setAlignment(Pos.CENTER);
        Label statment = new Label("Game Finished");
        //

                Grid.getChildren().add(statment);
            
                
        //
        return Grid;
    }
    
        public static GridPane initMenu(Stage window)
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
                    GridPane myGrid = GUIView.initLayout(buttons, 19, 19);
                    Scene scene = new Scene(myGrid);
                    window.setScene(scene);
                    window.show();
                }
                
            });
            //
            options.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("options");
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
        
        public static void initOptions()
        {
            GridPane Grid = new GridPane();
            Grid.setPadding(new Insets(10,10,10,10));
            Grid.setVgap(10);
            Grid.setHgap(10);
            Grid.setAlignment(Pos.CENTER);
            //init buttons
            Button submit = new Button("Apply changes and start game");
            Label playersNumLabel = new Label("Number of players");
            TextArea playersNumTextArea = new TextArea();
            Label heightLabel = new Label("MineSweeper height");
            TextArea heightTextArea = new TextArea();
            Label widthLabel = new Label("MineSweeper Width");
            TextArea widthTextArea = new TextArea();


            //
            optionsButtonActions(submit,playersNumTextArea,heightTextArea,widthTextArea);
            Grid.getChildren().addAll(start,options,exit);
             initButton(start,0,1);
             initButton(options,0,2);
             initButton(exit,0,3);

            //
            return Grid;
        }

    private static void optionsButtonActions(Button submit,TextArea playersNumTextArea,TextArea heightTextArea,TextArea widthTextArea) 
    {
        
        submit.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String num = playersNumTextArea.getText();
                playerNum = Integer.parseInt(num);
                String height = heightTextArea.getText();
                String width = widthTextArea.getText();
                init();//CONTINUE frOM HERE
            }

        });
        
    }
    
        

}
