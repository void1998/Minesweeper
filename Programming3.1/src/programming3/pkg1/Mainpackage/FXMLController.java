/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Mainpackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import programming3.pkg1.Game;
import programming3.pkg1.Grid;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.ScoreGame;
import programming3.pkg1.Viewpackage.NewGUI;

/**
 * FXML Controller class
 *
 * @author void
 */
public class FXMLController  {
    
    public Stage primaryStage;
    public NewGUI Helper = new NewGUI();
    

    public void GO() throws IOException
    {
        primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        this.primaryStage.setTitle("MineSweeper");
        
        this.primaryStage.setScene(new Scene(root,680,530));
        
        this.primaryStage.setMinHeight(530);
        this.primaryStage.setMinWidth(693);
        this.primaryStage.setMaxHeight(810);
        this.primaryStage.setMaxWidth(693);
        
        this.primaryStage.show();
    }
    /**
     * Initializes the controller class.
     */
    //Exit button action
    public void exit(ActionEvent event)
    {
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.close();
    }
    
    //settings button action
    public void settings(ActionEvent event) throws IOException
    {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        
        window.setScene(new Scene(root,680,530));
        
        window.show();
    }
    
    //Score board button action
    public void scoreBoard(ActionEvent event) throws IOException
    {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("ScoreBoard.fxml"));
        
        window.setScene(new Scene(root,680,530));
        
        window.show();
    }
        
    //Load button action
    public void LoadMenu(ActionEvent event) throws IOException
    {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("LoadMenu.fxml"));
        
        window.setScene(new Scene(root,680,530));
        
        window.show();
    }
    
    //Game button action
    public void Game(ActionEvent event) throws IOException
    {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        VBox root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        //
        HBox secondFather = (HBox)root.getChildren().get(1);
        HBox thirdFather = (HBox)secondFather.getChildren().get(3);
        Label TurnTimer = (Label)thirdFather.getChildren().get(1);
        NewGUI.Time.getTtTextProperty().addListener((v,oldValue,newValue) -> {
                TurnTimer.setText(newValue);
                });
        //
        HBox GridContainer = new HBox();
        GridContainer.setAlignment(Pos.CENTER);
        GridContainer.setPrefHeight(245.0);
        GridContainer.setPrefWidth(680.0);

       GridPane realGrid = Helper.initGridPane();
       
       GridContainer.getChildren().add(realGrid);
      
       root.getChildren().add(2,GridContainer);
       
        window.setScene(new Scene(root)); 
        window.show();
    }
    
    //Quick load button action
    public void QuickLoad(ActionEvent event) throws IOException
    {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        
        window.setScene(new Scene(root,680,530));
        
        window.show();
    }
}
