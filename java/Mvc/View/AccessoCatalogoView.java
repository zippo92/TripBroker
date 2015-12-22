package Mvc.View;

import Mvc.Control.AccessoCatalogoControl;
import Patterns.CbMediator.CbColleague;
import Patterns.CbMediator.CbMediator;
import Patterns.CbMediator.CbMediatorImpl;
import entityPackage.Offerta;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
    private GridPane gpPern,gpTras,gpEve;
    private int gpPernRow,gpTrasRow,gpEveRow;
    private List<ImageView> infoEve = new ArrayList<ImageView>();
    private List<ImageView> infoTras = new ArrayList<ImageView>();
    private List<ImageView> infoPern = new ArrayList<ImageView>();
    private Image image;
    private Stage stage;
    private ToggleGroup groupPern, groupTras;
    private List<CheckBox> checkBoxes;
    private CbMediatorImpl cbMediator;




    public AccessoCatalogoView(Stage primaryStage, String utente, AccessoCatalogoControl control,CbMediatorImpl mediator) throws IOException {

        cbMediator = mediator;
        cbMediator.addColleague(this);

        dep = utente;
        accessoCatalogoControl = control;
        double percentageWidth = 0.98;
        double percentageHeight = 0.90;
        componentLayout = new BorderPane();
        componentLayout.setPadding(new Insets(20,0,20,20));
        stage = primaryStage;

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();

        image = new Image("search.png");

        this.scene = new Scene(componentLayout, percentageWidth, percentageHeight);
        buildLeft();
        buildCenter();
        buildTop();


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public void send(List<CheckBox> checkBoxes) {
        cbMediator.send(checkBoxes,this);
    }

    public CbMediator getCbMediator() {return cbMediator;}

    public void receive(List<CheckBox> checkBoxes) {
    //Never Reached
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

        gpEve.setHgap(25);
        gpEve.setVgap(15);


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
        infoEve= new ArrayList<ImageView>();

        List<OffertaEvento> offerte  = new ArrayList<OffertaEvento>();

        offerte = accessoCatalogoControl.getOffEven();

        gpEveRow=2;
        int app;
        if(offerte!=null)
        for(OffertaEvento offertaEvento : offerte){
            this.addRow(offertaEvento);

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

        gpTras.setHgap(25);
        gpTras.setVgap(15);


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
        infoTras = new ArrayList<ImageView>();

        offerte = accessoCatalogoControl.getOffTras();

        gpTrasRow=2;
        int app;
        if(offerte!=null)
            for(OffertaTrasporto offertaTrasporto : offerte){
                this.addRow(offertaTrasporto);
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

        gpPern.setHgap(25);
        gpPern.setVgap(15);



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
        infoPern = new ArrayList<ImageView>();

        offerte = accessoCatalogoControl.getOffPern();

        gpPernRow=2;
        int app;
        if(offerte!=null)
            for(OffertaPernotto offertaPernotto : offerte){
                this.addRow(offertaPernotto);

            }

        sp.getChildren().add(gpPern);

        return sp;
    }





    public void addRow(Object offerta)
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
            gpEveRow++;

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
            gpPernRow++;

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

            gpTrasRow++;



        }

    }


    private void addDesignerBox()
    {
        Label selPern = new Label("Aggiungi");
        selPern.setFont(new Font("Arial",15));
        gpPern.add(selPern,6,1);

        int i,j=11;
        groupPern = new ToggleGroup();
        for (i=2;i<gpPernRow;i++) {

            RadioButton radioButton = new RadioButton();
            radioButton.setId(gpPern.getChildren().get(j).getId());//setta l'id  con l'id dell'offerta relativa (contenuto dentro l'imageview, dalla posizione 11 ogni 6 posizioni
            j+=6;
            radioButton.setToggleGroup(groupPern);
            radioButton.setOnAction(accessoCatalogoControl::radioPern);

            gpPern.add(radioButton,6,i);

        }

        Label selTras = new Label("Aggiungi");
        selTras.setFont(new Font("Arial",15));

        gpTras.add(selTras,6,1);

        j=11;
        groupTras = new ToggleGroup();
        for (i=2;i<gpTrasRow;i++) {

            RadioButton radioButton = new RadioButton();
            radioButton.setId(gpTras.getChildren().get(j).getId());
            j+=6;
            radioButton.setToggleGroup(groupTras);
            radioButton.setOnAction(accessoCatalogoControl::radioTras);

            gpTras.add(radioButton,6,i);

        }

        Label selEve = new Label("Aggiungi");
        selEve.setFont(new Font("Arial",15));

        gpEve.add(selEve,5,1);

        checkBoxes= new ArrayList<CheckBox>();
         j=9;
        for(i=2;i<gpEveRow;i++)
        {   CheckBox checkBox = new CheckBox();

            checkBox.setId(gpEve.getChildren().get(j).getId()); //setta l'id  con l'id dell'offerta relativa
            j+=5;
            gpEve.add(checkBox,5,i);
            checkBoxes.add(checkBox);

        }
        final Pane  spring = new Pane();
        Button okButton = new Button("Aggiungi a pacchetto");
        okButton.setOnAction(accessoCatalogoControl::addPack);

        gpEve.add(spring,0,gpEveRow);
        gpEve.add(okButton,0,gpEveRow+1,4,1);



        this.send(checkBoxes);
        this.setEffect(1,null);


    }

    private void removeDesignerBox()
    {
        int from = gpPern.getChildren().size() - gpPernRow +1 ;
        int to= gpPern.getChildren().size();
        gpPern.getChildren().remove(from,to);


        from = gpTras.getChildren().size() - gpTrasRow +1 ;
        to= gpTras.getChildren().size();
        gpTras.getChildren().remove(from,to);

        from = gpEve.getChildren().size() - gpEveRow -1 ; //comprende anche il bottone di conferma
        to= gpEve.getChildren().size();
        gpEve.getChildren().remove(from,to);

    }


    public void setEffect(int val,String citta)
    {
        BoxBlur bb = new BoxBlur();
        bb.setWidth(5);
        bb.setHeight(5);
        bb.setIterations(3);
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


            j=8;
            for (i=2;i<gpTrasRow;i++) {
                if(!( (Label) gpTras.getChildren().get(j)).getText().equals(citta)) {
                    int pos = gpTras.getChildren().size()-1;
                    pos=pos - (gpTrasRow -i) +1;
                    gpTras.getChildren().get(pos).setVisible(false);
                }
                j+=6;
            }

            of1.setEffect(bb);
            of3.setEffect(bb);

            of2.setDisable(false);
            of1.setDisable(true);
            of3.setDisable(true);

        }
        if(val ==3)
        {
            of3.setEffect(null);


            j=7;
            for (i=2;i<gpEveRow;i++) {
                if(!( (Label) gpEve.getChildren().get(j)).getText().equals(citta)) {
                    int pos = gpEve.getChildren().size()-1;
                    pos=pos - (gpEveRow -i) +1 -2;
                    gpEve.getChildren().get(pos).setVisible(false);
                }
                j+=5;
            }


            of1.setEffect(bb);
            of2.setEffect(bb);

            of3.setDisable(false);
            of1.setDisable(true);
            of2.setDisable(true);

        }

    }


    private StackPane buildPacchetti(){
        StackPane sp = new StackPane();
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
                aggrega.setOnAction(accessoCatalogoControl::aggregazioneOfferte);


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


        //Add VBox to leftLayout.
        leftLayout.setCenter(setButtonBox(dep));

        //Place into Application.
        componentLayout.setLeft(leftLayout);

    }

    public void mostraOfferta(Object offerta)
    {

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        dialog.setTitle("Descrizione Offerta");
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(20,0,20,20));


        gp.setAlignment(Pos.TOP_LEFT);
        ColumnConstraints cc = new ColumnConstraints(50,50, Double.MAX_VALUE,
                Priority.ALWAYS, HPos.LEFT, true);
        gp.getColumnConstraints().addAll(cc, cc);

       RowConstraints rc = new RowConstraints(5, 5, Double.MAX_VALUE,
                Priority.ALWAYS, VPos.TOP, true);
       gp.getRowConstraints().addAll(rc, rc);
        gp.setHgap(5);
        gp.setVgap(5);

        gp.add(new Label("Nome:"),0,0);
        gp.add(new Label( ((Offerta)offerta).getNome()),1,0);

        gp.add(new Label("Prezzo:"),0,1);
        gp.add(new Label(Integer.toString(((Offerta)offerta).getPrezzo())),1,1);

        gp.add(new Label("Città:"),0,2);
        gp.add(new Label(((Offerta)offerta).getCittà()),1,2);

        gp.add(new Label("Data:"),0,3);
        gp.add(new Label(((Offerta)offerta).getDataScadenza()),1,3);


        if(offerta instanceof OffertaPernotto)
        {

            gp.add(new Label("Numero notti:"),0,4);
            gp.add(new Label(Integer.toString (((OffertaPernotto)offerta).getNumeroNotti())),1,4);

            gp.add(new Label("Stelle: "),0,5);
            gp.add(new Label(Integer.toString (((OffertaPernotto)offerta).getNumeroNotti())),1,5);

            gp.add(new Label("Tipologia"),0,6);
            gp.add(new Label( ((OffertaPernotto)offerta).getTipologia()),1,6);

            Button ok = new Button("OK");
            ok.setMaxWidth(100.0);
            ok.setOnAction(accessoCatalogoControl::okListener);

            gp.add(ok,1,7,2,1);

        }

        if(offerta instanceof OffertaTrasporto) {


            gp.add(new Label("Città di partenza"), 0, 4);
            gp.add(new Label(((OffertaTrasporto) offerta).getCittàPartenza()), 1, 4);

            gp.add(new Label("Durata:"),0,5);
            gp.add(new Label( Integer.toString  (((OffertaTrasporto)offerta).getDurata())),1,5);

            gp.add(new Label("Tipologia"),0,6);
            gp.add(new Label( ((OffertaTrasporto)offerta).getTipologia()),1,6);

            Button ok = new Button("OK");
            ok.setMaxWidth(100.0);
            ok.setOnAction(accessoCatalogoControl::okListener);

            gp.add(ok,1,7,2,1);

        }

        if(offerta instanceof OffertaEvento)
        {
            gp.add(new Label("Tipologia"),0,4);
            gp.add(new Label( ((OffertaEvento)offerta).getTipologia()),1,4);

            Button ok = new Button("OK");
            ok.setMaxWidth(100.0);
            ok.setOnAction(accessoCatalogoControl::okListener);

            gp.add(ok,1,7,2,1);
        }


        Scene dialogScene = new Scene(gp, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    public void showCheckBox(boolean show)
    {

        if(show)
            this.addDesignerBox();

        else
            this.removeDesignerBox();


    }


}
