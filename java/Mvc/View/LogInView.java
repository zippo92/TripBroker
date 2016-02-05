package Mvc.View;


import Mvc.Control.LogInControl;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInView implements GpColleague{

    Stage stage;
    LogInControl logInControl;
    GpMediator gpMediator;

    public LogInView(Stage primaryStage, LogInControl control, GpMediator mediator) throws IOException {
        stage = primaryStage;


        stage.getIcons().add(new Image("icon.png"));


        logInControl = control;

        gpMediator = mediator;
        mediator.addColleague(this);

        GridPane gp = new GridPane();


        gp.prefHeight(363.0);
        gp.prefWidth(364.0);
        gp.setVgap(10);


        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        gp.getColumnConstraints().add(columnConstraints);



        BorderPane topLayout = new BorderPane();

        Label divider = new Label();
        divider.setMaxHeight(1);
        divider.setMinHeight(1);
//        divider.setMinWidth(Screen.getPrimary().getBounds().getWidth());
        topLayout.setBottom(divider);

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.TOP_CENTER);
        Label title = new Label();
        title.setText("TRIP BROKER");
        title.setFont(new Font("Segoe Print",50));

        //Add Title label to titleBox
        titleBox.getChildren().add(title);

        //Add Title Box (with label) to topLayout
        topLayout.setCenter(titleBox);
//        topLayout.setStyle("-fx-background-color: lightgrey;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");



        TextField name = new TextField();
        name.setPromptText("Nome utente");
        name.setPrefWidth(300.0);
        name.setPrefHeight(50.0);

        TextField psw = new TextField();
        psw.setPromptText("Password");
        psw.setPrefWidth(300.0);
        psw.setPrefHeight(50.0);


        Button logIn = new Button("Log in");
        logIn.setPrefWidth(100);
        logIn.setDefaultButton(true);
        logIn.setOnAction(logInControl::logInAction);

        gp.add(topLayout,0,0);
        gp.add(name,0,1);
        gp.add(psw,0,2);
        gp.add(logIn,0,3);

        gpMediator.send(gp,this);

        primaryStage.setTitle("Trip Broker");
        double percentageWidth = 0.30;
        double percentageHeight = 0.25;



        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();


        Scene scene = new Scene(gp, percentageWidth, percentageHeight);

        scene.getStylesheets().add("JMetroLightTheme.css");


        primaryStage.setScene(scene);
        primaryStage.show();

    }





    public void send(GridPane gp) {
        gpMediator.send(gp,this);
    }
    public GpMediator getGpMediator() {return gpMediator;}

    public void receive(GridPane gp) {
        //Never Reched
    }
    public void showPopup(String message)
    {
        final Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(stage);
        dialog.getIcons().add(new Image("icon.png"));

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