package Mvc.View;

import Mvc.Control.AggregazioneOfferteControl;
import Mvc.LimitedTextField;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Simone on 22/12/2015.
 */
public class AggregazioneOfferteView implements GpColleague {

    private SplitPane layout;
    private Scene scene;
    private StackPane sp1;
    private StackPane sp2;
    private List<OffertaEvento> offertaEvento;
    private OffertaPernotto offertaPernotto;
    private OffertaTrasporto offertaTrasporto;
    private GpMediatorImpl gpMediator;
    private AggregazioneOfferteControl aggregazioneOfferteControl;


    /*
    * è una split pane con linea orizzontale, sopra varie info del pacchetto ,sotto le offerte contenute
    *
    * */
    public AggregazioneOfferteView(Stage stage ,List<OffertaEvento> offertaEvento,OffertaPernotto offertaPernotto,OffertaTrasporto offertaTrasporto,GpMediatorImpl mediator,
                                   AggregazioneOfferteControl control)
    {

        aggregazioneOfferteControl = control;
        gpMediator = mediator;
        gpMediator.addColleague(this);

        layout = new SplitPane();

        this.offertaEvento = offertaEvento;
        this.offertaPernotto = offertaPernotto;
        this.offertaTrasporto = offertaTrasporto;

        double percentageWidth = 0.40;
        double percentageHeight = 0.40;
        layout.setPadding(new Insets(20,0,20,20));

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();

        buildtop();
        buildbot();

        layout.setOrientation(Orientation.VERTICAL);
        layout.getItems().addAll(sp1, sp2);
        layout.setDividerPositions(0.3f, 0.6f);

        this.scene = new Scene(layout, percentageWidth, percentageHeight);

        this.scene.getStylesheets().add("JMetroLightTheme.css");


        stage.getIcons().add(new Image("icon.png"));

        stage.setTitle("Inserimento nuovo pacchetto");
        stage.setScene(scene);
        stage.show();
    }


    /*
    * funzioni per il mediator, per inviare una gridPane
    * */
    public void send(GridPane gp) {
        gpMediator.send(gp,this);
    }
    public GpMediator getGpMediator() {return gpMediator;}

    public void receive(GridPane gp) {
        //Never Reched
    }

    /*
    *  costruisce la parte superiore
    * */
    private void buildtop()
    {
        sp1 = new StackPane();

        GridPane gp = new GridPane();
        gp.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        gp.setVgap(10);


        Label name = new Label("Nome Pacchetto");
        name.setFont(new Font("Arial",15));

        Label price = new Label("Prezzo");
        price.setFont(new Font("Arial",15));

        Label citta = new Label("Città");

        LimitedTextField LimitedTextField = new LimitedTextField(offertaPernotto.getCittà());
        LimitedTextField.setEditable(false);


        LimitedTextField priceField = new LimitedTextField();

        int somma= offertaPernotto.getPrezzo() + offertaTrasporto.getPrezzo();

        for(OffertaEvento evento : offertaEvento)
            somma+=evento.getPrezzo();

        priceField.setPromptText(Integer.toString(somma));

        LimitedTextField nameField = new LimitedTextField();


        gp.add(name,0,0);
        gp.add(nameField,1,0);

        gp.add(price,0,1);
        gp.add(priceField,1,1);

        gp.add(citta,0,2);
        gp.add(LimitedTextField,1,2);

        Pane blank = new Pane();

        gp.add(blank,0,2);

        gpMediator.send(gp,this);

        sp1.getChildren().add(gp);

    }


    /*
    * Costruisce la parte inferiore utilizzando le offerte inviategli nel costruttore
    * */
    private void buildbot()
    {
        GridPane gp = new GridPane();
        sp2 = new StackPane();

        gp.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        gp.setVgap(10);



        Label offerte = new Label("Offerte contenute:");
        offerte.setFont(new Font("Arial",15));

        gp.add(offerte,0,2,4,1);

        Label nome = new Label("Nome offerta");
        nome.setFont(new Font("Arial",15));

        Label prezzo = new Label("Prezzo offerta");
        prezzo.setFont(new Font("Arial",15));

        Label tipo = new Label("Tipologia");
        tipo.setFont(new Font("Arial",15));

        Label altro = new Label("Altro");
        altro.setFont(new Font("Arial",15));

        gp.add(nome,0,3);
        gp.add(prezzo,1,3);
        gp.add(tipo,2,3);
        gp.add(altro,3,3,2,1);


        gp.add(new Label(offertaPernotto.getNome()),0,4);
        gp.add(new Label(Integer.toString(offertaPernotto.getPrezzo())),1,4);
        gp.add(new Label(offertaPernotto.getTipologia()),2,4);
        gp.add(new Label("Stelle:" + Integer.toString(offertaPernotto.getStelle())+"stelle"),3,4);


        Pane blank = new Pane();

        gp.add(blank,0,5);

        gp.add(new Label(offertaTrasporto.getNome()),0,6);
        gp.add(new Label(Integer.toString(offertaTrasporto.getPrezzo())),1,6);

        gp.add(new Label(offertaTrasporto.getTipologia()),2,6);
        gp.add(new Label("Città di partenza: " + offertaTrasporto.getCittàPartenza()),3,6);
        gp.add(new Label("Durata: " + offertaTrasporto.getDurata()),4,6);

        int i=7;
        for(OffertaEvento evento : offertaEvento)
        {
            gp.add(new Label(evento.getNome()),0,i);
            gp.add(new Label(Integer.toString(evento.getPrezzo())),1,i);
            gp.add(new Label(evento.getTipologia()),2,i);
            i++;

        }

        Button okButton = new Button("Conferma pacchetto");
        okButton.setOnAction(aggregazioneOfferteControl::creaPacchetto);


        gp.add(okButton,0,i+1,3,1);



        sp2.getChildren().add(gp);

    }

}
