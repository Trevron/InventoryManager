package trevorsoftware1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import trevorsoftware1.Model.State;


/**
 *
 * @author Trevor Metcalf
 */
public class TrevorSoftware1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // load main screen
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/FXMLMainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);   
    }
    // load singleton State class
    private static Class getClass(String classname) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = State.class.getClassLoader();
        }
        return (classLoader.loadClass(classname));
    }
    
}
