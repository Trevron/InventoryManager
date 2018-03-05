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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author treth
 */
public class FXMLMainScreenController{

    @FXML
    private AnchorPane mainScreen;

    @FXML
    private TableView<?> mainPartTable;

    @FXML
    private TableColumn<?, ?> partIDColumn;

    @FXML
    private TableColumn<?, ?> partNameColumn;

    @FXML
    private TableColumn<?, ?> partInvColumn;

    @FXML
    private TableColumn<?, ?> partPriceColumn;

    @FXML
    private TextField partSearchField;

    @FXML
    private TableView<?> mainProductTable;

    @FXML
    private TableColumn<?, ?> productIDColumn;

    @FXML
    private TableColumn<?, ?> productNameColumn;

    @FXML
    private TableColumn<?, ?> productInventoryColumn;

    @FXML
    private TableColumn<?, ?> productPriceColumn;

    @FXML
    private TextField productSearchField;
    
    @FXML
    private Button productAddButton;
    
    @FXML
    private Button productModifyButton;
    
    @FXML
    private Button partAddButton;
    
    @FXML Button partModifyButton;

    @FXML
    void mainExitButton(ActionEvent event) {
        System.out.println("Exit button pressed!");
        System.exit(0);
    }

    @FXML
    void partAddButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Add part button pressed!");
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) partAddButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLInHousePart.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void partDeleteButton(ActionEvent event) {
        System.out.println("Delete part button pressed!");
    }

    @FXML
    void partModifyButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Modify part button pressed!");
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) partModifyButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLInHouseModify.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void partSearchButton(ActionEvent event) {
        System.out.println("Part search button pressed!");
    }

    @FXML
    private void productAddButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Add product button pressed!");
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) productAddButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLAddProduct.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void productDeleteButton(ActionEvent event) {
        System.out.println("Delete product button pressed!");
    }

    @FXML
    void productModifyButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Modify product button pressed!");
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) productModifyButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLModifyProduct.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void productSearchButton(ActionEvent event) {
        System.out.println("Product search button pressed!");
    }
    
}
