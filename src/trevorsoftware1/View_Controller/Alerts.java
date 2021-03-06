package trevorsoftware1.View_Controller;

import javafx.scene.control.Alert;

/**
 *
 * @author Trevor Metcalf
 */
public class Alerts {
    // initialize alerts
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
    private static final Alert noParts = new Alert(Alert.AlertType.ERROR);
    private static final Alert lowPrice = new Alert(Alert.AlertType.ERROR);
    private static final Alert partDelete = new Alert(Alert.AlertType.ERROR);
    private static final Alert productDelete = new Alert(Alert.AlertType.ERROR);
   
    // This class constructs alerts based on the required exceptions 
    // The method getAlert() is used to access the alerts from the various FXML controllers
    // getAlert() also defines the various text displayed by each alert
    // The alerts pass on useful information to the end user in popup style windows
    
    public static Alert getAlert(String alertType) {
        switch(alertType) {
            case "numFormatExc":
                numFormatExc.setTitle("NumberFormatException");
                numFormatExc.setHeaderText("Please check your inputs.");
                numFormatExc.setContentText("A non numerical character is trying to be passed as an integer");
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
            case "noParts":
                noParts.setTitle("No associated parts!");
                noParts.setHeaderText("There are no parts associated with this product.");
                noParts.setContentText("New products must contain at least one part.");
                return noParts;
            case "lowPrice":
                lowPrice.setTitle("Price too low!");
                lowPrice.setHeaderText("Please adjust price of product.");
                lowPrice.setContentText("Products cannot cost less than the price of the associated parts.");
                return lowPrice;
            case "partDelete":
                partDelete.setTitle("Cannot delete!");
                partDelete.setHeaderText("Part is associated with one or more product.");
                partDelete.setContentText("Parts cannot be deleted if they are associated with a product.");
                return partDelete;
            case "productDelete":
                productDelete.setTitle("Cannot delete!");
                productDelete.setHeaderText("Product has parts associated with it.");
                productDelete.setContentText("Products cannot be deleted if they have associated parts.");
                return productDelete;
            default:
                return null;
        }
    }
   
}
