/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevorsoftware1.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import trevorsoftware1.Model.Part;
import trevorsoftware1.Model.Product;
import trevorsoftware1.Model.State;

/**
 *
 * @author treth
 */
public class FXMLMainController implements Initializable {
    
    private State state;
    
    private ObservableList<Part> partList;
    private ObservableList<Product> productList;
 
    
    // MAIN SCREEN CONTROLS! - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    @FXML
    private AnchorPane mainScreen;

    @FXML
    private TableView<Part> mainPartTable;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TextField partSearchField;

    @FXML
    private TableView<Product> mainProductTable;

    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

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
        Alerts.getAlert("exit").showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.out.println("Program terminated.");
                System.exit(0);
            }
        });
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
        state.setSelectedPart(mainPartTable.getSelectionModel().getSelectedItem());
        if (this.state.getSelectedPart() != null) {
            Alerts.getAlert("delete").showAndWait();
            if (Alerts.getAlert("delete").getResult() == ButtonType.OK) {   
                System.out.println("Deleted part: " + this.state.getSelectedPart().getName());
                this.state.getInventory().deletePart(this.state.getSelectedPart().getPartID());
                this.state.setSelectedPart(null);
                this.partSearchButton(new ActionEvent());
            }
        } else {
            Alerts.getAlert("nullSelect").showAndWait();
        }
    }

    @FXML
    void partModifyButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Modify part button pressed!");
        this.state.setSelectedPart(mainPartTable.getSelectionModel().getSelectedItem());
        
        if (this.state.getSelectedPart() != null) {
            System.out.println(state.getSelectedPart().getName());
            int partID = state.getSelectedPart().getPartID();
            String name = state.getSelectedPart().getName();

            Stage stage;
            Parent root;

            //get reference to the button's stage
            stage=(Stage) partModifyButton.getScene().getWindow();
            //load up other FXML document
            root = FXMLLoader.load(getClass().getResource("FXMLInHouseModify.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            System.out.println(partID);
            System.out.println("Editing part " + partID + " | " + name);
   
        } else {
            System.out.println("No part selected.");
            Alerts.getAlert("nullSelect").showAndWait();
        }

    }
    

    @FXML
    void partSearchButton(ActionEvent event) {
        System.out.println("Part search button pressed!");
        
        // clear/initialize list
        this.partList.clear();
        
        // get partID from textfield and search, return part
        //int partID = Integer.parseInt(partSearchField.getText());
        String partName = partSearchField.getText();
        System.out.println("searching for part: " + partSearchField.getText());
        //Pass input using overloaded method.
        ArrayList<Part> foundParts = state.getInventory().lookupPart(partName);
        this.partList.addAll(foundParts);
        
        //add returned part to observable list
        mainPartTable.setItems(this.partList);
        
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
        
        state.setSelectedProduct(mainProductTable.getSelectionModel().getSelectedItem());
        if (this.state.getSelectedProduct() != null) {
            Alerts.getAlert("delete").showAndWait();
            if (Alerts.getAlert("delete").getResult() == ButtonType.OK) {   
                System.out.println("Deleted product: " + this.state.getSelectedProduct().getName());
                this.state.getInventory().removeProduct(this.state.getSelectedProduct().getProductID());
                this.state.setSelectedProduct(null);
                this.productSearchButton(new ActionEvent());
            }
        } else {
            Alerts.getAlert("nullSelect").showAndWait();
        }
    }

    @FXML
    void productModifyButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Modify product button pressed!");
        this.state.setSelectedProduct(mainProductTable.getSelectionModel().getSelectedItem());
        if (this.state.getSelectedProduct() != null) {       
            Stage stage;
            Parent root;
            //get reference to the button's stage
            stage=(Stage) productModifyButton.getScene().getWindow();
            //load up other FXML document
            root = FXMLLoader.load(getClass().getResource("FXMLModifyProduct.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alerts.getAlert("nullSelect").showAndWait();
        }
    }

    @FXML
    void productSearchButton(ActionEvent event) {
        System.out.println("Product search button pressed!");
        // clear/initialize list
        this.productList.clear();
        String productName = productSearchField.getText();
        System.out.println("searching for product: " + productSearchField.getText());
        //Pass input using overloaded method.
        ArrayList<Product> foundProducts = state.getInventory().lookupProduct(productName);
        this.productList.addAll(foundProducts);
        //add returned part to observable list
        mainProductTable.setItems(this.productList);
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.state = State.getInstance();
        
        // main part table
        this.partList = FXCollections.observableArrayList();
        this.partList.clear();
        this.partList.addAll(this.state.getInventory().getAllParts());
        this.mainPartTable.setItems(this.partList);
        // Set up table cells
        partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));


        // main product table
        this.productList = FXCollections.observableArrayList();
        this.productList.clear();
        this.productList.addAll(this.state.getInventory().getProducts());
        this.mainProductTable.setItems(this.productList);
        // set up table cells
        productIDColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("inStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
    }
    
    
   
}

