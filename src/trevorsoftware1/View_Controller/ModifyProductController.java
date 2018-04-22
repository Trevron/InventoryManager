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
 * @author Trevor Metcalf
 */
public class ModifyProductController implements Initializable {
    
    private ObservableList<Part> partList, associatedPartList;
    private ArrayList<Part> associatedParts = new ArrayList();
    private State state;
    Stage stage;
    Parent root;

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
        // set selected part in state to the highlighted part from table1
        this.state.setSelectedPart(modProductTable1.getSelectionModel().getSelectedItem());
        if(this.state.getSelectedPart() != null) {
            // add selected part to associated parts
            associatedParts.add(this.state.getSelectedPart());
            // clear selected part from state
            this.state.setSelectedPart(null);
            // update observable list 
            this.associatedPartList.clear();
            this.associatedPartList.addAll(associatedParts);
        } else {
            // Show error stating nothing was selected
            Alerts.getAlert("nullSelect").showAndWait();
        }  
    }

    @FXML
    void modProductCancelButtonHandler(ActionEvent event) throws IOException {
        // Display confirmation
        Alerts.getAlert("cancel").showAndWait();
        if (Alerts.getAlert("cancel").getResult() == ButtonType.OK) {
            // go back to main screen
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
    void modProductDeleteButtonHandler(ActionEvent event) {
        // set selected part based off of the highlighted part from the tableview
        this.state.setSelectedPart(modProductTable2.getSelectionModel().getSelectedItem());
        if(this.state.getSelectedPart() != null) {
            // Show deletion confirmation
            Alerts.getAlert("delete").showAndWait();
            if (Alerts.getAlert("delete").getResult() == ButtonType.OK) {
                // delete part from associated parts list and update the observable
                associatedParts.remove(this.state.getSelectedPart());
                this.state.setSelectedPart(null);
                this.associatedPartList.clear();
                this.associatedPartList.addAll(associatedParts);
            }
        } else {
            // ERROR - Nothing was selected -
            Alerts.getAlert("nullSelect").showAndWait();
        }
    }

    @FXML
    void modProductSaveButtonHandler(ActionEvent event) throws IOException {
        // initialize values
        String name = "default";
        double price = 0.0;
        int inStock = 0;
        int min = 0;
        int max = 0;
        boolean isValid = false;
        boolean numFormat = false;
        // make sure the correct inputs are passed in
        // exception controls
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
        // exception controls
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
            // validation successful
            isValid = true;
        }
        
        if (isValid) {
            // if exception controls are passed, set the modifications to selected product
            this.state.getSelectedProduct().setName(name);
            this.state.getSelectedProduct().setPrice(price);
            this.state.getSelectedProduct().setInStock(inStock);
            this.state.getSelectedProduct().setMin(min);
            this.state.getSelectedProduct().setMax(max);
            this.state.getSelectedProduct().setAssociatedParts(associatedParts);
            
            // return to main screen 
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
        // clear previous search from table
        this.partList.clear();
        // lookupPart returns an arraylist of parts matching the String input
        String partName = modProductSearchTextField.getText();
        ArrayList<Part> foundParts = this.state.getInventory().lookupPart(partName);
        this.partList.addAll(foundParts);
        //add returned parts to observable list
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
        // get instance of State
        this.state = State.getInstance();
        // add product part table 1
        // get parts from inventory and add them to the observable array list
        this.partList = FXCollections.observableArrayList();
        this.partList.clear();
        this.partList.addAll(state.getInventory().getAllParts());
        this.modProductTable1.setItems(this.partList);
        // Set up table cells
        modProductPartIDCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        modProductPartNameCol1.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        modProductPartInvCol1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        modProductPartPriceCol1.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
  
        
        // add product associated parts table 2
        // get products from inventory and add them to the observable array list
        this.associatedPartList = FXCollections.observableArrayList();
        this.associatedPartList.clear();
        this.associatedParts.addAll(this.state.getSelectedProduct().getAssociatedParts());
        this.associatedPartList.addAll(associatedParts);
        this.modProductTable2.setItems(this.associatedPartList);
        // Set up table cells
        modProductPartIDCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        modProductPartNameCol2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        modProductPartInvCol2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        modProductPartPriceCol2.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        // disable product ID for automatic geerationn
        this.modProductPartIDTextField.setDisable(true);
        
        
        // input current values into textfields
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
