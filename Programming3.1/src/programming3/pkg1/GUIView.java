
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
                Grid.getChildren().add(button[i][j]);
        //
        return Grid;
        
    }
    
    public static void interact(Button button)
    {
        
    }
    

}
