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
 * @author Trevor Metcalf
 */
public class FXMLMainController implements Initializable {
    // initialize variables
    private State state;  
    private ObservableList<Part> partList;
    private ObservableList<Product> productList;
    Stage stage;
    Parent root;
 
    
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
        // exception control - exit confirmation
        Alerts.getAlert("exit").showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.exit(0);
            }
        });
    }

    @FXML
    void partAddButtonHandler(ActionEvent event) throws IOException {
        // switch to add part screen
        // get reference to the button's stage
        stage=(Stage) partAddButton.getScene().getWindow();
        // load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLInHousePart.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void partDeleteButton(ActionEvent event) {
        boolean canDelete = true;
        // set selected part in state to highlighted part from the part table
        state.setSelectedPart(mainPartTable.getSelectionModel().getSelectedItem());
        if (this.state.getSelectedPart() != null) {
            // check if part is associated with product
            // 'i' determines the product, 'x' determines the associated part
            for (int i = 0; i < this.state.getInventory().getProducts().size(); i++) {
                for (int x = 0; x < ((Product) this.state.getInventory().getProducts().get(i)).getAssociatedParts().size(); x++) {
                    Product product = (Product) this.state.getInventory().getProducts().get(i);
                    Part part = (Part) product.getAssociatedParts().get(x);
                    if (part == this.state.getSelectedPart()) {
                        canDelete = false;
                        break;
                    } 
                }
            } 
            // if part is not associated with a product, delete product and update observable 
            if (canDelete) {
                Alerts.getAlert("delete").showAndWait();
                // exception control - delete confirmation
                if (Alerts.getAlert("delete").getResult() == ButtonType.OK) {   
                    this.state.getInventory().deletePart(this.state.getSelectedPart().getPartID());
                    this.state.setSelectedPart(null);
                    this.partSearchButton(new ActionEvent());
                } 
            } else if (canDelete == false) {
                // exception control - can't delete part if is associated with a product
                Alerts.getAlert("partDelete").showAndWait();
            }
        } else {
            // exception control - nothing was selected
            Alerts.getAlert("nullSelect").showAndWait();
        }
    }

    @FXML
    void partModifyButtonHandler(ActionEvent event) throws IOException {
        // set selected part in state to highlighted part from the part table
        this.state.setSelectedPart(mainPartTable.getSelectionModel().getSelectedItem());
        if (this.state.getSelectedPart() != null) {
            // go to modify part screen
            // get reference to the button's stage
            stage=(Stage) partModifyButton.getScene().getWindow();
            //load up other FXML document
            root = FXMLLoader.load(getClass().getResource("FXMLInHouseModify.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // exception control - No part selected
            Alerts.getAlert("nullSelect").showAndWait();
        }
    }
    

    @FXML
    void partSearchButton(ActionEvent event) {
        // clear/initialize list
        this.partList.clear();
        // create array using lookupPart method
        String partName = partSearchField.getText();
        ArrayList<Part> foundParts = state.getInventory().lookupPart(partName);
        this.partList.addAll(foundParts);
        // update observable with found parts
        mainPartTable.setItems(this.partList);     
    }

    @FXML
    private void productAddButtonHandler(ActionEvent event) throws IOException {
        // switch to add product screen
        // get reference to the button's stage
        stage=(Stage) productAddButton.getScene().getWindow();
        // load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLAddProduct.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void productDeleteButton(ActionEvent event) {
        boolean canDelete;
        // set selected product in state to the highlighted product from the product tableview
        state.setSelectedProduct(mainProductTable.getSelectionModel().getSelectedItem());
        if (this.state.getSelectedProduct() != null) {
            // check if product is associated with any parts
            if (this.state.getSelectedProduct().getAssociatedParts().isEmpty()) {
                // deletion confirmation
                Alerts.getAlert("delete").showAndWait();
                if (Alerts.getAlert("delete").getResult() == ButtonType.OK) { 
                    // remove product from inventory and update observable
                    this.state.getInventory().removeProduct(this.state.getSelectedProduct().getProductID());
                    this.state.setSelectedProduct(null);
                    this.productSearchButton(new ActionEvent());
                }
            } else {
                // exception contol - product cannot be deleted if there are associated parts
                Alerts.getAlert("productDelete").showAndWait();
            }
        } else {
            // exception control - nothing was selected
            Alerts.getAlert("nullSelect").showAndWait();
        }
    }

    @FXML
    void productModifyButtonHandler(ActionEvent event) throws IOException {
        // set selected product in state to the highlighted product from the product table
        this.state.setSelectedProduct(mainProductTable.getSelectionModel().getSelectedItem());
        if (this.state.getSelectedProduct() != null) {       
            //switch to modify product screen
            //get reference to the button's stage
            stage=(Stage) productModifyButton.getScene().getWindow();
            //load up other FXML document
            root = FXMLLoader.load(getClass().getResource("FXMLModifyProduct.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // exception control - nothing was selected
            Alerts.getAlert("nullSelect").showAndWait();
        }
    }

    @FXML
    void productSearchButton(ActionEvent event) {
        // clear/initialize list
        this.productList.clear();
        // create an array using lookupProduct method
        String productName = productSearchField.getText();
        ArrayList<Product> foundProducts = state.getInventory().lookupProduct(productName);
        this.productList.addAll(foundProducts);
        // update observable with found products
        mainProductTable.setItems(this.productList);
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // get instance of state
        this.state = State.getInstance();
        // get parts from inventory and add them to the observable array list
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

        // get products from inventory and add them to the observable array list
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

