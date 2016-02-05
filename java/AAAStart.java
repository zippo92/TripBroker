import Mvc.Control.LogInControl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Simone on 05/02/2016.
 */
public class AAAStart extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        LogInControl logInControl = new LogInControl();

        logInControl.start(primaryStage);


    }
}
