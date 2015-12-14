package Mvc.View;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInView{

    BorderPane componentLayout;
//    Scene scene;

    public LogInView(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogInView.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}