package Mvc.View;

import Mvc.Control.AccessoCatalogoControl;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 07/12/2015.
 */
public class AccessoCatalogoView {

    private BorderPane componentLayout;
    private Scene scene;
    private String dep;
    private int Dim_Butt=18;
    private AccessoCatalogoControl accessoCatalogoControl;
    private TabPane tabPane;
    private SplitPane splitOfferte;
    private StackPane of1;
    private StackPane of2;
    private StackPane of3;
    private StackPane pacPane;
    private GridPane gpPern,gpTras,gpEve;


    public AccessoCatalogoView(Stage primaryStage, String utente, AccessoCatalogoControl control) throws IOException {
        dep = utente;
        accessoCatalogoControl = control;
        double percentageWidth = 0.98;
        double percentageHeight = 0.90;
        componentLayout = new BorderPane();
        componentLayout.setPadding(new Insets(20,0,20,20));

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();

        this.scene = new Scene(componentLayout, percentageWidth, percentageHeight);
        buildLeft();
        buildCenter();
        buildTop();


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private SplitPane buildOfferte(){
        SplitPane sp = new SplitPane();



        of1 = BuildOffertePernotto();

        of2 =BuildOfferteTrasporto();

        of3 = BuildOfferteEventi();

        sp.setDividerPositions(0.33f, 0.66f, 0.99f);
        sp.getItems().addAll(of1,of2,of3);


        return sp;
    }



    private StackPane BuildOfferteEventi()
    {
        StackPane sp = new StackPane();

        Label evLabel = new Label("Offerte di eventi");
        evLabel.setFont(new Font("Arial",20));
        gpEve = new GridPane();

        gpEve.setHgap(50);


        gpEve.add(evLabel,1,0,3,1);

        Label nome = new Label("Nome");
        nome.setFont(new Font("Arial",15));

        Label prezzo = new Label("Prezzo");
        prezzo.setFont(new Font("Arial",15));

        Label citta = new Label("Città");
        citta.setFont(new Font("Arial",15));

        Label tipo = new Label("Tipo");
        tipo.setFont(new Font("Arial",15));

        gpEve.add(nome,0,1);
        gpEve.add(prezzo,1,1);
        gpEve.add(citta,2,1);
        gpEve.add(tipo,3,1);

        List<OffertaEvento> offerte  = new ArrayList<OffertaEvento>();

        offerte = accessoCatalogoControl.getOffEven();

        int i=2;
        int app;
        if(offerte!=null)
        for(OffertaEvento offertaEvento : offerte){
            System.out.println(offertaEvento.getNome());
            gpEve.add(new Label(offertaEvento.getNome()),0,i);
            gpEve.add(new Label(Integer.toString(offertaEvento.getPrezzo())+"€"),1,i);
            gpEve.add(new Label(offertaEvento.getCittà()),2,i);
            gpEve.add(new Label(offertaEvento.getTipologia()),3,i);
            i++;

        }


        sp.getChildren().add(gpEve);
        return sp;
    }

    private StackPane BuildOfferteTrasporto()
    {
        StackPane sp = new StackPane();

        Label trasLabel = new Label("Offerte di Trasporto");
        trasLabel.setFont(new Font("Arial",20));


        gpTras = new GridPane();

        gpTras.setHgap(50);


        gpTras.add(trasLabel,1,0,3,1);

        Label nome = new Label("Nome");
        nome.setFont(new Font("Arial",15));

        Label prezzo = new Label("Prezzo");
        prezzo.setFont(new Font("Arial",15));

        Label citta = new Label("Città");
        citta.setFont(new Font("Arial",15));

        Label cittaP = new Label("Città Partenza");
        citta.setFont(new Font("Arial",15));

        Label tipo = new Label("Tipo");
        tipo.setFont(new Font("Arial",15));

        gpTras.add(nome,0,1);
        gpTras.add(prezzo,1,1);
        gpTras.add(citta,2,1);
        gpTras.add(cittaP,3,1);
        gpTras.add(tipo,4,1);

        List<OffertaTrasporto> offerte  = new ArrayList<OffertaTrasporto>();

        offerte = accessoCatalogoControl.getOffTras();

        int i=2;
        int app;
        if(offerte!=null)
            for(OffertaTrasporto offertaTrasporto : offerte){
                System.out.println(offertaTrasporto.getNome());
                gpTras.add(new Label(offertaTrasporto.getNome()),0,i);
                gpTras.add(new Label(Integer.toString(offertaTrasporto.getPrezzo())+"€"),1,i);
                gpTras.add(new Label(offertaTrasporto.getCittà()),2,i);
                gpTras.add(new Label(offertaTrasporto.getCittàPartenza()),3,i);
                gpTras.add(new Label(offertaTrasporto.getTipologia()),4,i);
                i++;

            }

        sp.getChildren().add(gpTras);

        return sp;
    }

    private StackPane BuildOffertePernotto()
    {
        StackPane sp = new StackPane();

        Label pernLabel = new Label("Offerte di Pernotto");
        pernLabel.setFont(new Font("Arial",20));


        gpPern = new GridPane();

        gpPern.setHgap(50);


        gpPern.add(pernLabel,1,0,3,1);

        Label nome = new Label("Nome");
        nome.setFont(new Font("Arial",15));

        Label prezzo = new Label("Prezzo");
        prezzo.setFont(new Font("Arial",15));

        Label citta = new Label("Città");
        citta.setFont(new Font("Arial",15));

        Label tipo = new Label("Tipo");
        tipo.setFont(new Font("Arial",15));

        Label notti = new Label("Notti");
        notti.setFont(new Font("Arial",15));


        gpPern.add(nome,0,1);
        gpPern.add(prezzo,1,1);
        gpPern.add(citta,2,1);
        gpPern.add(tipo,3,1);
        gpPern.add(notti,4,1);


        List<OffertaPernotto> offerte  = new ArrayList<OffertaPernotto>();

        offerte = accessoCatalogoControl.getOffPern();

        int i=2;
        int app;
        if(offerte!=null)
            for(OffertaPernotto offertaPernotto : offerte){
                System.out.println(offertaPernotto.getNome());
                gpPern.add(new Label(offertaPernotto.getNome()),0,i);
                gpPern.add(new Label(Integer.toString(offertaPernotto.getPrezzo())+"€"),1,i);
                gpPern.add(new Label(offertaPernotto.getCittà()),2,i);
                gpPern.add(new Label(offertaPernotto.getTipologia()),3,i);
                gpPern.add(new Label(Integer.toString(offertaPernotto.getNumeroNotti())),4,i);
                i++;

            }


        sp.getChildren().add(gpPern);

        return sp;
    }


    private StackPane buildPacchetti(){
        StackPane sp = new StackPane();
        Label label1 = new Label("label gdsgsdg");
        sp.getChildren().add(label1);



        return sp;
    }


    private void buildCenter()
    {

        tabPane = new TabPane();
//        tabPane.setStyle("-fx-background-color: lightsteelblue;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");
        Tab tabA = new Tab();
        tabA.setText("Offerte");
        tabA.setClosable(false);
        splitOfferte = buildOfferte();
        tabA.setContent(splitOfferte);
        tabPane.getTabs().add(tabA);

        Tab tabB = new Tab();
        tabB.setText("Pacchetti");
        tabB.setClosable(false);
        pacPane = buildPacchetti();
        tabB.setContent(pacPane);
        tabPane.getTabs().add(tabB);



        componentLayout.setCenter(tabPane);



    }

    private void buildTop() {

        BorderPane topLayout = new BorderPane();

        Label divider = new Label();
        divider.setMaxHeight(1);
        divider.setMinHeight(1);
        divider.setMinWidth(Screen.getPrimary().getBounds().getWidth());
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
        topLayout.setStyle("-fx-background-color: lightgrey;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");


        //Add topLayout (a BorderPane Manager) to App Layout.
        componentLayout.setTop(topLayout);

    }


    private VBox setButtonBox(String dep)
    {
        VBox buttonBox = new VBox();

        buttonBox.setAlignment(Pos.TOP_CENTER);

        buttonBox.setSpacing(10);

//        Dipendente dipendente = Dipendente.valueOf(dep);

        switch (dep) {

            case "Scout":

                    //Add Click Event.
//        btnExample1.setOnAction(new EventHandler() e){
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Exampletton Clicked.");
//                layout.setCenter(example1());
//            }
//

                    Button offerte = new Button() ;
                offerte.setText("Inserimento offerte");
                offerte.setFont(new Font(Dim_Butt));
                offerte.setMaxWidth(Double.MAX_VALUE);
                offerte.setOnAction(accessoCatalogoControl::inserimentoOfferte);




                Button contratti = new Button();
                contratti.setText("Gestione contratti");
                contratti.setFont(new Font(Dim_Butt));
                contratti.setMaxWidth(Double.MAX_VALUE);


                Button ricerca = new Button();
                ricerca.setText("Ricerca offerte");
                ricerca.setFont(new Font(Dim_Butt));
                ricerca.setMaxWidth(Double.MAX_VALUE);
                buttonBox.getChildren().addAll(offerte, contratti, ricerca);
                break;

            case "Designer":

                Button aggrega = new Button() ;
                aggrega.setText("Aggregazione Offerte");
                aggrega.setFont(new Font(Dim_Butt));
                aggrega.setMaxWidth(Double.MAX_VALUE);


                Button costi = new Button();
                costi.setText("Aggiorna costi offerte");
                costi.setFont(new Font(Dim_Butt));
                costi.setMaxWidth(Double.MAX_VALUE);



                buttonBox.getChildren().addAll(aggrega, costi);
                break;

            case "Admin":
                Button aggiorna = new Button() ;
                aggiorna.setText("Aggiorna offerte con criterio");
                aggiorna.setFont(new Font(Dim_Butt));
                aggiorna.setMaxWidth(Double.MAX_VALUE);

                Button andamento = new Button();
                andamento.setText("Visualizza andamento economico");
                andamento.setFont(new Font(Dim_Butt));
                andamento.setMaxWidth(Double.MAX_VALUE);



                Button log = new Button();
                log.setText("Visualizza log");
                log.setFont(new Font(Dim_Butt));
                log.setMaxWidth(Double.MAX_VALUE);


                buttonBox.getChildren().addAll(aggiorna,andamento,log);

            case "Agente":
                Button prenotazione = new Button() ;
                prenotazione.setText("Prenotazione viaggio");
                prenotazione.setMaxWidth(Double.MAX_VALUE);
                prenotazione.setFont(new Font(20));
                buttonBox.getChildren().addAll(prenotazione);

        }
        return buttonBox;
    }

    private void buildLeft() {

        BorderPane leftLayout = new BorderPane();

        leftLayout.setStyle("-fx-background-color: cadetblue;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");


        // Create a faux border-right effect using a Label.
        Label divider = new Label();
        divider.setPrefWidth(1);
        divider.setMinHeight(Screen.getPrimary().getBounds().getHeight());
        leftLayout.setRight(divider);

        //Place all demonstration buttons in a Vercial Box.





//


        //Add VBox to leftLayout.
        leftLayout.setCenter(setButtonBox(dep));

        //Place into Application.
        componentLayout.setLeft(leftLayout);

    }


}
