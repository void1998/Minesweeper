/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Viewpackage;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author void
 */
public class GUIView1 {
    //setting Window
    public static Stage window ;
    public static void initWindow( )
    {
        window = new Stage();
        window.setTitle("Minesweeper");
        window.setMinWidth(1000);
        window.setMinHeight(600);
        //window.setMaximized(true);
        window.setMaxWidth(1000);
        window.setMaxHeight(600);
    }
    //Main Menu
    public static void mainMenu()
    {
        VBox layout = setMainMenuLayout();
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public static VBox setMainMenuLayout()
    {
        VBox container = new VBox();
        Label mainLabel = new Label("Main Menu");
        Button startGameButton = new Button("NewGame");
        Button optionsButton = new Button("Options");
        Button exitButton = new Button("Exit");
        container.getChildren().addAll(mainLabel,startGameButton,optionsButton,exitButton);
        setMainMenuActions(startGameButton,optionsButton,exitButton);
        return container;
    }
    public static void setMainMenuActions(Button startGameButton,Button optionsButton,Button exitButton)
    {
        startGameButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    
                }
                
            });
        ////////////////////////////////////////
        optionsButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    optionsMenu();
                }
                
            });
        /////////////////////////////////////
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    window.close();
                }
                
            });
    }
    //Options Menu
    public static void optionsMenu()
    {
        VBox layout = setOptionsMenuLayout();
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public static VBox setOptionsMenuLayout()
    {
        VBox mainContainer = new VBox();
        //
        HBox subContainer1 = new HBox();
        Label mainLabel = new Label("Options");
        subContainer1.getChildren().addAll(mainLabel);
        //Players Number Input
        HBox subContainer2 = new HBox();
        Label playersNumLabel = new Label("Number of players:");
        TextField playersNumTextArea = new TextField();
        subContainer2.getChildren().addAll(playersNumLabel,playersNumTextArea);
        //Grid Height Input
        HBox subContainer3 = new HBox();
        Label heightLabel = new Label("MineSweeper Width:");
        TextField heightTextArea = new TextField();
        subContainer3.getChildren().addAll(heightLabel,heightTextArea);
        //Grid Width Inpu
        HBox subContainer4 = new HBox();
        Label widthLabel = new Label("MineSweeper Height :");
        TextField widthTextArea = new TextField();
        subContainer4.getChildren().addAll(widthLabel,widthTextArea);
        //Submit & back to main menu buttons
        HBox subContainer5 = new HBox();
        Button submit = new Button("Apply changes and start game:");
        Button back = new Button("Back to main menu");
        //adding actions to buttons
        setOptionsMenuActions(submit,back);
        subContainer5.getChildren().addAll(submit,back);
        //Adding all subContainers to the main
        mainContainer.getChildren().addAll(subContainer1,subContainer2,subContainer3,subContainer4,subContainer5);
        //return statment
        return mainContainer;

        
    }
    public static void setOptionsMenuActions(Button submit,Button back)
    {
        //submit button actions    
                submit.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    
                }
                
            });
        //back button actions
                back.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    mainMenu();
                }
                
            });
    }
    
}
