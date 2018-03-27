/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevorsoftware1.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import trevorsoftware1.Model.Part;

/**
 *
 * @author treth
 */
public class FXMLMainController implements Initializable {
    
    //Instantiate inventory
    public static Inventory inv = new Inventory();
    Inhouse tool = new Inhouse(123, "Tool1", 30.00 , 20, 10, 0, 12345 );
    Inhouse tool2 = new Inhouse(222, "Tool2", 10.00 , 20, 10, 0, 12346 );
    
    
    
    
    
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
        
        // Set up table cells
        partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        
        // create new observable arraylist
        ObservableList<Part> parts = FXCollections.observableArrayList();
        // clear/initialize list
        parts.clear();
        
        // get partID from textfield and search, return part
        int partID = Integer.parseInt(partSearchField.getText());
        System.out.println("searching for partID: " + partSearchField.getText());
        Part part = inv.lookupPart(partID);
        parts.add(part);
        
        //add returned part to observable list
        mainPartTable.setItems(parts);
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
    public void saveButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Save button pressed!");
        
        int partID = 0;
        String name = "";
        double price = 0;
        int inStock = 0;
        int min = 0;
        int max = 0;
        int machineID = 0;
        
        // Take input from text fields, parsing if necessary, and assign them to variables
        
        try {
        partID = Integer.parseInt(idField.getText());
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
        Inhouse item = new Inhouse(partID, name, price, inStock, min, max, machineID);
       
        // Add created part to inventory of parts
        inv.addPart(item);
        
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
    void os_partCancelButtonHandler(ActionEvent event) {

    }

    @FXML
    void os_partSaveButtonHandler(ActionEvent event) {

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
    void ihmod_partCancelButtonHandler(ActionEvent event) {

    }

    @FXML
    void ihmod_partSaveButtonHandler(ActionEvent event) {

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
    void osmod_cancelButtonHandler(ActionEvent event) {

    }

    @FXML
    void osmod_saveButtonHandler(ActionEvent event) {

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
    private TableView<?> addProductTable1;

    @FXML
    private TableColumn<?, ?> addProductPartIDCol1;

    @FXML
    private TableColumn<?, ?> addProductPartNameCol1;

    @FXML
    private TableColumn<?, ?> addProductPartInvCol1;

    @FXML
    private TableColumn<?, ?> addProductPartPriceCol1;

    @FXML
    private Button addProductAddButton;

    @FXML
    private TableView<?> addProductTable2;

    @FXML
    private TableColumn<?, ?> addProductPartIDCol2;

    @FXML
    private TableColumn<?, ?> addProductPartNameCol2;

    @FXML
    private TableColumn<?, ?> addProductPartInvCol2;

    @FXML
    private TableColumn<?, ?> addProductPartPriceCol2;

    @FXML
    private Button addProductDeleteButton;

    @FXML
    private Button addProductSaveButton;

    @FXML
    private Button addProductCancelButton;

    @FXML
    void addProductCancelButtonHandler(ActionEvent event) {

    }

    @FXML
    void addProductDeleteButtonHandler(ActionEvent event) {

    }

    @FXML
    void addProductSaveButtonHandler(ActionEvent event) {

    }

    @FXML
    void addProductSearchButtonHandler(ActionEvent event) {

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
    void modProductCancelButtonHandler(ActionEvent event) {

    }

    @FXML
    void modProductDeleteButtonHandler(ActionEvent event) {

    }

    @FXML
    void modProductSaveButtonHandler(ActionEvent event) {

    }

    @FXML
    void modProductSearchButtonHandler(ActionEvent event) {

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Alert dialogs
    
    Alert numFormatExc = new Alert(AlertType.ERROR);
    
   
    
    public void initialize(URL url, ResourceBundle rb) {
        
        inv.addPart(tool);
        inv.addPart(tool2);
        numFormatExc.setTitle("ERROR");
        numFormatExc.setHeaderText("Number Format Exception");
        numFormatExc.setContentText("Sorry, there was an error.");
        
    }
    
    
   
}

