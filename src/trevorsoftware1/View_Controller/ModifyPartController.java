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
public class ModifyPartController implements Initializable {
    // initialize variables
    private State state;
    Stage stage;
    Parent root;
    
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
    private TextField ihmod_partCompanyNameTextField;

    @FXML
    private RadioButton ihmod_inhouseRadio;

    @FXML
    private RadioButton ihmod_outsourcedRadio;

    @FXML
    private Button ihmod_partSaveButton;

    @FXML
    private Button ihmod_partCancelButton;
    
    @FXML
    private Label inoroutLabel;

    @FXML
    private ToggleGroup inorout;
    
    public void ihmod_radioHandler (ActionEvent event) {
        // check radio button toggle
        if (ihmod_inhouseRadio.isSelected()) {
            // set label for machineID
            inoroutLabel.setText("MachineID");
            ihmod_partMachineIDTextField.promptTextProperty().setValue("Machine ID");
        } else if (ihmod_outsourcedRadio.isSelected()) {
            // set radio button for company name
            inoroutLabel.setText("Company Name");
            ihmod_partMachineIDTextField.promptTextProperty().setValue("Company name");
        }
    }
    
    @FXML
    void ihmod_partCancelButtonHandler(ActionEvent event) throws IOException {
        // exception control - cancel confirmation
        Alerts.getAlert("cancel").showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    // return to main screen
                    // get reference to the button's stage
                    stage=(Stage) ihmod_partCancelButton.getScene().getWindow();
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
    void ihmod_partSaveButtonHandler(ActionEvent event) throws IOException {
        // initialize variables
        int partID = state.getInventory().assignPartID(); // autogenerate partID
        String name = "";
        double price = 0;
        int inStock = 0;
        int min = 0;
        int max = 0;
        int machineID = 0;
        String companyName = "default";
        Part part = null;
        boolean isValid = false;
        boolean numFormat = false;
        
        // Check radio button then create correct part type
        
        // take input from text fields, parsing if necessary, and assign them to variables 
        // exception controls
        try {
            partID = this.state.getSelectedPart().getPartID();
            name = ihmod_partNameTextField.getText();
            price = Double.parseDouble(ihmod_partPriceTextField.getText());
            inStock = Integer.parseInt(ihmod_partInstockTextField.getText());
            min = Integer.parseInt(ihmod_partMinTextField.getText());
            max = Integer.parseInt(ihmod_partMaxTextField.getText());
            if (ihmod_inhouseRadio.isSelected()) {
                machineID = Integer.parseInt(ihmod_partMachineIDTextField.getText());
            } else if (ihmod_outsourcedRadio.isSelected()) {
                companyName = ihmod_partMachineIDTextField.getText();
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
        } else if (numFormat == false) {
            // validation successful
            isValid = true;
        }
        
        if (isValid) {
            // set values of the selected part using state
            this.state.getSelectedPart().setPartID(partID);
            this.state.getSelectedPart().setName(name);
            this.state.getSelectedPart().setPrice(price);
            this.state.getSelectedPart().setInStock(inStock);
            this.state.getSelectedPart().setMin(min);
            this.state.getSelectedPart().setMax(max);
        
            if (ihmod_inhouseRadio.isSelected() && this.state.getSelectedPart() instanceof Inhouse) {
                // instance of Inhouse with inhouse selected
                ((Inhouse)this.state.getSelectedPart()).setMachineID(machineID);
            } else if (ihmod_outsourcedRadio.isSelected() && this.state.getSelectedPart() instanceof Outsourced) {
                // instance of Outsourced with outsourced selected
                ((Outsourced)this.state.getSelectedPart()).setCompanyName(companyName);
            } else if (ihmod_inhouseRadio.isSelected() && this.state.getSelectedPart() instanceof Outsourced) {
                // instance of Outsourced with inhouse selected, change part
                Part oldPart = this.state.getSelectedPart();
                // set selected part to new Inhouse part created from text fields
                this.state.setSelectedPart(new Inhouse(partID, name, price, inStock, min, max, machineID));
                // update inventory by swapping old part and new part
                this.state.getInventory().updatePart(oldPart, this.state.getSelectedPart());
            } else if (ihmod_outsourcedRadio.isSelected() && this.state.getSelectedPart() instanceof Inhouse) {
                // instance of Inhouse with outsourced selected, change part
                Part oldPart = this.state.getSelectedPart();
                // set selected part to new Outsourced part created from text fields
                this.state.setSelectedPart(new Outsourced(partID, name, price, inStock, min, max, companyName));
                // update inventory by swapping old part and new part
                this.state.getInventory().updatePart(oldPart, this.state.getSelectedPart());
            }
            // clear selected part in state
            this.state.setSelectedPart(null);
            //Go back to main screen
            //get reference to the button's stage
            stage=(Stage) ihmod_partSaveButton.getScene().getWindow();
            //load up other FXML document
            root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        // get instance of state
        this.state = State.getInstance();
        // disable partID text field for automatic ID generation
        this.ihmod_partIDTextField.setDisable(true);
        
        // set textfield inputs to the selected part's data
        if (this.state.getSelectedPart() != null) {
            ihmod_partIDTextField.setText(Integer.toString(state.getSelectedPart().getPartID()));
            ihmod_partNameTextField.setText(state.getSelectedPart().getName());
            ihmod_partInstockTextField.setText(Integer.toString(state.getSelectedPart().getInStock()));
            ihmod_partPriceTextField.setText(Double.toString(state.getSelectedPart().getPrice()));
            ihmod_partMinTextField.setText(Integer.toString(state.getSelectedPart().getMin()));
            ihmod_partMaxTextField.setText(Integer.toString(state.getSelectedPart().getMax()));
            if (state.getSelectedPart() instanceof Inhouse) {  
                ihmod_partMachineIDTextField.setText(Integer.toString(((Inhouse)state.getSelectedPart()).getMachineID()));
            }
            if (state.getSelectedPart() instanceof Outsourced) {  
                ihmod_outsourcedRadio.selectedProperty().set(true);
                ihmod_partMachineIDTextField.setText(((Outsourced)state.getSelectedPart()).getCompanyName());
            } 
        }
    }  
}
