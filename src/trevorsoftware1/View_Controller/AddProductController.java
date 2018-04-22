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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import trevorsoftware1.Model.Part;
import trevorsoftware1.Model.Product;
import trevorsoftware1.Model.State;


/**
 *
 * @author Trevor Metcalf
 */
public class AddProductController implements Initializable {
    
    private ObservableList<Part> partList, associatedPartList;
    private ArrayList<Part> associatedParts = new ArrayList();
    private State state;
    Stage stage;
    Parent root;
    
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
        // show cancel confirmation
        Alerts.getAlert("cancel").showAndWait();
        if (Alerts.getAlert("cancel").getResult() == ButtonType.OK) {
            // return to main screen
            // get reference to the button's stage
            stage=(Stage) addProductCancelButton.getScene().getWindow();
            //load up other FXML document
            root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void addProductAddButtonHandler(ActionEvent event) {
        // set selected part in state to the highlighted part from table1
        this.state.setSelectedPart(addProductTable1.getSelectionModel().getSelectedItem());
        if(this.state.getSelectedPart() != null) {
            // add selected part to associated part array
            associatedParts.add(this.state.getSelectedPart());
            this.state.setSelectedPart(null);
            this.associatedPartList.clear();
            this.associatedPartList.addAll(associatedParts);
        } else {
            Alerts.getAlert("nullSelect").showAndWait();
        }      
    }
    
    @FXML
    void addProductDeleteButtonHandler(ActionEvent event) {
        // set selected part in state to the highlighted part from table1
        this.state.setSelectedPart(addProductTable2.getSelectionModel().getSelectedItem());
        if(this.state.getSelectedPart() != null) {
            // show deletion confirmation
            Alerts.getAlert("delete").showAndWait();
            if (Alerts.getAlert("delete").getResult() == ButtonType.OK) {
                // delete associated part, set selected part to null and update observable
                associatedParts.remove(this.state.getSelectedPart());
                this.state.setSelectedPart(null);
                this.associatedPartList.clear();
                this.associatedPartList.addAll(associatedParts);
            }
        }
    }

    @FXML
    void addProductSaveButtonHandler(ActionEvent event) throws IOException {
        // auto generate product ID 
        int productID = this.state.getInventory().assignProductID();
        // initialize variables with default values
        String name = "default";
        double price = 0.0;
        int inStock = 0;
        int min = 0;
        int max = 0;
        boolean isValid = false;
        boolean numFormat = false;
        // make sure correct inputs are passed in
        // exception controls
        try {
            name = addProductNameTextField.getText();
            price = Double.parseDouble(addProductPriceTextField.getText());
            inStock = Integer.parseInt(addProductInstockTextField.getText());
            min = Integer.parseInt(addProductMinTextField.getText());
            max = Integer.parseInt(addProductMaxTextField.getText());
        } catch (NumberFormatException e) {
            Alerts.getAlert("numFormatExc").showAndWait();
            numFormat = true;
        }
        // exception controls / validation test
        if (min > max) {
            Alerts.getAlert("minOverMax").showAndWait();
        } else if (min < 0) {
            Alerts.getAlert("minUnderZero").showAndWait();
        } else if (inStock < min) {
            Alerts.getAlert("invUnderMin").showAndWait();
        } else if (inStock > max) {
            Alerts.getAlert("invOverMax").showAndWait();
        } else if (associatedParts.isEmpty()) {
            Alerts.getAlert("noParts").showAndWait();
        } else if (numFormat == true) {
        } else if (price < checkPrice()) {
            Alerts.getAlert("lowPrice").showAndWait();
        } else {
            isValid = true;
        }
        
        if (isValid) {
            // Create new product and add to inventory
            Product product = new Product(productID, name, price, inStock, min, max, associatedParts);
            this.state.getInventory().addProduct(product);
            // switch back to main screen
            stage=(Stage) addProductCancelButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void addProductSearchButtonHandler(ActionEvent event) {
        // initialize search results
        this.partList.clear();
        // search for part from textfield input
        String partName = addProductSearchTextField.getText();
        ArrayList<Part> foundParts = this.state.getInventory().lookupPart(partName);
        //add returned parts to / update observable list   
        this.partList.addAll(foundParts);
        addProductTable1.setItems(this.partList);  
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
        // get instance of state
        this.state = State.getInstance();
        
        // add product part table 1
        // get parts from inventory and add them to the observable array list
        this.partList = FXCollections.observableArrayList();
        this.partList.clear();
        this.partList.addAll(state.getInventory().getAllParts());
        this.addProductTable1.setItems(this.partList);
        // Set up table cells
        addProductPartIDCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        addProductPartNameCol1.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        addProductPartInvCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        addProductPartPriceCol1.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
  
        
        // add product associated parts table 2
        // assign array list to tableview even though the array list is empty
        this.associatedPartList = FXCollections.observableArrayList();
        this.associatedPartList.clear();
        this.associatedPartList.addAll(associatedParts);
        this.addProductTable2.setItems(this.associatedPartList);
        // Set up table cells
        addProductPartIDCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        addProductPartNameCol2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        addProductPartInvCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        addProductPartPriceCol2.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        // disable product ID for autogen
        this.addProductIDTextField.setDisable(true);
        
        // set default text
        addProductNameTextField.setText("default");
        addProductInstockTextField.setText("0");
        addProductPriceTextField.setText("0.00");
        addProductMinTextField.setText("0");
        addProductMaxTextField.setText("0"); 
    }
    
}
