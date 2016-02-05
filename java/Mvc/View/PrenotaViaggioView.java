package Mvc.View;

import Mvc.Control.PrenotaViaggioControl;
import Mvc.Model.entityPackage.OffertaEvento;
import Mvc.Model.entityPackage.Pacchetto;
import Mvc.Model.entityPackage.ViaggioGruppo;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import com.sun.deploy.xml.XMLable;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alessandro on 15/01/2016.
 */
public class PrenotaViaggioView implements GpColleague{

    private SplitPane layout;
    private Scene scene;
    private StackPane sp1;
    private StackPane sp2;
    private GridPane gridCredential;
    GridPane gpPack;
    int gpPackRow;
    PrenotaViaggioControl prenotaViaggioControl;
    ToggleGroup tg;
    private GpMediator gpMediator;

    public PrenotaViaggioView(Stage stage, PrenotaViaggioControl prenotaViaggioControl, GpMediator gpimpl){
        this.prenotaViaggioControl = prenotaViaggioControl;
        this.gpMediator = gpimpl;
        gpimpl.addColleague(this);

        this.tg = new ToggleGroup();

        this.gridCredential = new GridPane();

        double percentageWidth = 0.50;
        double percentageHeight = 0.50;



        layout = new SplitPane();
        layout.setPadding(new Insets(20,0,20,20));
        layout.setOrientation(Orientation.HORIZONTAL);
        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();


        sp1 = buildLeft();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(sp1);

        sp2 = new StackPane();

        layout.getItems().addAll(scrollPane, sp2);
        layout.setDividerPositions(0.5f, 0.5f);

        this.scene = new Scene(layout, percentageWidth, percentageHeight);

        scene.getStylesheets().add("JMetroLightTheme.css");
        stage.getIcons().add(new Image("icon.png"));

        stage.setTitle("Creazione Viaggio Gruppo");
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

    public void addTrip(ViaggioGruppo viaggoGruppo)
    {
        int i;

        int from = gpPack.getChildren().size();
        int fromRow = gpPackRow;


        RadioButton rb = new RadioButton();
        rb.setId(Integer.toString(viaggoGruppo.getVid()));
        rb.setToggleGroup(this.tg);
        rb.setOnAction(this::buildRight);

        gpPack.add(new Label(viaggoGruppo.getNumPacket().getNome()), 0, gpPackRow);
        gpPack.add(new Label(Integer.toString(viaggoGruppo.getNumPacket().getPrezzo())), 1, gpPackRow);
        gpPack.add(new Label(viaggoGruppo.getNumPacket().getOffertaPernotto().getCittà()), 2, gpPackRow);
        gpPack.add(new Label(viaggoGruppo.getNumPacket().getOffertaPernotto().getTipologia()), 4, gpPackRow);
        gpPack.add(new Label(Integer.toString(viaggoGruppo.getNumPacket().getOffertaPernotto().getStelle())), 5, gpPackRow);
        gpPack.add(new Label(Integer.toString(viaggoGruppo.getNumPacket().getOffertaPernotto().getNumeroNotti())), 6, gpPackRow);
        gpPack.add(new Label(viaggoGruppo.getNumPacket().getOffertaTrasporto().getCittàPartenza()), 8, gpPackRow);
        gpPack.add(new Label(viaggoGruppo.getNumPacket().getOffertaTrasporto().getTipologia()), 9, gpPackRow);
        gpPack.add(new Label(Integer.toString(viaggoGruppo.getNumPacket().getOffertaTrasporto().getDurata())), 10, gpPackRow);
        gpPack.add(rb,20,gpPackRow);

        gpPack.add(new Label(Integer.toString(viaggoGruppo.getMinP())), 15, gpPackRow);
        gpPack.add(new Label(Integer.toString(viaggoGruppo.getMaxP())), 16, gpPackRow);
        gpPack.add(new Label(Double.toString(viaggoGruppo.getDiscount())), 17, gpPackRow);
        gpPack.add(new Label(Integer.toString(viaggoGruppo.getNumreserved())), 18, gpPackRow);

        int j=0;
        for (OffertaEvento events : viaggoGruppo.getNumPacket().getOffertaEvento()) {
            gpPack.add(new Label(events.getNome()), 12, gpPackRow);
            gpPack.add(new Label(events.getTipologia()), 13, gpPackRow);

            j+=2;
            gpPackRow++;
        }



        Separator separator = new Separator();
        gpPack.add(separator,0,gpPackRow,19,1);
        gpPackRow++;

        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);

        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);

        Separator separator3 = new Separator();
        separator3.setOrientation(Orientation.VERTICAL);

        Separator separator4 = new Separator();
        separator4.setOrientation(Orientation.VERTICAL);

        Separator separator5 = new Separator();
        separator4.setOrientation(Orientation.VERTICAL);

        gpPack.add(separator1,3,fromRow,1,viaggoGruppo.getNumPacket().getOffertaEvento().size());
        gpPack.add(separator2,7,fromRow,1,viaggoGruppo.getNumPacket().getOffertaEvento().size());
        gpPack.add(separator3,11,fromRow,1,viaggoGruppo.getNumPacket().getOffertaEvento().size());
        gpPack.add(separator4,14,fromRow,1,viaggoGruppo.getNumPacket().getOffertaEvento().size());
        gpPack.add(separator5,19,fromRow,1,viaggoGruppo.getNumPacket().getOffertaEvento().size());
    }

    private void buildRight(ActionEvent event) {

        GridPane grdpn = new GridPane();

        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);

        Spinner<Integer> numreserved = new Spinner<Integer>();
        numreserved.setValueFactory(svf);
        numreserved.setEditable(true);
        numreserved.setPrefWidth(80);

        Button buttonreserve = new Button("Seleziona numero prenotati");
        grdpn.add(numreserved,0,0);
        grdpn.add(buttonreserve,1,0);
        buttonreserve.setOnAction(this::buildUserCredentials);

        /*
        GridPane gp = new GridPane();

        Label nameL = new Label("Nome partecipante");
        Label surnameL = new Label("Cognome partecipante");
        Label birthdayL = new Label("Data Nascita");

        TextField txname = new TextField();
        TextField txsurname = new TextField();
        TextField txbirthday = new TextField();

        gp.add(nameL,0,0);
        gp.add(txname,1,0);
        gp.add(surnameL,0,1);
        gp.add(txsurname,1,1);
        gp.add(birthdayL,0,2);
        gp.add(txbirthday,1,2);
        gp.setVgap(10);
        gp.setHgap(3);*/

        sp2.getChildren().add(grdpn);

        gpMediator.send(grdpn,this);
    }

    private void buildUserCredentials(ActionEvent event){

        //System.out.println("Valore di numreserved " + Integer.toString(numreserved));
        GridPane gp = ((GridPane)((Button) event.getSource()).getParent());
        int numreserved = ((Spinner<Integer>) gp.getChildren().get(0)).getValue();
        gridCredential.getChildren().clear();

        /*
        int j =0;
        for(int i=0; i<numreserved*3;i = i + 3){

            Label nameL = new Label("Nome partecipante");
            Label surnameL = new Label("Cognome partecipante");
            Label birthdayL = new Label("Data Nascita");

            TextField txname = new TextField();
            TextField txsurname = new TextField();
            TextField txbirthday = new TextField();

            gridCredential.add(nameL,j,i);
            gridCredential.add(txname,j+1,i);
            gridCredential.add(surnameL,j,i+1);
            gridCredential.add(txsurname,j+1,i+1);
            gridCredential.add(birthdayL,j,i+2);
            gridCredential.add(txbirthday,j+1,i+2);
            gridCredential.setVgap(20);
            gridCredential.setHgap(3);
        }*/

        for(int i=0;i<numreserved;i++){

            GridPane grd = new GridPane();

            Label lblL = new Label("Inserire credenziali partecipante");
            Label nameL = new Label("Nome partecipante");
            Label surnameL = new Label("Cognome partecipante");
            Label birthdayL = new Label("Data Nascita");

            TextField txname = new TextField();
            TextField txsurname = new TextField();
            TextField txbirthday = new TextField();

            grd.add(lblL,0,0);
            grd.addRow(1);
            grd.add(nameL,0,2);
            grd.add(txname,1,2);
            grd.add(surnameL,0,3);
            grd.add(txsurname,1,3);
            grd.add(birthdayL,0,4);
            grd.add(txbirthday,1,4);
            grd.addRow(5);
            grd.setVgap(10);
            grd.setHgap(3);

            gridCredential.add(grd,0,i);
        }

        Button okButton = new Button("OK");
        gridCredential.add(okButton,0,numreserved);

        okButton.setId(((RadioButton)tg.getSelectedToggle()).getId());
        okButton.setOnAction(prenotaViaggioControl::reserveTrip);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridCredential);

        gp.add(scrollPane,0,1);

        gpMediator.send(gridCredential,this);
    }

    private StackPane buildLeft() {

        StackPane sp = new StackPane();

        sp.setPadding(new Insets(0,0,0,5.0));

        gpPackRow = 1;

        gpPack = new GridPane();


        gpPack.setHgap(15);
        gpPack.setVgap(10);

        Label name = new Label("Nome Viaggio Gruppo");
        name.getStyleClass().add("text-titlesmall");

        Label price = new Label("Prezzo");
        price.getStyleClass().add("text-titlesmall");

        Label citta = new Label("Città");
        citta.getStyleClass().add("text-titlesmall");

        Label tipoPern= new Label ("Tipologia");
        tipoPern.getStyleClass().add("text-titlesmall");

        Label stelle= new Label ("Stelle");
        stelle.getStyleClass().add("text-titlesmall");

        Label notti = new Label("Numero notti");
        notti.getStyleClass().add("text-titlesmall");

        Label cittaP = new Label("Partenza");
        cittaP.getStyleClass().add("text-titlesmall");

        Label tipoTras = new Label("Tipologia");
        tipoTras.getStyleClass().add("text-titlesmall");

        Label durata = new Label ("Durata");
        durata.getStyleClass().add("text-titlesmall");

        Label evento = new Label("Nome evento");
        evento.getStyleClass().add("text-titlesmall");

        Label tipoEven = new Label ("Tipologia");
        tipoEven.getStyleClass().add("text-titlesmall");

        Label pernotto = new Label("Pernotto");
        pernotto.getStyleClass().add("text-titlesmall");

        Label trasporto = new Label("Trasporto");
        trasporto.getStyleClass().add("text-titlesmall");

        Label eventi = new Label("Eventi");
        eventi.getStyleClass().add("text-titlesmall");

        Label caratteristiche = new Label("Caratteristiche Viaggio Gruppo");
        caratteristiche.getStyleClass().add("text-titlesmall");

        Label pacchetto = new Label ("Pacchetto");
        pacchetto.getStyleClass().add("text-titlesmall");

        Label minL = new Label("Minimo numero partecipanti");
        minL.getStyleClass().add("text-titlesmall");

        Label maxL = new Label("Massimo numero partecipanti");
        maxL.getStyleClass().add("text-titlesmall");

        Label discountL = new Label("Sconto da applicare");
        discountL.getStyleClass().add("text-titlesmall");

        Label reservedL = new Label("Numero Prenotati");
        reservedL.getStyleClass().add("text-titlesmall");


        Separator separator = new Separator();
        gpPack.add(separator,0,gpPackRow,20,1);
        gpPackRow++;




        gpPack.add(pacchetto,1,2,3,1);
        gpPack.add(pernotto,4,2,3,1);
        gpPack.add(trasporto,8,2,2,1);
        gpPack.add(eventi,12,2,2,1);
        gpPack.add(caratteristiche,16,2,4,1);


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

        gpPack.add(minL,15,3);
        gpPack.add(maxL,16,3);
        gpPack.add(discountL,17,3);
        gpPack.add(reservedL,18,3);



        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);

        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);

        Separator separator3 = new Separator();
        separator3.setOrientation(Orientation.VERTICAL);

        Separator separator4 = new Separator();
        separator4.setOrientation(Orientation.VERTICAL);

        Separator separator6 = new Separator();
        separator4.setOrientation(Orientation.VERTICAL);

        Separator separator7 = new Separator();
        separator4.setOrientation(Orientation.VERTICAL);

        gpPack.add(separator1,3,1,1,5);
        gpPack.add(separator2,7,1,1,5);
        gpPack.add(separator3,11,1,1,5);
        gpPack.add(separator4,14,1,1,5);
        gpPack.add(separator6,19,1,1,5);

        gpPackRow+=2;

        Separator separator5 = new Separator();
        gpPack.add(separator5,0,gpPackRow,20,1);
        gpPackRow++;

        List<ViaggioGruppo> viaggi = prenotaViaggioControl.getTrips();
        if(viaggi!=null) {
            for (ViaggioGruppo viaggioGruppo : viaggi) {
                this.addTrip(viaggioGruppo);
            }

        }


        sp.getChildren().add(gpPack);



        return sp;

    }


}
