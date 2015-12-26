package Mvc.View;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
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
        double percentageWidth = 0.20;
        double percentageHeight = 0.20;



        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();


        Scene scene = new Scene(root, percentageWidth, percentageHeight);
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}