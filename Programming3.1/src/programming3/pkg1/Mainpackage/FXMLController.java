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
        //Getting timer label
        HBox thirdFather = (HBox)secondFather.getChildren().get(3);
        Label TurnTimer = (Label)thirdFather.getChildren().get(1);
        //getting player 1 labels
        HBox player1HBox = (HBox)secondFather.getChildren().get(0);
        VBox player1VBox = (VBox)player1HBox.getChildren().get(1);
        Label player1NameLabel = (Label)player1VBox.getChildren().get(0);
        Label player1ScoreLabel = (Label)player1VBox.getChildren().get(1);
        Label player1ShieldsLabel = (Label)player1VBox.getChildren().get(2);
        //getting player2 labels
        HBox player2HBox = (HBox)secondFather.getChildren().get(1);
        VBox player2VBox = (VBox)player2HBox.getChildren().get(1);
        Label player2NameLabel = (Label)player2VBox.getChildren().get(0);
        Label player2ScoreLabel = (Label)player2VBox.getChildren().get(1);
        Label player2ShieldsLabel = (Label)player2VBox.getChildren().get(2);
        //getting auto Player Score
        HBox autoPlayerHBox = (HBox)secondFather.getChildren().get(2);
        VBox autoPlayerVBox = (VBox)autoPlayerHBox.getChildren().get(1);
        Label autoPlayerScoreLabel = (Label)autoPlayerVBox.getChildren().get(1);
        //getting GameStatus Label
        HBox GameStatusContainer = (HBox)root.getChildren().get(2); 
        Label GameStatus = (Label)GameStatusContainer.getChildren().get(0);
        //
        HBox GridContainer = new HBox();
        GridContainer.setAlignment(Pos.CENTER);
        GridContainer.setPrefHeight(245.0);
        GridContainer.setPrefWidth(680.0);
        GridPane realGrid = new GridPane();
       Helper.initGridPane(realGrid,TurnTimer,player1NameLabel,player2NameLabel,
               player1ScoreLabel,player2ScoreLabel,player1ShieldsLabel
               ,player2ShieldsLabel,GameStatus,autoPlayerScoreLabel);
       
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
