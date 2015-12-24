package Mvc.Control;

import Mvc.Model.AggregazioneOfferteModel;
import Mvc.View.AggregazioneOfferteView;
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


    private List<OffertaEvento> offertaEvento;
    private OffertaPernotto offertaPernotto;
    private OffertaTrasporto offertaTrasporto;
    private AggregazioneOfferteView aggregazioneOfferteView;
    private AggregazioneOfferteModel aggregazioneOfferteModel;
    private AccessoCatalogoControl accessoCatalogoControl;
    private GpMediatorImpl mediator;
    private GridPane gridPane;
    private List<PackObserver> list = new ArrayList<PackObserver>();



    public AggregazioneOfferteControl(OffertaPernotto per,OffertaTrasporto tras,List<OffertaEvento> even,AccessoCatalogoControl control){

        this.offertaPernotto = per ;
        this.offertaTrasporto = tras;
        this.offertaEvento = even;
        this.accessoCatalogoControl = control;

        mediator = new GpMediatorImpl();
        mediator.addColleague(this);

        this.addPackObserver(accessoCatalogoControl);

        aggregazioneOfferteModel = new AggregazioneOfferteModel();
    }

    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return mediator;}

    public void receive(GridPane gp) {
        this.gridPane = gp;

    }

    public void addPackObserver(PackObserver observer) {
        list.add( observer );
    }

    public void removePackObserver(PackObserver observer) {
        list.remove( observer );
    }

    public void notifyPackObserver(Pacchetto pacchetto) {
        for(PackObserver observer: list) {
            observer.addPack(pacchetto);
        }
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        aggregazioneOfferteView = new AggregazioneOfferteView(primaryStage,offertaEvento,offertaPernotto,offertaTrasporto,mediator,this);


    }


    public void creaPacchetto(ActionEvent event)
    {


        Pacchetto pacchetto = new Pacchetto();

        pacchetto.setOffertaPernotto(offertaPernotto);
        pacchetto.setOffertaTrasporto(offertaTrasporto);
        pacchetto.setOffertaEvento(offertaEvento);

        pacchetto.setNome(((TextField) gridPane.getChildren().get(1)).getText());
        pacchetto.setPrezzo( Integer.parseInt(((TextField) gridPane.getChildren().get(3)).getText()));

        aggregazioneOfferteModel.creaPacchetto(pacchetto);

        this.notifyPackObserver(pacchetto);

        ((Node)(event.getSource())).getScene().getWindow().hide();


    }
}
