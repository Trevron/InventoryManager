/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevorsoftware1.View_Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author TrevTop
 */
public class ModifyPartController {
       // Modify part inhouse controller!!  -- - - - - - - - - - - - - - - - - - -- - - - - - 
   
    @FXML
    private AnchorPane ihmod_partScreen;

    @FXML
    private TextField ihmod_partIDTextField;

    @FXML
    private TextField ihmod_partNameTextField;

    @FXML
    private TextField ihmod_partInstockTextField;

    @FXML
    private TextField ihmod_partPriceTextField;

    @FXML
    private TextField ihmod_partMaxTextField;

    @FXML
    private TextField ihmod_partMinTextField;

    @FXML
    private TextField ihmod_partMachineIDTextField;

    @FXML
    private RadioButton ihmod_inhouseRadio;

    @FXML
    private RadioButton ihmod_outsourcedRadio;

    @FXML
    private Button ihmod_partSaveButton;

    @FXML
    private Button ihmod_partCancelButton;

    @FXML
    void ihmod_partCancelButtonHandler(ActionEvent event) throws IOException {

        System.out.println("Cancel button pressed!");
        
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) ihmod_partCancelButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    void ihmod_partSaveButtonHandler(ActionEvent event) {
        System.out.println("Save button pressed!");
        
        
    }
    
    
}
