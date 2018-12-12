/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Mainpackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import programming3.pkg1.Floders.Folders;
import programming3.pkg1.Timerhelperspackage.MoveTimer;
import programming3.pkg1.Viewpackage.NewGUI;

/**
 * FXML Controller class
 *
 * @author void
 */
public class GameController implements Initializable {
    
    @FXML
    public VBox root;

    

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    //Menu Bar Actions
        //File Menu
            //Save 
            public void save(ActionEvent event)
            {
        try {
            Folders.save(NewGUI.grid);
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            //Load
            public void load(ActionEvent event)
            {
        try {
            NewGUI.grid = Folders.readFile("45.bin");
            
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            //Exit
            public void exit(ActionEvent event)
            {
                Stage window = (Stage)((Node)root).getScene().getWindow();
                window.close();
                
            }
            
            public void startTime()
            {

            }
            
    
}
