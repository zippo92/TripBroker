package Mvc.View;

import Mvc.Control.InserimentoOfferteControl;
import Mvc.LimitedTextField;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Simone on 14/12/2015.
 */
public class InserimentoOfferteView implements GpColleague {

    private SplitPane layout;
    private Scene scene;
    private StackPane sp1;
    private StackPane sp2;
    private InserimentoOfferteControl inserimentoOfferteControl;
    private GpMediator gpMediator;


    /*
    * è formato da una split pane con linea centrale verticale ,a sinistra le informazioni comuni a tutte le offerte
    * a destra invece quelle relative all'offerta selezionata nel radiogroup
    *
    * */
    public InserimentoOfferteView(Stage stage, InserimentoOfferteControl control, GpMediator mediator) throws IOException {

        gpMediator = mediator;
        gpMediator.addColleague(this);
        inserimentoOfferteControl = control;
        double percentageWidth = 0.40;
        double percentageHeight = 0.40;
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

        scene.getStylesheets().add("JMetroLightTheme.css");
        stage.getIcons().add(new Image("icon.png"));

        stage.setTitle("Inserimento offerte");
        stage.setScene(scene);
        stage.show();
//        final StackPane sp2 = buildRight();
    }

    /*
    *
    * */
    public void send(GridPane gp) {
            gpMediator.send(gp,this);
            }
    public GpMediator getGpMediator() {return gpMediator;}

    public void receive(GridPane gp) {
        //Never Reched
    }


    /*
    *  Builda la zona a destra a seconda del valore passatogli, 0 OffertaPernotto 1 OffertaTrasporto 2 OffertaEvento
    * */
    public void buildRight(int val)
    {
        sp2.getChildren().clear();
        final GridPane gp = new GridPane();
        gp.setId("Right");
        gp.setAlignment(Pos.TOP_LEFT);
        ColumnConstraints cc = new ColumnConstraints(50,50, Double.MAX_VALUE,
                Priority.ALWAYS, HPos.LEFT, true);
        gp.getColumnConstraints().addAll(cc, cc);

//       RowConstraints rc = new RowConstraints(5, 5, Double.MAX_VALUE,
//                Priority.ALWAYS, VPos.TOP, true);
//       gp.getRowConstraints().addAll(rc, rc);
        gp.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        gp.setVgap(10);

        switch (val) {
        case 0:
            Label notti = new Label("Numero notti");
            notti.setFont(new Font("Arial",15));

            LimitedTextField nottiFIeld = new LimitedTextField();

            Label tipo = new Label("Tipologia");
            tipo.setFont(new Font("Arial",15));

            ObservableList<String> items = FXCollections.observableArrayList (
                    "B&B", "Hotel", "Appartamento", "Casa Vacanze");

            final ComboBox comboBox = new ComboBox(items);
            comboBox.setPromptText("tipologia");

            ObservableList<String> itemsStar = FXCollections.observableArrayList (
                    "★☆☆☆☆", "★★☆☆☆", "★★★☆☆", "★★★★☆", "★★★★★");

            final ComboBox comboBoxStar = new ComboBox(itemsStar);
            comboBoxStar.setPromptText("Stelle");

            Label numNotti = new Label("Numero stelle");
            numNotti.setFont(new Font("Arial",15));


            gp.add(notti,0,0);
            gp.add(nottiFIeld,1,0);

            gp.add(tipo,0,1);
            gp.add(comboBox,1,1);

            gp.add(numNotti,0,2);
            gp.add(comboBoxStar,1,2);

            break;
            case 1:
                Label citta = new Label("Città di partenza");
                citta.setFont(new Font("Arial",15));

                LimitedTextField cittaField = new LimitedTextField();

                Label tipoTras = new Label("Tipologia");
                tipoTras.setFont(new Font("Arial",15));


                ObservableList<String> itemsTrasp = FXCollections.observableArrayList (
                        "Treno", "Aereo", "Nave", "Autobus");

                final ComboBox comboBoxTrasp = new ComboBox(itemsTrasp);
                comboBoxTrasp.setPromptText("tipologia");

                Label durata = new Label("Durata viaggio");
                durata.setFont(new Font("Arial",15));

                LimitedTextField durataField = new LimitedTextField();

                gp.add(citta,0,0);
                gp.add(cittaField,1,0);

                gp.add(tipoTras,0,1);
                gp.add(comboBoxTrasp,1,1);

                gp.add(durata,0,2);
                gp.add(durataField,1,2);
                break;
            case 2:
                Label tipoEvento = new Label("Tipologia evento");
                tipoEvento.setFont(new Font("Arial",15));

                ObservableList<String> itemsEvent = FXCollections.observableArrayList (
                        "Museo", "Concerto", "Visita guidata", "Cinema" , "Teatro");

                final ComboBox comboBoxEventi = new ComboBox(itemsEvent);


                gp.add(tipoEvento,0,0);
                gp.add(comboBoxEventi,1,0);

        }

        Button okButton = new Button("OK");
        okButton.setMaxWidth(100.0);
        okButton.setOnAction(inserimentoOfferteControl::inserimentoListener);

        this.send(gp);
        sp2.getChildren().add(gp);
        sp2.setAlignment(Pos.BOTTOM_RIGHT);
        sp2.getChildren().add(okButton);

    }


    /*
    *
    *  costruisce la zona a sinistra, comune a tutte le offerte
    *
    *   i radiobutton hanno un listener nel controllore che permette di far apparire le informazioni specifiche
    *   nell'altra colonna
    * */
    private StackPane buildLeft()
    {
//        final StackPane sp1 = new StackPane();
//        sp1.getChildren().add(new Button("Button One"));
//        final StackPane sp2 = new StackPane();
//        sp2.getChildren().add(new Button("Button Two"));

        final StackPane sp = new StackPane();

        final GridPane gp = new GridPane();

        gp.setId("Left");
        gp.setAlignment(Pos.TOP_LEFT);
        ColumnConstraints cc = new ColumnConstraints(50,50, Double.MAX_VALUE,
                Priority.ALWAYS, HPos.LEFT, true);
        gp.getColumnConstraints().addAll(cc, cc);

        gp.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        gp.setVgap(10);

        Label name = new Label("Nome Offerta");
        name.setFont(new Font("Arial",15));

        Label price = new Label("Prezzo");
        price.setFont(new Font("Arial",15));

        Label citta = new Label("Città");
        citta.setFont(new Font("Arial",15));

        Label data = new Label("data");
        data.setFont(new Font("Arial",15));

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());



        LimitedTextField priceField = new LimitedTextField();

        LimitedTextField nameField = new LimitedTextField();
        
        LimitedTextField cittaField = new LimitedTextField();

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


        gp.add(name,0,0);
        gp.add(nameField,1,0);

        gp.add(price,0,1);
        gp.add(priceField,1,1);

        gp.add(citta,0,2);
        gp.add(cittaField,1,2);

        gp.add(pernottoB,0,3);
        gp.add(trasportoB,0,4);
        gp.add(eventiB,0,5);


        gp.add(data,0,6);
        gp.add(datePicker,1,6);

        this.send(gp);
        sp.getChildren().add(gp);
        return sp;

    }


}
