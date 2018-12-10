/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Mainpackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        // TODO
    }    
    
    //Menu Bar Actions
        //File Menu
            //Save 
            public void save(ActionEvent event)
            {
                
            }
            //Load
            public void load(ActionEvent event)
            {
                
            }
            //Exit
            public void exit(ActionEvent event)
            {
                Stage window = (Stage)((Node)root).getScene().getWindow();
                window.close();
                
            }
    
}
