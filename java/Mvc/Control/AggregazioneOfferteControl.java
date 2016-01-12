package Mvc.Control;

import Mvc.View.AggregazioneOfferteView;
import Patterns.DAOFactory.DAOFactory;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import Patterns.PackObserver.PackObserver;
import Patterns.PackObserver.PackSubject;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;
import entityPackage.Pacchetto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 22/12/2015.
 */
public class AggregazioneOfferteControl extends Application implements GpColleague,PackSubject {

    private static AggregazioneOfferteControl instance;
    private List<OffertaEvento> offertaEvento;
    private OffertaPernotto offertaPernotto;
    private OffertaTrasporto offertaTrasporto;
    private AggregazioneOfferteView aggregazioneOfferteView;
//    private AggregazioneOfferteModel aggregazioneOfferteModel;
    private AccessoCatalogoControl accessoCatalogoControl;
    private GpMediatorImpl mediator;
    private GridPane gridPane;
    private List<PackObserver> list = new ArrayList<PackObserver>();

/*
*   gli vengono passate le offerte da aggregare ,istanzia il medietor tra AggregazioneOfferteControl e AggregazioneOfferteView
*
* */
    public AggregazioneOfferteControl(OffertaPernotto per,OffertaTrasporto tras,List<OffertaEvento> even,AccessoCatalogoControl control){

        this.offertaPernotto = per ;
        this.offertaTrasporto = tras;
        this.offertaEvento = even;
        this.accessoCatalogoControl = control;

        mediator = new GpMediatorImpl();
        mediator.addColleague(this);

        this.addPackObserver(accessoCatalogoControl);

//        aggregazioneOfferteModel = new AggregazioneOfferteModel();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        aggregazioneOfferteView = new AggregazioneOfferteView(primaryStage,offertaEvento,offertaPernotto,offertaTrasporto,mediator,this);


    }

    /*
    *  funzioni per ricevere la gridpane della view attraverso il mediator
    * */
    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return mediator;}

    public void receive(GridPane gp) {
        this.gridPane = gp;

    }


    /*
    *
    *  funzioni dell'observer per notificare ad AccessoCatalogoView che è stata aggiunta una riga nella tabella dei pacchetti
    *
    *
    * */
    public void addPackObserver(PackObserver observer) {
        list.add( observer );
    }

    public void removePackObserver(PackObserver observer) {
        list.remove( observer );
    }

    public void notifyPackObserver(boolean delete,Pacchetto pacchetto) {
        for(PackObserver observer: list) {
            observer.addPack(pacchetto);
        }
    }




    /*
    *
    *   Listener del pulsante "Conferma Pacchetto" che permette di inserire il pacchetto nel db e di fare la notifica all'observer
    * */

    public void creaPacchetto(ActionEvent event)
    {
        boolean red = false;

        String nome = null;
        int prezzo = 0;

        if(! (((TextField) gridPane.getChildren().get(1)).getText().equals("")) ) {
            nome = ((TextField) gridPane.getChildren().get(1)).getText();
            gridPane.getChildren().get(1).setStyle("");
        }
        else
        {
            red = true;
            gridPane.getChildren().get(3).setStyle("-fx-border-color: red");
        }


        if(!  (((TextField) gridPane.getChildren().get(3)).getText().equals(""))) {
            try{
                prezzo = Integer.parseInt(((TextField) gridPane.getChildren().get(3)).getText());
                gridPane.getChildren().get(3).setStyle("");
            } catch (NumberFormatException e)
            {
                red = true;
                gridPane.getChildren().get(3).setStyle("-fx-border-color: red");
            }
        }
        else
        {
            red = true;
            gridPane.getChildren().get(3).setStyle("-fx-border-color: red");
        }


        if(!red) {


            Pacchetto pacchetto = new Pacchetto();

            pacchetto.setOffertaPernotto(offertaPernotto);
            pacchetto.setOffertaTrasporto(offertaTrasporto);
            pacchetto.setOffertaEvento(offertaEvento);
            pacchetto.setStato(false);

            pacchetto.setNome(((TextField) gridPane.getChildren().get(1)).getText());
            pacchetto.setPrezzo(Integer.parseInt(((TextField) gridPane.getChildren().get(3)).getText()));

//        aggregazioneOfferteModel.creaPacchetto(pacchetto);

            accessoCatalogoControl.addLog(pacchetto,"aggiunto");

            DAOFactory.getPacchettoDAO().store(pacchetto);


            this.notifyPackObserver(false,pacchetto);

            ((Node) (event.getSource())).getScene().getWindow().hide();
        }

    }
}
