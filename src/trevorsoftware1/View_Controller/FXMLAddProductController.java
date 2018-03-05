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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLAddProductController {

    @FXML
    private AnchorPane addProductScreen;

    @FXML
    private TextField productID;

    @FXML
    private TextField ProductName;

    @FXML
    private TextField productInventory;

    @FXML
    private TextField productPrice;

    @FXML
    private TextField productMax;

    @FXML
    private TextField productMin;

    @FXML
    private Button productSearchButton;

    @FXML
    private TextField productSearchField;

    @FXML
    private TableView<?> productTable1;

    @FXML
    private Button productAddButton;

    @FXML
    private TableView<?> productTable2;

    @FXML
    private Button productDeleteButton;

    @FXML
    private Button productSaveButton;

    @FXML
    private Button productCancelButton;
    
    @FXML
    void productCancelButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Cancel button pressed!");
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) productCancelButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}