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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import trevorsoftware1.Model.Inhouse;
import trevorsoftware1.Model.Inventory;
import trevorsoftware1.Model.Outsourced;
import trevorsoftware1.Model.Part;
import trevorsoftware1.Model.Product;

/**
 *
 * @author treth
 */
public class FXMLMainController implements Initializable {
    
    //Instantiate inventory
    public static Inventory inv = new Inventory();
    
    private ObservableList<Part> partList, associatedPartList;
    ;

    private Part modPart = null;
    private Product currentProduct = null;
 
    
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
        exitAlert.showAndWait().ifPresent(response -> {
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
        
    }

    @FXML
    void partModifyButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Modify part button pressed!");
        modPart = mainPartTable.getSelectionModel().getSelectedItem();
        
        if (modPart != null) {
            System.out.println(modPart.getName());
            int partID = modPart.getPartID();
            String name = modPart.getName();
            
            
            
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
        System.out.println("searching for partID: " + partSearchField.getText());
        //Pass input using overloaded method.
        ArrayList<Part> foundParts = inv.lookupPart(partName);
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
 /*       
        // Set up table cells
        productIDColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("inStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        
        // create new observable arraylist
        ObservableList<Product> products = FXCollections.observableArrayList();
        // clear/initialize list
        products.clear();
        
        // get partID from textfield and search, return part
        //int partID = Integer.parseInt(partSearchField.getText());
        String productName = productSearchField.getText();
        System.out.println("searching for partID: " + partSearchField.getText());
        //Pass input using overloaded method.
        Product product = inv.lookupProduct(productName);
        products.add(product);
        
        //add returned part to observable list
        mainProductTable.setItems(product);
 */      
    }
    
    // Add Part InHouse Controls - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    
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
    
    @FXML
    private Label inoroutLabel;
    
    public void inHouseRadioHandler(ActionEvent event) {
        if (inHouseRadio.isSelected()) {
            System.out.println("Inhouse part.");
            inoroutLabel.setText("MachineID");
            machineIDField.promptTextProperty().setValue("Machine ID");
        } else if (outsourcedRadio.isSelected()) {
            System.out.println("Outsourced part.");
            inoroutLabel.setText("Company Name");
            machineIDField.promptTextProperty().setValue("Company name");
        }
    }
    
    public void outsourcedRadioHandler(ActionEvent event) {
        if (inHouseRadio.isSelected()) {
            System.out.println("Inhouse part.");
            inoroutLabel.setText("MachineID");
            machineIDField.promptTextProperty().setValue("Machine ID");
        } else if (outsourcedRadio.isSelected()) {
            System.out.println("Outsourced part.");
            inoroutLabel.setText("Company Name");
            machineIDField.promptTextProperty().setValue("Company name");
        }
    }
    
    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Cancel button pressed!");
    
        cancelAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    System.out.println("Add part cancelled.");
                    
                    Stage stage;
                    Parent root;
                    //get reference to the button's stage
                    stage=(Stage) cancelButton.getScene().getWindow();
                    //load up other FXML document
                    root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        });
        
    }
    
    
    @FXML
    public void saveButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Save button pressed!");
        
        int partID = inv.assignPartID(); // autogenerate partID
        String name = "";
        double price = 0;
        int inStock = 0;
        int min = 0;
        int max = 0;
        int machineID = 0;
        String companyName = "default";
        Part part = null;
        
        // Check radio button then create correct part type
        if (inHouseRadio.isSelected()) {
            System.out.println("Inhouse part created.");
            // Take input from text fields, parsing if necessary, and assign them to variables 
            try {
            name = nameField.getText();
            price = Double.parseDouble(priceField.getText());
            inStock = Integer.parseInt(invField.getText());
            min = Integer.parseInt(minField.getText());
            max = Integer.parseInt(maxField.getText());
            machineID = Integer.parseInt(machineIDField.getText());
            } catch (NumberFormatException e) {
                System.err.println("NumberFormatException: " + e.getMessage());
                numFormatExc.showAndWait();
            }
            // Create new inhouse part
            part = new Inhouse(partID, name, price, inStock, min, max, machineID);    
        } else if (outsourcedRadio.isSelected()) {
            System.out.println("Outsourced part created.");
            try {
            name = nameField.getText();
            price = Double.parseDouble(priceField.getText());
            inStock = Integer.parseInt(invField.getText());
            min = Integer.parseInt(minField.getText());
            max = Integer.parseInt(maxField.getText());
            companyName = machineIDField.getText();
            } catch (NumberFormatException e) {
                System.err.println("NumberFormatException: " + e.getMessage());
                numFormatExc.showAndWait();
            }
            // Create new outsourced part
            part = new Outsourced(partID, name, price, inStock, min, max, companyName);     
        }
        
        // Add created part to inventory of parts
        inv.addPart(part);
        
        //Go back to main screen
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
    
    
    // Add part outsourced ------------------------------------------------------------
    
    @FXML
    private AnchorPane addPartOutsourced;

    @FXML
    private TextField os_partIDTextField;

    @FXML
    private TextField os_partNameTextField;

    @FXML
    private TextField os_partInStockTextField;

    @FXML
    private TextField os_partPriceTextField;

    @FXML
    private TextField os_partMaxTextField;

    @FXML
    private TextField os_partMinTextField;

    @FXML
    private TextField os_partCompanyNameTextField;

    @FXML
    private RadioButton os_partInhouseRadio;

    @FXML
    private RadioButton os_partOutsourcedRadio;

    @FXML
    private Button os_partSaveButton;

    @FXML
    private Button os_partCancelButton;

    @FXML
    void os_partCancelButtonHandler(ActionEvent event) throws IOException {

        System.out.println("Cancel button pressed!");
        
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) os_partCancelButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void os_partSaveButtonHandler(ActionEvent event) {

        System.out.println("Save button pressed!");
    }
    
    
    
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
    
    
    // Modify part outsourced controller - - - - - - - - - - - - - -  - - - - - - - - - - - - -
    
    @FXML
    private AnchorPane osmod_partOutsourcedScreen;

    @FXML
    private TextField osmod_partIDTextField;

    @FXML
    private TextField osmod_nameTextField;

    @FXML
    private TextField osmod_instockTextField;

    @FXML
    private TextField osmod_priceTextField;

    @FXML
    private TextField osmod_maxTextField;

    @FXML
    private TextField osmod_minTextField;

    @FXML
    private TextField osmod_companyNameTextField;

    @FXML
    private RadioButton osmod_inhouseRadio;

    @FXML
    private RadioButton osmod_outsourcedRadio;

    @FXML
    private Button osmod_saveButton;

    @FXML
    private Button osmod_cancelButton;

    @FXML
    void osmod_cancelButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Cancel button pressed!");
        
        
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) osmod_cancelButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void osmod_saveButtonHandler(ActionEvent event) {
        System.out.println("Save button pressed!");
    }
    
    
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
        Part part = addProductTable1.getSelectionModel().getSelectedItem();
        
    }
    
    @FXML
    void addProductDeleteButtonHandler(ActionEvent event) {
        System.out.println("Delete button pressed!");
    }

    @FXML
    void addProductSaveButtonHandler(ActionEvent event) {
        System.out.println("Save button pressed!");
    }

    @FXML
    void addProductSearchButtonHandler(ActionEvent event) {
        System.out.println("Search button pressed!");
        this.partList.clear();
        
        // get partID from textfield and search, return part
        //int partID = Integer.parseInt(partSearchField.getText());
        String partName = addProductSearchTextField.getText();
        System.out.println("searching for partID: " + addProductSearchTextField.getText());
        //Pass input using overloaded method.
        ArrayList<Part> foundParts = inv.lookupPart(partName);
        this.partList.addAll(foundParts);
        
        //add returned part to observable list
        addProductTable1.setItems(this.partList);
    }
    
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
    
    
    
    
     
    
    
    
    
    // Alert dialogs - - - - - - - - - - - - - - - - - - - - - - - - - -
    
    Alert numFormatExc = new Alert(AlertType.ERROR);
    Alert cancelAlert = new Alert(AlertType.CONFIRMATION);
    Alert saveAlert = new Alert(AlertType.CONFIRMATION);
    Alert exitAlert = new Alert(AlertType.CONFIRMATION);
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // main part table
        if (this.mainPartTable != null) {
            this.partList = FXCollections.observableArrayList();
            this.partList.clear();
            this.partList.addAll(inv.getAllParts());
            this.mainPartTable.setItems(this.partList);
            // Set up table cells
            partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
            partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            partInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
            partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        }
        
        // main product table
        // code here
        
        // add product part table 1
        if (this.addProductTable1 != null) {
            this.partList = FXCollections.observableArrayList();
            this.partList.clear();
            this.partList.addAll(inv.getAllParts());
            this.addProductTable1.setItems(this.partList);
            // Set up table cells
            addProductPartIDCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
            addProductPartNameCol1.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            addProductPartInvCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
            addProductPartPriceCol1.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        }
        
        // add product associated parts table 2
        if (this.addProductTable2 != null) {
            this.associatedPartList = FXCollections.observableArrayList();
            this.associatedPartList.clear();
            //this.associatedPartList.addAll(inv.getProducts());
            this.addProductTable2.setItems(this.associatedPartList);
            // Set up table cells
            addProductPartIDCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
            addProductPartNameCol2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            addProductPartInvCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
            addProductPartPriceCol2.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        }
      
        

        // Set up alerts
        numFormatExc.setTitle("ERROR");
        numFormatExc.setHeaderText("Number Format Exception");
        numFormatExc.setContentText("Sorry, there was an error.");
        
        cancelAlert.setTitle("Cancel");
        cancelAlert.setHeaderText("Are you sure you want to continue?");
        cancelAlert.setContentText("Changes will not be saved.");
        
        saveAlert.setTitle("Save");
        saveAlert.setHeaderText("Are you sure you want to continue?");
        saveAlert.setContentText("Changes will be saved.");
        
        exitAlert.setTitle("Close program");
        exitAlert.setHeaderText("Are you sure you want to close this program?");
        exitAlert.setContentText("Nothing will be saved.");
        
        // Disable ID field for autogenerate
        if(this.idField != null) {
            this.idField.setDisable(true);
        }
        
    }
    
    
   
}

