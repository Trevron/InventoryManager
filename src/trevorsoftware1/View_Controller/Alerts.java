/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevorsoftware1.View_Controller;

import javafx.scene.control.Alert;

/**
 *
 * @author TrevTop
 */
public class Alerts {
    private static final Alert numFormatExc = new Alert(Alert.AlertType.ERROR);
    private static final Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static final Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static final Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
    
   
    public static Alert getAlert(String alertType) {
        switch(alertType) {
            case "numFormatExc":
                numFormatExc.setTitle("ERROR");
                numFormatExc.setHeaderText("Number Format Exception");
                numFormatExc.setContentText("Sorry, there was an error.");
                return numFormatExc;
            case "cancel":
                cancelAlert.setTitle("Cancel");
                cancelAlert.setHeaderText("Are you sure you want to continue?");
                cancelAlert.setContentText("Changes will not be saved.");
                return cancelAlert;
            case "save":
                saveAlert.setTitle("Save");
                saveAlert.setHeaderText("Are you sure you want to continue?");
                saveAlert.setContentText("Changes will be saved.");
                return saveAlert;
            case "exit":
                exitAlert.setTitle("Close program");
                exitAlert.setHeaderText("Are you sure you want to close this program?");
                exitAlert.setContentText("Nothing will be saved.");
                return exitAlert;
            default:
                return null;
        }
    }
   
}
