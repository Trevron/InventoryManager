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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import trevorsoftware1.Model.Part;
import trevorsoftware1.Model.Product;
import trevorsoftware1.Model.State;

/**
 *
 * @author TrevTop
 */
public class ModifyProductController implements Initializable {
    
    private ObservableList<Part> partList, associatedPartList;
    private ArrayList<Part> associatedParts = new ArrayList();
    private State state;

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
    private TableView<Part> modProductTable1;

    @FXML
    private TableColumn<Part, Integer> modProductPartIDCol1;

    @FXML
    private TableColumn<Part, String> modProductPartNameCol1;

    @FXML
    private TableColumn<Part, Integer> modProductPartInvCol1;

    @FXML
    private TableColumn<Part, Double> modProductPartPriceCol1;

    @FXML
    private Button modProductAddButton;

    @FXML
    private TableView<Part> modProductTable2;

    @FXML
    private TableColumn<Part, Integer> modProductPartIDCol2;

    @FXML
    private TableColumn<Part, String> modProductPartNameCol2;

    @FXML
    private TableColumn<Part, Integer> modProductPartInvCol2;

    @FXML
    private TableColumn<Part, Double> modProductPartPriceCol2;

    @FXML
    private Button modProductDeleteButton;

    @FXML
    private Button modProductSaveButton;

    @FXML
    private Button modProductCancelButton;

    @FXML
    void modProductAddButtonHandler(ActionEvent event) {
        System.out.println("Add button pressed!");
        this.state.setSelectedPart(modProductTable1.getSelectionModel().getSelectedItem());
        if(this.state.getSelectedPart() != null) {
            associatedParts.add(this.state.getSelectedPart());
            this.state.setSelectedPart(null);
            this.associatedPartList.clear();
            this.associatedPartList.addAll(associatedParts);
        } else {
            System.out.println("No part selected");
            Alerts.getAlert("nullSelect").showAndWait();
        }  
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
        this.state.setSelectedPart(modProductTable2.getSelectionModel().getSelectedItem());
        if(this.state.getSelectedPart() != null) {
            Alerts.getAlert("delete").showAndWait();
            if (Alerts.getAlert("delete").getResult() == ButtonType.OK) {
                associatedParts.remove(this.state.getSelectedPart());
                this.state.setSelectedPart(null);
                this.associatedPartList.clear();
                this.associatedPartList.addAll(associatedParts);
            }
        } else {
            System.out.println("No part selected");
        }
    }

    @FXML
    void modProductSaveButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Save button pressed!");
        
        String name = "default";
        double price = 0.0;
        int inStock = 0;
        int min = 0;
        int max = 0;
        boolean isValid = false;
        boolean numFormat = false;
        
        try {
            name = modProductNameTextField.getText();
            price = Double.parseDouble(modProductPriceTextField.getText());
            inStock = Integer.parseInt(modProductInstockTextField.getText());
            min = Integer.parseInt(modProductMinTextField.getText());
            max = Integer.parseInt(modProductMaxTextField.getText());
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e.getMessage());
            Alerts.getAlert("numFormatExc").showAndWait();
            numFormat = true;
        }
        
        if (min > max) {
            System.out.println("Min cannot be greater than max");
            Alerts.getAlert("minOverMax").showAndWait();
        } else if (min < 0) {
            System.out.println("Min must be at least 0");
            Alerts.getAlert("minUnderZero").showAndWait();
        } else if (inStock < min) {
            System.out.println("Inv cannot be less than min");
            Alerts.getAlert("invUnderMin").showAndWait();
        } else if (inStock > max) {
            System.out.println("Inv cannot be greater than max");
            Alerts.getAlert("invOverMax").showAndWait();
        } else if (associatedParts.isEmpty()) {
            System.out.println("Associated part list is empty.");
            Alerts.getAlert("noParts").showAndWait();
        } else if (numFormat == true) {
            System.out.println("There was a problem with part creation.");
        } else if (price < checkPrice()) {
            System.out.println("Price cannot be less than total cost of associated parts.");
            Alerts.getAlert("lowPrice").showAndWait();
        } else {
            System.out.println("Validation succesful");
            isValid = true;
        }
        
        if (isValid) {
            this.state.getSelectedProduct().setName(name);
            this.state.getSelectedProduct().setPrice(price);
            this.state.getSelectedProduct().setInStock(inStock);
            this.state.getSelectedProduct().setMin(min);
            this.state.getSelectedProduct().setMax(max);
            this.state.getSelectedProduct().setAssociatedParts(associatedParts);
            
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
    }

    @FXML
    void modProductSearchButtonHandler(ActionEvent event) {
        System.out.println("Search button pressed!");
        
        this.partList.clear();
        
        String partName = modProductSearchTextField.getText();
        System.out.println("searching for part: " + modProductSearchTextField.getText());
        ArrayList<Part> foundParts = this.state.getInventory().lookupPart(partName);
        this.partList.addAll(foundParts);
        
        //add returned part to observable list
        modProductTable1.setItems(this.partList);
    }
    
    // check price of associated parts
    private double checkPrice() {
        double price = 0;
        if (!associatedParts.isEmpty()) {
            for (int i = 0; i < associatedParts.size(); i++) {
                price += associatedParts.get(i).getPrice();
            }
        }
        return price;
    }
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        this.state = State.getInstance();
        
        
        // add product part table 1
        this.partList = FXCollections.observableArrayList();
        this.partList.clear();
        this.partList.addAll(state.getInventory().getAllParts());
        System.out.println(Arrays.toString(state.getInventory().getAllParts().toArray()));
        this.modProductTable1.setItems(this.partList);
        // Set up table cells
        modProductPartIDCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        modProductPartNameCol1.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        modProductPartInvCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        modProductPartPriceCol1.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
  
        
        // add product associated parts table 2
        this.associatedPartList = FXCollections.observableArrayList();
        this.associatedPartList.clear();
        this.associatedParts.addAll(this.state.getSelectedProduct().getAssociatedParts());
        this.associatedPartList.addAll(associatedParts);
        System.out.println(Arrays.toString(associatedParts.toArray()));
        this.modProductTable2.setItems(this.associatedPartList);
        // Set up table cells
        modProductPartIDCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        modProductPartNameCol2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        modProductPartInvCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        modProductPartPriceCol2.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        // disable product ID for autogen
        this.modProductPartIDTextField.setDisable(true);
        
        
        // input values
        if (this.state.getSelectedProduct() != null) {
            modProductPartIDTextField.setText(Integer.toString(this.state.getSelectedProduct().getProductID()));
            modProductNameTextField.setText(this.state.getSelectedProduct().getName());
            modProductInstockTextField.setText(Integer.toString(this.state.getSelectedProduct().getInStock()));
            modProductPriceTextField.setText(Double.toString(this.state.getSelectedProduct().getPrice()));
            modProductMinTextField.setText(Integer.toString(this.state.getSelectedProduct().getMin()));
            modProductMaxTextField.setText(Integer.toString(this.state.getSelectedProduct().getMax()));   
        }    
    }
}
