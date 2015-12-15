package Mvc.View;

import Mvc.Control.InserimentoOfferteControl;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Simone on 14/12/2015.
 */
public class InserimentoOfferteView {

    private SplitPane layout;
    private Scene scene;
    private StackPane sp1;
    private StackPane sp2;
    private InserimentoOfferteControl inserimentoOfferteControl;

    public InserimentoOfferteView(Stage stage,InserimentoOfferteControl control) throws IOException {

        inserimentoOfferteControl = control;
        double percentageWidth = 0.50;
        double percentageHeight = 0.50;
        layout = new SplitPane();
        layout.setPadding(new Insets(20,0,20,20));

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();

        this.scene = new Scene(layout, percentageWidth, percentageHeight);

        sp1 = buildLeft();
        sp2 = new StackPane();
        layout.getItems().addAll(sp1, sp2);
        layout.setDividerPositions(0.5f, 0.5f);


        stage.setTitle("Inserimento offerte");
        stage.setScene(scene);
        stage.show();
//        final StackPane sp2 = buildRight();


    }


    public void buildRight(int val)
    {
        final GridPane gp = new GridPane();
        sp2.getChildren().clear();
        gp.setAlignment(Pos.TOP_LEFT);
        ColumnConstraints cc = new ColumnConstraints(50, 50, Double.MAX_VALUE,
                Priority.ALWAYS, HPos.LEFT, true);
        gp.getColumnConstraints().addAll(cc, cc);

        switch (val) {
        case 0:
            Label boh1 = new Label("Durata pernotto");
            boh1.setFont(new Font("Arial",15));

            TextField bohField = new TextField();


            gp.add(boh1,0,0);
            gp.add(bohField,1,0);
            break;
            case 1:
                Label boh2 = new Label("Trenino ciuf ciuf");
                boh2.setFont(new Font("Arial",15));

                TextField boh2Field = new TextField();


                gp.add(boh2,0,0);
                gp.add(boh2Field,1,0);
                break;
            case 2:
                Label boh3 = new Label("Museo");
                boh3.setFont(new Font("Arial",15));

                TextField boh3Field = new TextField();


                gp.add(boh3,0,0);
                gp.add(boh3Field,1,0);

        }
        sp2.getChildren().add(gp);
    }


    private void bottone1 (ActionEvent event)
    {

        this.buildRight(0);
    }

    private void bottone2 (ActionEvent event)
    {

        this.buildRight(1);
    }

    private void bottone3 (ActionEvent event)
    {

        this.buildRight(2);
    }



    private StackPane buildLeft()
    {
//        final StackPane sp1 = new StackPane();
//        sp1.getChildren().add(new Button("Button One"));
//        final StackPane sp2 = new StackPane();
//        sp2.getChildren().add(new Button("Button Two"));

          final StackPane sp = new StackPane();

          final GridPane gp = new GridPane();

        gp.setAlignment(Pos.TOP_LEFT);
        ColumnConstraints cc = new ColumnConstraints(50,50, Double.MAX_VALUE,
                Priority.SOMETIMES, HPos.LEFT, true);
        gp.getColumnConstraints().addAll(cc, cc);

//        RowConstraints rc = new RowConstraints(5, 5, Double.MAX_VALUE,
//                Priority.ALWAYS, VPos.TOP, true);
//        gp.getRowConstraints().addAll(rc, rc);


        Label name = new Label("Nome Offerta");
        name.setFont(new Font("Arial",15));

        Label price = new Label("Prezzo");
        price.setFont(new Font("Arial",15));

        Label duration = new Label("Durata");


        TextField priceField = new TextField();
        TextField nameField = new TextField();
        TextField durationField = new TextField();

        ToggleGroup group = new ToggleGroup();
        RadioButton pernottoB = new RadioButton("Pernotto");
        pernottoB.setToggleGroup(group);
        RadioButton trasportoB = new RadioButton("Trasporto");
        trasportoB.setToggleGroup(group);
        RadioButton eventiB = new RadioButton("Eventi");
        eventiB.setToggleGroup(group);

        pernottoB.setOnAction(inserimentoOfferteControl::radioListener);
        trasportoB.setOnAction(inserimentoOfferteControl::radioListener);
        eventiB.setOnAction(inserimentoOfferteControl::radioListener);

        Button okButton = new Button("OK");

        gp.add(name,0,0);
        gp.add(nameField,1,0);

        gp.add(price,0,1);
        gp.add(priceField,1,1);

        gp.add(duration,0,2);
        gp.add(durationField,1,2);

        gp.add(pernottoB,0,3);
        gp.add(trasportoB,0,4);
        gp.add(eventiB,0,5);

        gp.add(okButton,0,7);

        sp.getChildren().add(gp);
        return sp;

    }
}
