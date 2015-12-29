package Mvc.View;


import Mvc.Control.LogInControl;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInView{

    Stage stage;
    LogInControl logInControl;

    public LogInView(Stage primaryStage, LogInControl control) throws IOException {
        stage = primaryStage;
        logInControl = control;
        Parent root = FXMLLoader.load(getClass().getResource("LogInView.fxml"));
        primaryStage.setTitle("Hello World");
        double percentageWidth = 0.20;
        double percentageHeight = 0.20;



        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();


        Scene scene = new Scene(root, percentageWidth, percentageHeight);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public void showPopup(String message)
    {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(message));
        Button button = new Button("OK");
        button.setPrefWidth(100);
        button.setOnAction(logInControl::okListener);
        dialogVbox.getChildren().add(button);
        Scene dialogScene = new Scene(dialogVbox, 200, 100);
        dialog.setScene(dialogScene);
        dialog.show();

    }


}