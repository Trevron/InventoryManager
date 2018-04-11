/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author TrevTop
 */
public class ModifyPartController implements Initializable {
    
    private State state;
    
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
        if (ihmod_inhouseRadio.isSelected()) {
            System.out.println("Inhouse part.");
            inoroutLabel.setText("MachineID");
            ihmod_partMachineIDTextField.promptTextProperty().setValue("Machine ID");
        } else if (ihmod_outsourcedRadio.isSelected()) {
            System.out.println("Outsourced part.");
            inoroutLabel.setText("Company Name");
            ihmod_partMachineIDTextField.promptTextProperty().setValue("Company name");
        }
    }
    
    
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
    void ihmod_partSaveButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Save button pressed!");
        
        int partID = state.getInventory().assignPartID(); // autogenerate partID
        String name = "";
        double price = 0;
        int inStock = 0;
        int min = 0;
        int max = 0;
        int machineID = 0;
        String companyName = "default";
        Part part = null;
        
        // Check radio button then create correct part type
        
        // Take input from text fields, parsing if necessary, and assign them to variables 
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
            System.err.println("NumberFormatException: " + e.getMessage());
            Alerts.getAlert("numFormatExc").showAndWait();
        }

        this.state.getSelectedPart().setPartID(partID);
        this.state.getSelectedPart().setName(name);
        this.state.getSelectedPart().setPrice(price);
        this.state.getSelectedPart().setInStock(inStock);
        this.state.getSelectedPart().setMin(min);
        this.state.getSelectedPart().setMax(max);
        if (ihmod_inhouseRadio.isSelected() && this.state.getSelectedPart() instanceof Inhouse) {
            System.out.println("Inhouse part modified.");
            ((Inhouse)this.state.getSelectedPart()).setMachineID(machineID);
        } else if (ihmod_outsourcedRadio.isSelected() && this.state.getSelectedPart() instanceof Outsourced) {
            System.out.println("Outsourced part modified.");
            ((Outsourced)this.state.getSelectedPart()).setCompanyName(companyName);
        } else if (ihmod_inhouseRadio.isSelected() && this.state.getSelectedPart() instanceof Outsourced) {
            System.out.println("Outsourced part changed to inhouse.");
            Part oldPart = this.state.getSelectedPart();
            this.state.setSelectedPart(new Inhouse(partID, name, price, inStock, min, max, machineID));
            // create update part method in inventory to replace one part with another.
        } else if (ihmod_outsourcedRadio.isSelected() && this.state.getSelectedPart() instanceof Inhouse) {
            System.out.println("Inhouse part changed to outsourced.");
        }
   
        this.state.setSelectedPart(null);
        
        //Go back to main screen
        Stage stage;
        Parent root;

        //get reference to the button's stage
        stage=(Stage) ihmod_partSaveButton.getScene().getWindow();
        //load up other FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        this.state = State.getInstance();
        
        this.ihmod_partIDTextField.setDisable(true);
        
        
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
