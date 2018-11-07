
package programming3.pkg1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        window.setMaxWidth(600);
        window.setMaxHeight(600);
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
            }
        }
        return button;
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
    
    public static void interact(Button buttons[][],Square squares[][])
    {
        for(int i=0;i<19;i++)
        {
            for(int j=0;j<19;j++)
            {
                if(squares[i][j].getSquareStatus().getStatus().equals(Constants.CLOSED))
                buttons[i][j].setStyle("-fx-background-color: gray;-fx-border-color:black;");
                else if(squares[i][j].getSquareStatus().getStatus().equals(Constants.MARKED))
                {
                    buttons[i][j].setStyle("-fx-background-color: orange;-fx-border-color:black;");
                }
                else if(squares[i][j].getSquareStatus().getStatus().equals(Constants.OPENED_EMPTY))
                {
                    buttons[i][j].setStyle("-fx-background-color: white;-fx-border-color:black;");
                }
                else if(squares[i][j].getSquareStatus().getStatus().equals(Constants.OPENED_MINE))
                {
                    buttons[i][j].setStyle("-fx-background-color: black;-fx-border-color:black;");
                }
                else if(squares[i][j].getSquareStatus().getStatus().equals(Constants.OPENED_NUMBER))
                {
                    buttons[i][j].setStyle("-fx-background-color: red;-fx-border-color:black;");
                }

            }
        }
                
        
    }
    

}
