/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevorsoftware1.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
 * @author TrevTop
 */
public class AddProductController implements Initializable {
    
    private ObservableList<Part> partList, associatedPartList;
    private ArrayList<Part> associatedParts = new ArrayList();
    private State state;
    
    // Add product controller! - - - - - - - - -  - - - - - - - - - - -  - - - - - - - - - - - 
    
    @FXML
    private AnchorPane addProductScreen;

    @FXML
    private TextField addProductIDTextField;

    @FXML
    private TextField addProductNameTextField;

    @FXML
    private TextField addProductInstockTextField;

    @FXML
    private TextField addProductPriceTextField;

    @FXML
    private TextField addProductMaxTextField;

    @FXML
    private TextField addProductMinTextField;

    @FXML
    private Button addProductSearchButton;

    @FXML
    private TextField addProductSearchTextField;

    @FXML
    private TableView<Part> addProductTable1;

    @FXML
    private TableColumn<Part, Integer> addProductPartIDCol1;

    @FXML
    private TableColumn<Part, String> addProductPartNameCol1;

    @FXML
    private TableColumn<Part, Integer> addProductPartInvCol1;

    @FXML
    private TableColumn<Part, Double> addProductPartPriceCol1;

    @FXML
    private Button addProductAddButton;

    @FXML
    private TableView<Part> addProductTable2;

    @FXML
    private TableColumn<Part, Integer> addProductPartIDCol2;

    @FXML
    private TableColumn<Part, String> addProductPartNameCol2;

    @FXML
    private TableColumn<Part, Integer> addProductPartInvCol2;

    @FXML
    private TableColumn<Part, Double> addProductPartPriceCol2;

    @FXML
    private Button addProductDeleteButton;

    @FXML
    private Button addProductSaveButton;

    @FXML
    private Button addProductCancelButton;

    @FXML
    void addProductCancelButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Cancel button pressed!");
        
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) addProductCancelButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addProductAddButtonHandler(ActionEvent event) {
        System.out.println("Add button pressed!");
        this.state.setSelectedPart(addProductTable1.getSelectionModel().getSelectedItem());
        if(this.state.getSelectedPart() != null) {
            associatedParts.add(this.state.getSelectedPart());
            this.state.setSelectedPart(null);
            this.associatedPartList.clear();
            this.associatedPartList.addAll(associatedParts);
        } else {
            System.out.println("No part selected");
        }
        
    }
    
    @FXML
    void addProductDeleteButtonHandler(ActionEvent event) {
        System.out.println("Delete button pressed!");
    }

    @FXML
    void addProductSaveButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Save button pressed!");
        int productID = 0;
        String name = "default";
        double price = 0.0;
        int inStock = 0;
        int min = 0;
        int max = 0;
        
        productID = Integer.parseInt(addProductIDTextField.getText());
        name = addProductNameTextField.getText();
        price = Double.parseDouble(addProductPriceTextField.getText());
        inStock = Integer.parseInt(addProductInstockTextField.getText());
        min = Integer.parseInt(addProductMinTextField.getText());
        max = Integer.parseInt(addProductMaxTextField.getText());
        Product product = new Product(productID, name, price, inStock, min, max, associatedParts);
        
        this.state.getInventory().addProduct(product);
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) addProductCancelButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addProductSearchButtonHandler(ActionEvent event) {
        System.out.println("Search button pressed!");
        
        this.partList.clear();
        
        String partName = addProductSearchTextField.getText();
        System.out.println("searching for partID: " + addProductSearchTextField.getText());
        ArrayList<Part> foundParts = this.state.getInventory().lookupPart(partName);
        this.partList.addAll(foundParts);
        
        //add returned part to observable list
        addProductTable1.setItems(this.partList);
        
        
    }
   
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        
        this.state = State.getInstance();
        
        // add product part table 1

            this.partList = FXCollections.observableArrayList();
            this.partList.clear();
            this.partList.addAll(state.getInventory().getAllParts());
            System.out.println(Arrays.toString(state.getInventory().getAllParts().toArray()));
            this.addProductTable1.setItems(this.partList);
            // Set up table cells
            addProductPartIDCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
            addProductPartNameCol1.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            addProductPartInvCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
            addProductPartPriceCol1.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
  
        
        // add product associated parts table 2
            this.associatedPartList = FXCollections.observableArrayList();
            this.associatedPartList.clear();
            this.associatedPartList.addAll(associatedParts);
            System.out.println(Arrays.toString(associatedParts.toArray()));
            
            this.addProductTable2.setItems(this.associatedPartList);
            // Set up table cells
            addProductPartIDCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
            addProductPartNameCol2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            addProductPartInvCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
            addProductPartPriceCol2.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
    }
    
}
