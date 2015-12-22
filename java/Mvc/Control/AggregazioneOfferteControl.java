package Mvc.Control;

import Mvc.Model.AggregazioneOfferteModel;
import Mvc.View.AggregazioneOfferteView;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;
import entityPackage.Pacchetto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Simone on 22/12/2015.
 */
public class AggregazioneOfferteControl extends Application implements GpColleague {


    private List<OffertaEvento> offertaEvento;
    private OffertaPernotto offertaPernotto;
    private OffertaTrasporto offertaTrasporto;
    private AggregazioneOfferteView aggregazioneOfferteView;
    private AggregazioneOfferteModel aggregazioneOfferteModel;
    private GpMediatorImpl mediator;
    private GridPane gridPane;

    public AggregazioneOfferteControl(OffertaPernotto per,OffertaTrasporto tras,List<OffertaEvento> even){

        this.offertaPernotto = per ;
        this.offertaTrasporto = tras;
        this.offertaEvento = even;

        mediator = new GpMediatorImpl();
        mediator.addColleague(this);

        aggregazioneOfferteModel = new AggregazioneOfferteModel();
    }

    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return mediator;}

    public void receive(GridPane gp) {
        this.gridPane = gp;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        aggregazioneOfferteView = new AggregazioneOfferteView(primaryStage,offertaEvento,offertaPernotto,offertaTrasporto,mediator,this);


    }


    public void creaPacchetto(ActionEvent event)
    {

        System.out.println(((TextField) gridPane.getChildren().get(1)).getText());
        System.out.println(((TextField) gridPane.getChildren().get(3)).getText());
        System.out.println(((TextField) gridPane.getChildren().get(5)).getText());

        Pacchetto pacchetto = new Pacchetto();

        pacchetto.setOffertaPernotto(offertaPernotto);
        pacchetto.setOffertaTrasporto(offertaTrasporto);
        pacchetto.setOffertaEvento(offertaEvento);

        pacchetto.setNome(((TextField) gridPane.getChildren().get(1)).getText());
        pacchetto.setPrezzo( Integer.parseInt(((TextField) gridPane.getChildren().get(3)).getText()));

        aggregazioneOfferteModel.creaPacchetto(pacchetto);

    }
}
