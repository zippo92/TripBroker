package Mvc.View;

import Mvc.Control.AccessoCatalogoControl;
import Mvc.TipoOfferta;
import Patterns.CbMediator.CbColleague;
import Patterns.CbMediator.CbMediator;
import Patterns.CbMediator.CbMediatorImpl;
import entityPackage.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 07/12/2015.
 */
public class AccessoCatalogoView implements CbColleague{

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
    private GridPane gpPern,gpTras,gpEve,gpPack;
    private int gpPernRow,gpTrasRow,gpEveRow,gpPackRow;
    private List<ImageView> infoEve = new ArrayList<ImageView>();
    private List<ImageView> infoTras = new ArrayList<ImageView>();
    private List<ImageView> infoPern = new ArrayList<ImageView>();
    private Image image;
    private Stage stage;
    private List<CheckBox> checkBoxes;
    private CbMediatorImpl cbMediator;
    private Boolean check;
    private List<List<Label>> packRow;


    /*
    * Il costruttore costruisce la schermata di accesso catalogo
    *
    *
    * */

    public AccessoCatalogoView(Stage primaryStage, String utente, AccessoCatalogoControl control,CbMediatorImpl mediator) throws IOException {




        cbMediator = mediator;
        cbMediator.addColleague(this);
        check = false ;
        dep = utente;
        accessoCatalogoControl = control;
        double percentageWidth = 0.98;
        double percentageHeight = 0.90;
        componentLayout = new BorderPane();

        stage = primaryStage;
        stage.getIcons().add(new Image("icon.png"));


        packRow = new ArrayList<List<Label>>();

        image = new Image("search.png");

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();


        this.scene = new Scene(componentLayout, percentageWidth, percentageHeight);
        buildLeft();
        buildCenter();
        buildTop();


        scene.getStylesheets().add("JMetroLightTheme.css");


        primaryStage.setTitle("TripBroker");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    /*
    *   le prossime 3 funzioni servono a implementare il mediator ( in questo caso il mediator è unidirezionale, quindi
    *   la receive non verrà mai raggiunta
    *
    * */
    public void send(List<CheckBox> checkBoxes) {
        cbMediator.send(checkBoxes,this);
    }

    public CbMediator getCbMediator() {return cbMediator;}

    public void receive(List<CheckBox> checkBoxes) {
    //Never Reached
    }


    /*buildLeft costruisce la zona a sinistra, con i button relativi alla specializzazione dell'utente*/
    private void buildLeft() {

        BorderPane leftLayout = new BorderPane();

        leftLayout.setStyle("-fx-background-color:  #008287;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");


        // Create a faux border-right effect using a Label.
        Label divider = new Label();
        divider.setPrefWidth(1);
        divider.setMinHeight(Screen.getPrimary().getBounds().getHeight());
        leftLayout.setRight(divider);

        //Place all demonstration buttons in a Vercial Box.


        //Add VBox to leftLayout.
        leftLayout.setCenter(setButtonBox(dep));

        //Place into Application.
        componentLayout.setLeft(leftLayout);

    }

    /*setButtonBox sceglie quali button inserire (dep può essere Scout,Designer,Administrator,Agente)*/

    private VBox setButtonBox(String dep)
    {
        VBox buttonBox = new VBox();

        buttonBox.setAlignment(Pos.TOP_CENTER);

        buttonBox.setSpacing(10);

//        Dipendente dipendente = Dipendente.valueOf(dep);

        Button logout;

        switch (dep) {

            case "Scout":


                logout = new Button() ;
                logout.setText("Log out");
                logout.setFont(new Font(Dim_Butt));
                logout.setMaxWidth(Double.MAX_VALUE);
                logout.setOnAction(accessoCatalogoControl::logout);


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
                buttonBox.getChildren().addAll(logout,offerte, contratti, ricerca);
                break;

            case "Designer":

                logout = new Button() ;
                logout.setText("Log out");
                logout.setFont(new Font(Dim_Butt));
                logout.setMaxWidth(Double.MAX_VALUE);
                logout.setOnAction(accessoCatalogoControl::logout);


                Button aggrega = new Button() ;
                aggrega.setText("Aggregazione Offerte");
                aggrega.setFont(new Font(Dim_Butt));
                aggrega.setMaxWidth(Double.MAX_VALUE);
                aggrega.setOnAction(accessoCatalogoControl::aggregazioneOfferte);


                Button costi = new Button();
                costi.setText("Aggiorna costi offerte");
                costi.setFont(new Font(Dim_Butt));
                costi.setMaxWidth(Double.MAX_VALUE);



                buttonBox.getChildren().addAll(logout,aggrega, costi);
                break;

            case "Admin":


                logout = new Button() ;
                logout.setText("Log out");
                logout.setFont(new Font(Dim_Butt));
                logout.setMaxWidth(Double.MAX_VALUE);
                logout.setOnAction(accessoCatalogoControl::logout);

                Button conferma = new Button() ;
                conferma.setText("Conferma pacchetti");
                conferma.setOnAction(accessoCatalogoControl::confermaPacchetti);
                conferma.setFont(new Font(Dim_Butt));
                conferma.setMaxWidth(Double.MAX_VALUE);


                Button aggiorna = new Button() ;
                aggiorna.setText("Aggiorna offerte con criterio");
                aggiorna.setOnAction(accessoCatalogoControl::aggiornaCosti);
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

                buttonBox.getChildren().addAll(logout,conferma,aggiorna,andamento,log);
                break;

            case "Agente":

                logout = new Button() ;
                logout.setText("Log out");
                logout.setFont(new Font(Dim_Butt));
                logout.setMaxWidth(Double.MAX_VALUE);
                logout.setOnAction(accessoCatalogoControl::logout);


                Button prenotazione = new Button() ;
                prenotazione.setText("Prenotazione viaggio");
                prenotazione.setMaxWidth(Double.MAX_VALUE);
                prenotazione.setFont(new Font(20));
                buttonBox.getChildren().addAll(logout,prenotazione);
                break;

        }
        return buttonBox;
    }

    /*buildTop costruisce la barra del titolo in alto*/
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

//
//        Image trip = new Image("banner.jpg");
//        ImageView imageView = new ImageView(trip);


//
//        //Add Title label to titleBox
//        titleBox.getChildren().add(imageView);
//
//        //Add Title label to titleBox
        titleBox.getChildren().add(title);

        //Add Title Box (with label) to topLayout
//        topLayout.setCenter(imageView);

        topLayout.setCenter(titleBox);

        topLayout.setStyle("-fx-background-color: lightgrey;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");


        //Add topLayout (a BorderPane Manager) to App Layout.
        componentLayout.setTop(topLayout);

    }

    /* BuildCenter costruisce la parte centrale , con le due tab, una relativa alle offerte e una relativa ai pacchetti
    *
    * */
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

    /*buildOfferte costruisce la tab relativa alle offerte, costituita da una splitpane divisa in 3 parti, in quanto
    * ci sono 3 tipi di offerte differenti */
    private SplitPane buildOfferte(){
        SplitPane sp = new SplitPane();


        of1 = BuildOffertePernotto();

        of1.setPadding(new Insets(0,0,0,5.0));


        of2 =BuildOfferteTrasporto();

        of2.setPadding(new Insets(0,0,0,5.0));


        of3 = buildOfferteEventi();

        of3.setPadding(new Insets(0,0,0,5.0));


        sp.setDividerPositions(0.33f, 0.66f, 0.99f);
        sp.getItems().addAll(of1,of2,of3);


        return sp;
    }


    /* buildOfferteEventi contatta il controller per prelevare dal database le offerte eventi, poi le inserisce all 'interno
     della propria zona
    *
    * */
    private StackPane buildOfferteEventi()
    {
        StackPane sp = new StackPane();

        Label evLabel = new Label("Offerte di eventi");
        evLabel.getStyleClass().add("text-titlebig");
        gpEve = new GridPane();

        gpEve.setHgap(25);
        gpEve.setVgap(15);


        gpEve.add(evLabel,1,0,3,1);

        Label nome = new Label("Nome");
        nome.getStyleClass().add("text-title");

        Label prezzo = new Label("Prezzo");
        prezzo.getStyleClass().add("text-title");

        Label citta = new Label("Città");
        citta.getStyleClass().add("text-title");

        Label tipo = new Label("Tipo");
        tipo.getStyleClass().add("text-title");

        gpEve.add(nome,0,1);
        gpEve.add(prezzo,1,1);
        gpEve.add(citta,2,1);
        gpEve.add(tipo,3,1);
        infoEve= new ArrayList<ImageView>();

        List<OffertaEvento> offerte  = new ArrayList<OffertaEvento>();

//        offerte = accessoCatalogoControl.getOffEven();

        offerte = (List<OffertaEvento>) accessoCatalogoControl.getAllOff(TipoOfferta.OffertaEvento);

        gpEveRow=2;
        int app;
        if(offerte!=null)
        for(OffertaEvento offertaEvento : offerte){
            this.addOff(offertaEvento);

        }


        sp.getChildren().add(gpEve);
        return sp;
    }

    /* buildOfferteTrasporto contatta il controller per prelevare dal database le offerte eventi, poi le inserisce all 'interno
     della propria zona
    *
    * */

    private StackPane BuildOfferteTrasporto()
    {
        StackPane sp = new StackPane();

        Label trasLabel = new Label("Offerte di Trasporto");
        trasLabel.getStyleClass().add("text-titlebig");


        gpTras = new GridPane();

        gpTras.setHgap(25);
        gpTras.setVgap(15);


        gpTras.add(trasLabel,1,0,3,1);

        Label nome = new Label("Nome");
        nome.getStyleClass().add("text-title");

        Label prezzo = new Label("Prezzo");
        prezzo.getStyleClass().add("text-title");

        Label citta = new Label("Città");
        citta.getStyleClass().add("text-title");

        Label cittaP = new Label("Città Partenza");
        cittaP.getStyleClass().add("text-title");

        Label tipo = new Label("Tipo");
        tipo.getStyleClass().add("text-title");

        gpTras.add(nome,0,1);
        gpTras.add(prezzo,1,1);
        gpTras.add(citta,2,1);
        gpTras.add(cittaP,3,1);
        gpTras.add(tipo,4,1);

        List<OffertaTrasporto> offerte  = new ArrayList<OffertaTrasporto>();

        infoTras = new ArrayList<ImageView>();

        offerte = (List<OffertaTrasporto>) accessoCatalogoControl.getAllOff(TipoOfferta.OffertaTrasporto);

//        offerte = accessoCatalogoControl.getOffTras();

        gpTrasRow=2;
        int app;
        if(offerte!=null)
            for(OffertaTrasporto offertaTrasporto : offerte){
                this.addOff(offertaTrasporto);
            }

        sp.getChildren().add(gpTras);

        return sp;
    }


/* buildOffertePernotto contatta il controller per prelevare dal database le offerte pernotto, poi le inserisce all 'interno
     della propria zona
    *
    * */

    private StackPane BuildOffertePernotto()
    {
        StackPane sp = new StackPane();

        Label pernLabel = new Label("Offerte di Pernotto");
        pernLabel.getStyleClass().add("text-titlebig");


        gpPern = new GridPane();

        gpPern.setHgap(25);
        gpPern.setVgap(15);



        gpPern.add(pernLabel,1,0,3,1);

        Label nome = new Label("Nome");
        nome.getStyleClass().add("text-title");

        Label prezzo = new Label("Prezzo");
        prezzo.getStyleClass().add("text-title");

        Label citta = new Label("Città");
        citta.getStyleClass().add("text-title");

        Label tipo = new Label("Tipo");
        tipo.getStyleClass().add("text-title");

        Label notti = new Label("Notti");
        notti.getStyleClass().add("text-title");


        gpPern.add(nome,0,1);
        gpPern.add(prezzo,1,1);
        gpPern.add(citta,2,1);
        gpPern.add(tipo,3,1);
        gpPern.add(notti,4,1);


        List<OffertaPernotto> offerte  = new ArrayList<OffertaPernotto>();
        infoPern = new ArrayList<ImageView>();

        offerte = (List<OffertaPernotto>) accessoCatalogoControl.getAllOff(TipoOfferta.OffertaPernotto);


//        offerte = accessoCatalogoControl.getOffPern();

        gpPernRow=2;
        int app;
        if(offerte!=null)
            for(OffertaPernotto offertaPernotto : offerte){
                this.addOff(offertaPernotto);

            }

        sp.getChildren().add(gpPern);

        return sp;
    }




    /*
    *
    *   addOff inserisce una offerta nella relativa tabella
    * */
    public void addOff(Object offerta)
    {
        if(offerta instanceof OffertaEvento) {

            gpEve.add(new Label(((OffertaEvento)offerta).getNome()),0,gpEveRow);
            gpEve.add(new Label(Integer.toString(((OffertaEvento)offerta).getPrezzo())+"€"),1,gpEveRow);
            gpEve.add(new Label(((OffertaEvento)offerta).getCittà()),2,gpEveRow);
            gpEve.add(new Label(((OffertaEvento)offerta).getTipologia()),3,gpEveRow);

            ImageView imageView = new ImageView(image);
            imageView.setId(Integer.toString( ( (OffertaEvento) offerta).getEveID()));
            imageView.setOnMouseClicked(accessoCatalogoControl::visualizzaOfferteEvento);

            infoEve.add(imageView);


            gpEve.add(imageView,4,gpEveRow);

            Separator separator = new Separator();
            gpEve.add(separator,0,gpEveRow+1,5,1);


            gpEveRow+=2;

        }



        if(offerta instanceof OffertaPernotto) {

            gpPern.add(new Label(((OffertaPernotto)offerta).getNome()),0,gpPernRow);
            gpPern.add(new Label(Integer.toString(((OffertaPernotto)offerta).getPrezzo())+"€"),1,gpPernRow);
            gpPern.add(new Label(((OffertaPernotto)offerta).getCittà()),2,gpPernRow);
            gpPern.add(new Label(((OffertaPernotto)offerta).getTipologia()),3,gpPernRow);
            gpPern.add(new Label(Integer.toString(((OffertaPernotto)offerta).getNumeroNotti())),4,gpPernRow);


            ImageView imageView = new ImageView(image);
            imageView.setId(Integer.toString(((OffertaPernotto)offerta).getPerID()));
            imageView.setOnMouseClicked(accessoCatalogoControl::visualizzaOffertePernotto);

            infoPern.add(imageView);


            gpPern.add(imageView,5,gpPernRow);

            Separator separator = new Separator();
            gpPern.add(separator,0,gpPernRow+1,6,1);


            gpPernRow+=2;

        }


        if(offerta instanceof  OffertaTrasporto) {


            gpTras.add(new Label(((OffertaTrasporto)offerta).getNome()),0,gpTrasRow);
            gpTras.add(new Label(Integer.toString(((OffertaTrasporto)offerta).getPrezzo())+"€"),1,gpTrasRow);
            gpTras.add(new Label(((OffertaTrasporto)offerta).getCittà()),2,gpTrasRow);
            gpTras.add(new Label(((OffertaTrasporto)offerta).getCittàPartenza()),3,gpTrasRow);
            gpTras.add(new Label(((OffertaTrasporto)offerta).getTipologia()),4,gpTrasRow);


            ImageView imageView = new ImageView(image);
            imageView.setId(Integer.toString(((OffertaTrasporto)offerta).getTrasID()));
            imageView.setOnMouseClicked(accessoCatalogoControl::visualizzaOfferteTrasporto);

            infoEve.add(imageView);


            gpTras.add(imageView,5,gpTrasRow);


            Separator separator = new Separator();
            gpTras.add(separator,0,gpTrasRow+1,6,1);


            gpTrasRow+=2;
        }

    }


    /*
    *
    *   aggiunge i radiobutton e le checkbox per permettere al designer di selezionare le offerte
    *
    * */
    private void addDesignerBox()
    {

        Label selPern = new Label("Aggiungi");
        selPern.getStyleClass().add("text-title");
        gpPern.add(selPern,6,1);
        ToggleGroup groupPern, groupTras;


        int i,j=11;
        groupPern = new ToggleGroup();
        for (i=2;i<gpPernRow;i++) {
            if(i%2==0) {
                RadioButton radioButton = new RadioButton();
                radioButton.setId(gpPern.getChildren().get(j).getId());//setta l'id  con l'id dell'offerta relativa (contenuto dentro l'imageview, dalla posizione 11 ogni 6 posizioni
                j += 7;
                radioButton.setToggleGroup(groupPern);
                radioButton.setOnAction(accessoCatalogoControl::radioPern);

                gpPern.add(radioButton, 6, i);
            }
        }

        Label selTras = new Label("Aggiungi");
        selTras.getStyleClass().add("text-title");

        gpTras.add(selTras,6,1);

        j=11;
        groupTras = new ToggleGroup();
        for (i=2;i<gpTrasRow;i++) {
            if(i%2==0) {
                RadioButton radioButton = new RadioButton();
                radioButton.setId(gpTras.getChildren().get(j).getId());
                j += 7;
                radioButton.setToggleGroup(groupTras);
                radioButton.setOnAction(accessoCatalogoControl::radioTras);

                gpTras.add(radioButton, 6, i);
            }
        }

        Label selEve = new Label("Aggiungi");
        selEve.getStyleClass().add("text-title");

        gpEve.add(selEve,5,1);

        checkBoxes= new ArrayList<CheckBox>();
         j=9;
        for(i=2;i<gpEveRow;i++)
        {
            if(i%2==0) {

                CheckBox checkBox = new CheckBox();

                checkBox.setId(gpEve.getChildren().get(j).getId()); //setta l'id  con l'id dell'offerta relativa
                j += 6;
                gpEve.add(checkBox, 5, i);
                checkBoxes.add(checkBox);
            }
        }
        final Pane  spring = new Pane();
        Button okButton = new Button("Aggiungi a pacchetto");
        okButton.setOnAction(accessoCatalogoControl::addPack);

        gpEve.add(spring,0,gpEveRow);
        gpEve.add(okButton,0,gpEveRow+1,4,1);



        this.send(checkBoxes);
        this.setEffect(1,null);
        check = true;


    }

    /*
    *
    *  rimuove i radiobutton e le checkbox quando il designer finisce il proprio lavoro
    * */

    private void removeDesignerBox()
    {
            if(check) {
                int from = gpPern.getChildren().size() - gpPernRow / 2;
                int to = gpPern.getChildren().size();
                gpPern.getChildren().remove(from, to);


                from = gpTras.getChildren().size() - gpTrasRow / 2;
                to = gpTras.getChildren().size();
                gpTras.getChildren().remove(from, to);

                from = gpEve.getChildren().size() - gpEveRow / 2 - 2; //comprende anche il bottone di conferma
                to = gpEve.getChildren().size();
                gpEve.getChildren().remove(from, to);
                check = false;
            }

    }


    /*
    *
    *  Oscura le colonne di offerte diverse da val,e rende invisibili i checkbox e i radiobutton
    *  della colonna di val non compadibili con l'offerta
    *
    *
    *   esempio: val = 1 , => colonna n° 2 , cioè offerteTrasporto
     *            quindi oscura la colonna offertePernotto e offerteEvento ,ed elimina radiobutton di offerteTrasporto
     *            non compatibili con l'offerta
     *
     *            se val =0, fa tornare tutto normale
    *
    *
    * */
    public void setEffect(int val,String citta)
    {
        BoxBlur bb = new BoxBlur();
        bb.setWidth(5);
        bb.setHeight(5);
        bb.setIterations(3);
        boolean trovato = false;

        int i,j;


        if(val == 0)
        {
            of1.setEffect(null);
            of2.setEffect(null);
            of3.setEffect(null);

            of1.setDisable(false);
            of2.setDisable(false);
            of3.setDisable(false);

        }
        if(val ==1)
        {
            of1.setEffect(null);
            of2.setEffect(bb);
            of3.setEffect(bb);

            of1.setDisable(false);
            of2.setDisable(true);
            of3.setDisable(true);


        }


        if(val ==2)
        {
            of2.setEffect(null);
            trovato = false;
            j=8;
            for (i=2;i<gpTrasRow;i+=2) {
                if(!( (Label) gpTras.getChildren().get(j)).getText().equals(citta)) {
                    int pos = gpTras.getChildren().size()-1;
                    pos=pos - (gpTrasRow-i)/2 + 1;
                    gpTras.getChildren().get(pos).setVisible(false);
                }
                else trovato = true;
                j+=7;
            }
            if(trovato) {
                of1.setEffect(bb);
                of3.setEffect(bb);

                of2.setDisable(false);
                of1.setDisable(true);
                of3.setDisable(true);
            }

            else
                showPopup("Non è possibile trovare combinazioni possibili");

        }
        if(val ==3)
        {
            of3.setEffect(null);
            trovato = false;

            j=7;
            for (i=2;i<gpEveRow;i+=2) {
                if(!( (Label) gpEve.getChildren().get(j)).getText().equals(citta)) {
                    int pos = gpEve.getChildren().size()-1;
                    pos=pos - ((gpEveRow-i)/2) +1 -2;
                    gpEve.getChildren().get(pos).setVisible(false);
                }
                else trovato=true;
                j+=6;
            }

            if(trovato) {
                of1.setEffect(bb);
                of2.setEffect(bb);

                of3.setDisable(false);
                of1.setDisable(true);
                of2.setDisable(true);
            }
            else
                showPopup("Non è possibile trovare combinazioni possibili");
        }

    }

    /*
    * Mostra un popup con il messaggio message
    * */

    private void showPopup(String message)
    {
        final Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(message));
        Button button = new Button("OK");
        button.setPrefWidth(100);
        button.setOnAction(accessoCatalogoControl::okButton);
        dialogVbox.getChildren().add(button);
        dialog.getIcons().add(new Image("icon.png"));

        Scene dialogScene = new Scene(dialogVbox, 200, 100);

        dialog.setScene(dialogScene);
        dialog.show();

    }


    /*
    *  Costruisce la tab relativa ai pacchetti, Preleva i pacchetti dal db e li inserisce.
    *  Colora di rosso quelli non approvati, di blu quelli approvati
    *
    * */
    private StackPane buildPacchetti(){
        StackPane sp = new StackPane();

        sp.setPadding(new Insets(0,0,0,5.0));


        gpPackRow = 1;

        gpPack = new GridPane();


        gpPack.setHgap(50);
        gpPack.setVgap(10);


        Circle red = new Circle();
        red.setRadius(5.0f);
        red.setFill(Color.RED);


        Circle blue = new Circle();
        blue.setRadius(5.0f);
        blue.setFill(Color.BLUE);


        Label redLabel = new Label("        Pacchetti non approvati");

        Label blueLabel = new Label("        Pacchetti approvati");



        Label name = new Label("Nome Pacchetto");
        name.getStyleClass().add("text-title");

        Label price = new Label("Prezzo");
        price.getStyleClass().add("text-title");

        Label citta = new Label("Città");
        citta.getStyleClass().add("text-title");

        Label tipoPern= new Label ("Tipologia");
        tipoPern.getStyleClass().add("text-title");

        Label stelle= new Label ("Stelle");
        stelle.getStyleClass().add("text-title");

        Label notti = new Label("Numero notti");
        notti.getStyleClass().add("text-title");

        Label cittaP = new Label("Partenza");
        cittaP.getStyleClass().add("text-title");

        Label tipoTras = new Label("Tipologia");
        tipoTras.getStyleClass().add("text-title");

        Label durata = new Label ("Durata");
        durata.getStyleClass().add("text-title");

        Label evento = new Label("Nome evento");
        evento.getStyleClass().add("text-title");

        Label tipoEven = new Label ("Tipologia");
        tipoEven.getStyleClass().add("text-title");

        Label pernotto = new Label("Pernotto");
        pernotto.getStyleClass().add("text-titlebig");

        Label trasporto = new Label("Trasporto");
        trasporto.getStyleClass().add("text-titlebig");

        Label eventi = new Label("Eventi");
        eventi.getStyleClass().add("text-titlebig");

        Label pacchetto = new Label ("Pacchetto");
        pacchetto.getStyleClass().add("text-titlebig");



        gpPack.add(red,1,0,2,1);
        gpPack.add(redLabel,1,0,2,1);
        gpPack.add(blue,3,0,2,1);
        gpPack.add(blueLabel,3,0,2,1);

        Separator separator = new Separator();
        gpPack.add(separator,0,gpPackRow,15,1);
        gpPackRow++;




        gpPack.add(pacchetto,1,2,3,1);
        gpPack.add(pernotto,4,2,3,1);
        gpPack.add(trasporto,8,2,2,1);
        gpPack.add(eventi,12,2,2,1);

        gpPack.add(name,0,3);
        gpPack.add(price,1,3);
        gpPack.add(citta,2,3);


        gpPack.add(tipoPern,4,3);
        gpPack.add(stelle,5,3);
        gpPack.add(notti,6,3);

        gpPack.add(cittaP,8,3);
        gpPack.add(tipoTras,9,3);
        gpPack.add(durata,10,3);



        gpPack.add(evento,12,3);
        gpPack.add(tipoEven,13,3);

        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);

        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);

        Separator separator3 = new Separator();
        separator3.setOrientation(Orientation.VERTICAL);

        Separator separator4 = new Separator();
        separator4.setOrientation(Orientation.VERTICAL);

        gpPack.add(separator1,3,1,1,5);
        gpPack.add(separator2,7,1,1,5);
        gpPack.add(separator3,11,1,1,5);
        gpPack.add(separator4,14,1,1,5);

        gpPackRow+=2;

        Separator separator5 = new Separator();
        gpPack.add(separator5,0,gpPackRow,15,1);
        gpPackRow++;




        List<Pacchetto> pacchetti= accessoCatalogoControl.getPack();

        if(pacchetti!=null) {
            for (Pacchetto pack : pacchetti) {
                this.addPack(pack);
            }

        }


            sp.getChildren().add(gpPack);



        return sp;
    }

    /*
    * Aggiunge una nuova riga alla tabella dei pacchetti
    *
    * */
    public void addPack(Pacchetto pack)
    {

            List<Label> labels = new ArrayList<Label>();
            labels.add(new Label(Integer.toString(pack.getId())));

            int i;

            int from = gpPack.getChildren().size();
            int fromRow = gpPackRow;


            gpPack.add(new Label(pack.getNome()), 0, gpPackRow);
            gpPack.add(new Label(Integer.toString(pack.getPrezzo())), 1, gpPackRow);
            gpPack.add(new Label(pack.getOffertaPernotto().getCittà()), 2, gpPackRow);
            gpPack.add(new Label(pack.getOffertaPernotto().getTipologia()), 4, gpPackRow);
            gpPack.add(new Label(Integer.toString(pack.getOffertaPernotto().getStelle())), 5, gpPackRow);
            gpPack.add(new Label(Integer.toString(pack.getOffertaPernotto().getNumeroNotti())), 6, gpPackRow);
            gpPack.add(new Label(pack.getOffertaTrasporto().getCittàPartenza()), 8, gpPackRow);
            gpPack.add(new Label(pack.getOffertaTrasporto().getTipologia()), 9, gpPackRow);
            gpPack.add(new Label(Integer.toString(pack.getOffertaTrasporto().getDurata())), 10, gpPackRow);


            int j=0;
            for (OffertaEvento events : pack.getOffertaEvento()) {
                gpPack.add(new Label(events.getNome()), 12, gpPackRow);
                gpPack.add(new Label(events.getTipologia()), 13, gpPackRow);

                j+=2;
                gpPackRow++;
            }

            for(i=0;i<9+j;i++)
            {

                labels.add(((Label) gpPack.getChildren().get(from+i)));
            }

            Separator separator = new Separator();
            gpPack.add(separator,0,gpPackRow,15,1);
            gpPackRow++;


            Separator separator1 = new Separator();
            separator1.setOrientation(Orientation.VERTICAL);

            Separator separator2 = new Separator();
            separator2.setOrientation(Orientation.VERTICAL);

            Separator separator3 = new Separator();
            separator3.setOrientation(Orientation.VERTICAL);

             Separator separator4 = new Separator();
            separator4.setOrientation(Orientation.VERTICAL);

            gpPack.add(separator1,3,fromRow,1,pack.getOffertaEvento().size());
            gpPack.add(separator2,7,fromRow,1,pack.getOffertaEvento().size());
            gpPack.add(separator3,11,fromRow,1,pack.getOffertaEvento().size());
            gpPack.add(separator4,14,fromRow,1,pack.getOffertaEvento().size());


            packRow.add(labels);


            colorRow(pack,pack.getStato());




    }

/*
 *  Colora di rosso quelli non approvati, di blu quelli approvati
*/
    private void colorRow(Pacchetto pacchetto, boolean stato)
    {
        int row = 0;
        int i=0;
        for(List<Label> labell : packRow)
        {
            if( Integer.parseInt(labell.get(0).getText()) == pacchetto.getId()) {
                row = i;
                break;
            }

            i++;
        }

        for(Label label : packRow.get(row))
        {
            if(stato)
                label.setStyle("-fx-text-fill: blue;");
            else
                label.setStyle("-fx-text-fill: red;");
        }

    }






/*
*
* Mostra un popup contenente le informazioni della tabella selezionata
* */
    public void mostraOfferta(Object offerta)
    {
        TextField textField;
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        dialog.setTitle("Descrizione Offerta");
        dialog.getIcons().add(new Image("icon.png"));

        GridPane gp = new GridPane();
        gp.setPadding(new Insets(20,0,20,20));

        gp.setHgap(10);
        gp.setVgap(10);

        textField = new TextField( ((Offerta)offerta).getNome());
        textField.setEditable(false);
        textField.setFocusTraversable(false);

        gp.add(new Label("Nome:"),0,0);
        gp.add(textField,1,0);

        textField = new TextField(Integer.toString(((Offerta)offerta).getPrezzo()));
        textField.setEditable(false);
        textField.setFocusTraversable(false);

        gp.add(new Label("Prezzo:"),0,1);
        gp.add(textField,1,1);

        textField =new TextField(((Offerta)offerta).getCittà());
        textField.setEditable(false);
        textField.setFocusTraversable(false);

        gp.add(new Label("Città:"),0,2);
        gp.add(textField,1,2);

        textField = new TextField(((Offerta)offerta).getDataScadenza());
        textField.setEditable(false);
        textField.setFocusTraversable(false);

        gp.add(new Label("Data:"),0,3);
        gp.add(textField,1,3);


        if(offerta instanceof OffertaPernotto)
        {
            textField = new TextField(Integer.toString (((OffertaPernotto)offerta).getNumeroNotti()));
            textField.setEditable(false);
            textField.setFocusTraversable(false);

            gp.add(new Label("Numero notti:"),0,4);
            gp.add(textField,1,4);

            textField = new TextField(Integer.toString (((OffertaPernotto)offerta).getNumeroNotti()));
            textField.setEditable(false);
            textField.setFocusTraversable(false);

            gp.add(new Label("Stelle: "),0,5);
            gp.add(textField,1,5);

            textField = new TextField(((OffertaPernotto)offerta).getTipologia());
            textField.setEditable(false);
            textField.setFocusTraversable(false);

            gp.add(new Label("Tipologia"),0,6);
            gp.add(textField,1,6);

            Button ok = new Button("OK");
            ok.setMaxWidth(100.0);
            ok.setOnAction(accessoCatalogoControl::okListener);

            gp.add(ok,1,7,2,1);

        }

        if(offerta instanceof OffertaTrasporto) {


            textField = new TextField(((OffertaTrasporto) offerta).getCittàPartenza());
            textField.setEditable(false);
            textField.setFocusTraversable(false);

            gp.add(new Label("Città di partenza"), 0, 4);
            gp.add(textField, 1, 4);

            textField = new TextField( Integer.toString  (((OffertaTrasporto)offerta).getDurata()));
            textField.setEditable(false);
            textField.setFocusTraversable(false);


            gp.add(new Label("Durata:"),0,5);
            gp.add(textField,1,5);


            textField = new TextField( ((OffertaTrasporto)offerta).getTipologia());
            textField.setEditable(false);
            textField.setFocusTraversable(false);

            gp.add(new Label("Tipologia"),0,6);
            gp.add(textField,1,6);

            Button ok = new Button("OK");
            ok.setMaxWidth(100.0);
            ok.setOnAction(accessoCatalogoControl::okListener);

            gp.add(ok,1,7,2,1);

        }

        if(offerta instanceof OffertaEvento)
        {
            textField = new TextField(((OffertaEvento)offerta).getTipologia());
            textField.setEditable(false);
            textField.setFocusTraversable(false);


            gp.add(new Label("Tipologia"),0,4);
            gp.add(textField,1,4);

            Button ok = new Button("OK");
            ok.setMaxWidth(100.0);
            ok.setOnAction(accessoCatalogoControl::okListener);

            gp.add(ok,1,7,2,1);
        }

        double percentageWidth = 0.20;
        double percentageHeight = 0.30;

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();

        Scene dialogScene = new Scene(gp, percentageWidth, percentageHeight);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    /* mostra o elimina le checkbox e radiobutton
     */
    public void showCheckBox(boolean show)
    {

        if(show)
            this.addDesignerBox();

        else
            this.removeDesignerBox();


    }


    public void upPack(Pacchetto pacchetto)

    {
        int row = 0;
        int i=0;
        for(List<Label> labell : packRow)
        {
            if( Integer.parseInt(labell.get(0).getText()) == pacchetto.getId()) {
                row = i;
                break;
            }

            i++;
        }

        if(pacchetto.getNome()!=null)
            packRow.get(row).get(1).setText(pacchetto.getNome());


        if(pacchetto.getPrezzo()!=null)
            packRow.get(row).get(2).setText(Integer.toString(pacchetto.getPrezzo()));

        if(pacchetto.getOffertaPernotto()!=null) {
            //TODO
//            packRow.get(row).get(3).setText(pacchetto.getOffertaPernotto().getCittà());
//            packRow.get(row).get(4).setText(pacchetto.getOffertaPernotto().getTipologia());
//            packRow.get(row).get(5).setText(Integer.toString(pacchetto.getOffertaPernotto().getStelle()));
//            packRow.get(row).get(6).setText(Integer.toString(pacchetto.getOffertaPernotto().getNumeroNotti()));

        if(pacchetto.getOffertaTrasporto()!=null)
        {
            //TODO
        }

        if(pacchetto.getOffertaPernotto()!=null)
        {
            //TODO
        }

        }

        for(Label label : packRow.get(row))
        {
            if(pacchetto.getStato())
                label.setStyle("-fx-text-fill: blue;");
            else
                label.setStyle("-fx-text-fill: red;");
        }

    }

    public void delPack(int id)
    {


        int row = 0;
        int i=0;
        int tot=0;

        for(List<Label> labell : packRow)
        {
            if( Integer.parseInt(labell.get(0).getText()) == id) {
                row = i;
                break;
            }
            tot+=(labell.size() -1 +4);




            i++;
        }





        int from = 26 + tot;

        gpPack.getChildren().remove(from-1,from + packRow.get(row).size()+3);


    }


}
//
//gpPack.add(new Label(pack.getNome()), 0, gpPackRow);
//        gpPack.add(new Label(Integer.toString(pack.getPrezzo())), 1, gpPackRow);
//        gpPack.add(new Label(pack.getOffertaPernotto().getCittà()), 2, gpPackRow);
//        gpPack.add(new Label(pack.getOffertaPernotto().getTipologia()), 4, gpPackRow);
//        gpPack.add(new Label(Integer.toString(pack.getOffertaPernotto().getStelle())), 5, gpPackRow);
//        gpPack.add(new Label(Integer.toString(pack.getOffertaPernotto().getNumeroNotti())), 6, gpPackRow);
//        gpPack.add(new Label(pack.getOffertaTrasporto().getCittàPartenza()), 8, gpPackRow);
//        gpPack.add(new Label(pack.getOffertaTrasporto().getTipologia()), 9, gpPackRow);
//        gpPack.add(new Label(Integer.toString(pack.getOffertaTrasporto().getDurata())), 10, gpPackRow);
//
//
//        int j=0;
//        for (OffertaEvento events : pack.getOffertaEvento()) {
//        gpPack.add(new Label(events.getNome()), 12, gpPackRow);
//        gpPack.add(new Label(events.getTipologia()), 13, gpPackRow);
//
//        j+=2;
//        gpPackRow++;
//        }