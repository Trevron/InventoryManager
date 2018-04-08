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
import static trevorsoftware1.View_Controller.FXMLMainController.inv;


/**
 *
 * @author TrevTop
 */
public class AddPartController implements Initializable {
    
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
    
        Alerts.getAlert("cancel").showAndWait().ifPresent(response -> {
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
                    System.out.println("IOException! Error!");
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
                Alerts.getAlert("numFormatExc").showAndWait();
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
                Alerts.getAlert("numFormatExc").showAndWait();
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
  
    public void initialize (URL url, ResourceBundle rb) {
        // disable ID field for auto generation
        this.idField.setDisable(true);
        
        
    }
    
    
}
