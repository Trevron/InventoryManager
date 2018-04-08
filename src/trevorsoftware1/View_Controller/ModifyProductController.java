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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author TrevTop
 */
public class ModifyProductController {
      
    
    
    
    
    // Modify product controller! - - - - -  - - - - - - - - - - - - - - - - - - - - - - -
    
    @FXML
    private TextField modProductPartIDTextField;

    @FXML
    private TextField modProductNameTextField;

    @FXML
    private TextField modProductInstockTextField;

    @FXML
    private TextField modProductPriceTextField;

    @FXML
    private TextField modProductMaxTextField;

    @FXML
    private TextField modProductMinTextField;

    @FXML
    private Button modProductSearchButton;

    @FXML
    private TextField modProductSearchTextField;

    @FXML
    private TableView<?> modProductTable1;

    @FXML
    private TableColumn<?, ?> modProductPartIDCol1;

    @FXML
    private TableColumn<?, ?> modProductPartNameCol1;

    @FXML
    private TableColumn<?, ?> modProductPartInvCol1;

    @FXML
    private TableColumn<?, ?> modProductPartPriceCol1;

    @FXML
    private Button modProductAddButton;

    @FXML
    private TableView<?> modProductTable2;

    @FXML
    private TableColumn<?, ?> modProductPartIDCol2;

    @FXML
    private TableColumn<?, ?> modProductPartNameCol2;

    @FXML
    private TableColumn<?, ?> modProductPartInvCol2;

    @FXML
    private TableColumn<?, ?> modProductPartPriceCol2;

    @FXML
    private Button modProductDeleteButton;

    @FXML
    private Button modProductSaveButton;

    @FXML
    private Button modProductCancelButton;

    @FXML
    void modProductAddButtonHandler(ActionEvent event) {

    }

    @FXML
    void modProductCancelButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Cancel button pressed!");
        
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) modProductCancelButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void modProductDeleteButtonHandler(ActionEvent event) {
        System.out.println("Delete button pressed!");
    }

    @FXML
    void modProductSaveButtonHandler(ActionEvent event) {
        System.out.println("Save button pressed!");
    }

    @FXML
    void modProductSearchButtonHandler(ActionEvent event) {
        System.out.println("Search button pressed!");
    }
    
}