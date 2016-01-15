package Mvc.View;

import Mvc.Control.VisualizzaLogControl;
import Mvc.Model.entityPackage.Log;
import Patterns.DAOFactory.DAOFactory;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 12/01/2016.
 */
public class VisualizzaLogView implements GpColleague{


    Scene scene;
    BorderPane layout;
    GpMediatorImpl gpMediator;
    VisualizzaLogControl visualizzaLogControl;
    GridPane gridPane;



    public VisualizzaLogView(Stage stage,GpMediatorImpl mediator,VisualizzaLogControl control) {

        visualizzaLogControl = control;
        double percentageWidth = 0.40;
        double percentageHeight = 0.40;
        layout = new BorderPane();
        layout.setPadding(new Insets(20, 0, 20, 20));

        gpMediator = mediator;
        gpMediator.addColleague(this);

        buildCenter();


        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();

        this.scene = new Scene(layout, percentageWidth, percentageHeight);


        scene.getStylesheets().add("JMetroLightTheme.css");
        stage.getIcons().add(new Image("icon.png"));

        stage.setTitle("Visualizza log");
        stage.setScene(scene);
        stage.show();

    }

    public void send(GridPane gp) {
        gpMediator.send(gp,this);
    }
    public GpMediator getGpMediator() {return gpMediator;}

    public void receive(GridPane gp) {
        //Never Reched
    }

    public void fillPane(List<Log> logs)
    {

        int i=2;
        for(Log log : logs)
        {

            gridPane.add(new Label(log.getUser().getRuolo()),0,i);

            gridPane.add(new Label(log.getUser().getNome()),1,i);

            gridPane.add(new Label(log.getAzione()),2,i);

            gridPane.add(new Label(log.getDate().toString()),3,i);

            gridPane.add(new Label(log.getTime().toString()),4,i);

            gridPane.add(new Separator(),0,i+1,5,1);

            i+=2;

        }

    }


    private void buildCenter() {
        ScrollPane scrollPane = new ScrollPane();

        gridPane = new GridPane();
        gridPane.setId("gp");

        this.send(gridPane);

        gridPane.setHgap(20);
        gridPane.setVgap(20);


        List<Log> logs = new ArrayList<Log>();

        logs = DAOFactory.getLogDAO().getList();






        Label ruolo = new Label("Ruolo");
        ruolo.getStyleClass().add("text-title");

        Label nome = new Label("Nome");
        nome.getStyleClass().add("text-title");


        Label azione = new Label("Azione");
        azione.getStyleClass().add("text-title");


        Label data = new Label("Data");
        data.getStyleClass().add("text-title");


        Label ora = new Label ("Ora");
        ora.getStyleClass().add("text-title");



        gridPane.add(ruolo,0,0);

        gridPane.add(nome,1,0);

        gridPane.add(azione,2,0);

        gridPane.add(data,3,0);

        gridPane.add(ora,4,0);

        gridPane.add(new Separator(),0,1,5,1);



        fillPane(logs);


        GridPane filtro = new GridPane();
        filtro.setId("filtro");

        filtro.setHgap(20);
        filtro.setVgap(10);



        this.send(filtro);



        Button id = new Button("Filtra per id");
        id.setPrefWidth(200);
        id.setOnAction(visualizzaLogControl::idfilter);


        TextField idField = new TextField();
        idField.setPromptText("id");
        idField.setPrefWidth(200);

        Button ruoloButton = new Button("Filtra per ruolo");
        ruoloButton.setPrefWidth(200);
        ruoloButton.setOnAction(visualizzaLogControl::rolefilter);

        Button all = new Button("Mostra tutti");
        all.setPrefWidth(200);
        all.setOnAction(visualizzaLogControl::showall);




        ObservableList<String> items = FXCollections.observableArrayList (
                "Scout", "Admin", "Designer", "Agente");

        final ComboBox comboBox = new ComboBox(items);
        comboBox.setPromptText("ruolo");
        comboBox.setPrefWidth(200);




        filtro.add(id,0,1);
        filtro.add(idField,1,1);

        filtro.add(ruoloButton,0,2);
        filtro.add(comboBox,1,2);
        filtro.add(all,0,3);

        Label text = new Label("Visualizza log");
        text.setFont(new Font("Arial", 20));

        scrollPane.setContent(gridPane);


        layout.setCenter(scrollPane);

        BorderPane borderPane = new BorderPane();

        borderPane.setTop(new Separator());

        borderPane.setCenter(filtro);

        layout.setBottom(borderPane);
//        layout.getChildren().add(scrollPane);
//
//        layout.getChildren().add(filtro);



    }


}
