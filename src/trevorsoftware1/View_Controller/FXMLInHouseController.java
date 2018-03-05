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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author treth
 */
public class FXMLInHouseController {
    @FXML
    private AnchorPane AddInHouse;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField machineIDField;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private ToggleGroup inorout;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;
    
    public void inHouseRadioHandler(ActionEvent event) {
        
    }
    
    public void outsourcedRadioHandler(ActionEvent event) {
        
    }

    
    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Cancel button pressed!");
        
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) cancelButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    
    @FXML
    public void saveButtonHandler(ActionEvent event) {
        System.out.println("Save button pressed!");
    }
    
    
}
