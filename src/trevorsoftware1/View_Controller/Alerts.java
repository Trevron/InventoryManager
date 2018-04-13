/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevorsoftware1.View_Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author TrevTop
 */
public class Alerts {
    private static final Alert numFormatExc = new Alert(Alert.AlertType.ERROR);
    private static final Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static final Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static final Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static final Alert minOverMax = new Alert(Alert.AlertType.ERROR);
    private static final Alert minUnderZero = new Alert(Alert.AlertType.ERROR);
    private static final Alert invUnderMin = new Alert(Alert.AlertType.ERROR);
    private static final Alert invOverMax = new Alert(Alert.AlertType.ERROR);
    private static final Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static final Alert nullSelect = new Alert(Alert.AlertType.ERROR);
   
    public static Alert getAlert(String alertType) {
        switch(alertType) {
            case "numFormatExc":
                numFormatExc.setTitle("ERROR");
                numFormatExc.setHeaderText("Number Format Exception");
                numFormatExc.setContentText("Sorry, there was an error.");
                return numFormatExc;
            case "cancel":
                cancelAlert.setTitle("Cancel?");
                cancelAlert.setHeaderText("Are you sure you want to continue?");
                cancelAlert.setContentText("Changes will not be saved.");
                return cancelAlert;
            case "save":
                saveAlert.setTitle("Save?");
                saveAlert.setHeaderText("Are you sure you want to continue?");
                saveAlert.setContentText("Changes will be saved.");
                return saveAlert;
            case "exit":
                exitAlert.setTitle("Close program?");
                exitAlert.setHeaderText("Are you sure you want to close this program?");
                exitAlert.setContentText("Nothing will be saved.");
                return exitAlert;
            case "minOverMax":
                minOverMax.setTitle("Error with min!");
                minOverMax.setHeaderText("Minimum cannot be greater than maximum.");
                minOverMax.setContentText("Please check your numbers and try again.");
                return minOverMax;
            case "minUnderZero":
                minUnderZero.setTitle("Error with min!");
                minUnderZero.setHeaderText("Minimum cannot be less than zero.");
                minUnderZero.setContentText("Please check your numbers and try again.");
                return minUnderZero;
            case "invUnderMin":
                invUnderMin.setTitle("Error with inv!");
                invUnderMin.setHeaderText("Inventory cannot be less than min.");
                invUnderMin.setContentText("Please check your numbers and try again.");
                return invUnderMin;
            case "invOverMax":
                invOverMax.setTitle("Error with inv!");
                invOverMax.setHeaderText("Inventory cannot be more than max.");
                invOverMax.setContentText("Please check your numbers and try again.");
                return invOverMax;
            case "delete":
                deleteAlert.setTitle("Delete?");
                deleteAlert.setHeaderText("Are you sure you want to delete?");
                deleteAlert.setContentText("This cannot be undone.");
                return deleteAlert;
            case "nullSelect":
                nullSelect.setTitle("Null Selection");
                nullSelect.setHeaderText("You have not selected anything.");
                nullSelect.setContentText("Highlight something and try again.");
                return nullSelect;
            default:
                return null;
        }
    }
   
}
