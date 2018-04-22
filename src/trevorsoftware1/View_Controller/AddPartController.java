package trevorsoftware1.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import trevorsoftware1.Model.Inhouse;
import trevorsoftware1.Model.Outsourced;
import trevorsoftware1.Model.Part;
import trevorsoftware1.Model.State;



/**
 *
 * @author Trevor Metcalf
 */
public class AddPartController implements Initializable {
    // initialize variables
    private State state;
    Stage stage;
    Parent root;
    
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
        // check radio button toggle
        if (inHouseRadio.isSelected()) {
            // set label for machineID
            inoroutLabel.setText("MachineID");
            machineIDField.promptTextProperty().setValue("Machine ID");
        } else if (outsourcedRadio.isSelected()) {
            // set label for company name
            inoroutLabel.setText("Company Name");
            machineIDField.promptTextProperty().setValue("Company name");
        }
    }
    
    public void outsourcedRadioHandler(ActionEvent event) {
        // check radio button toggle
        if (inHouseRadio.isSelected()) {
            // set label for machineID
            inoroutLabel.setText("MachineID");
            machineIDField.promptTextProperty().setValue("Machine ID");
        } else if (outsourcedRadio.isSelected()) {
            // set label for company name
            inoroutLabel.setText("Company Name");
            machineIDField.promptTextProperty().setValue("Company name");
        }
    }
    
    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        // exception control - cancel confirmation
        Alerts.getAlert("cancel").showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    // return to main screen
                    // get reference to the button's stage
                    stage=(Stage) cancelButton.getScene().getWindow();
                    // load up other FXML document
                    root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println("IOException! Error!");
                }
            }
        });
    }
    
    
    @FXML
    public void saveButtonHandler(ActionEvent event) throws IOException {
        // initialize variables
        int partID = this.state.getInventory().assignPartID(); // autogenerate partID
        String name = "";
        double price = 0;
        int inStock = 0;
        int min = 0;
        int max = 0;
        int machineID = 0;
        String companyName = "default";
        boolean inhouse = true;
        Part part = null;
        boolean isValid = false;
        boolean numFormat = false;
        
        // Take input from text fields, parsing if necessary, and assign them to variables 
        // exception control
        try {
            name = nameField.getText();
            price = Double.parseDouble(priceField.getText());
            inStock = Integer.parseInt(invField.getText());
            min = Integer.parseInt(minField.getText());
            max = Integer.parseInt(maxField.getText());
            if (inHouseRadio.isSelected()) {
                inhouse = true;     
                machineID = Integer.parseInt(machineIDField.getText());
            } else if (outsourcedRadio.isSelected()) {
                inhouse = false;
                companyName = machineIDField.getText();
            }
        } catch (NumberFormatException e) {
            // exception control - number format exception
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
        } else if (inhouse == false && numFormat == false) {
            part = new Outsourced(partID, name, price, inStock, min, max, companyName);
            isValid = true;
        } else if (inhouse == true && numFormat == false) {
            part = new Inhouse(partID, name, price, inStock, min, max, machineID);
            isValid = true;  
        } else {
            // validation succesful
            numFormat = false;
        }
        
        // add created part to inventory 
        if (isValid) {
            // exception control - save confirmation
            Alerts.getAlert("save").showAndWait();
            if (Alerts.getAlert("save").getResult() == ButtonType.OK) {
                this.state.getInventory().addPart(part);
                // return to main screen
                // get reference to the button's stage
                stage=(Stage) cancelButton.getScene().getWindow();
                // load up other FXML document
                root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
  
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        // get instance of state
        this.state = State.getInstance();

        // disable ID field for auto generation
        this.idField.setDisable(true);
        
        // set default textfield inputs
        nameField.setText("default");
        invField.setText("0");
        priceField.setText("0.00");
        minField.setText("0");
        maxField.setText("0");
        machineIDField.setText("0"); 
    }
    
    
}
