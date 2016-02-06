package Mvc.Control;

import Mvc.Model.entityPackage.OffertaEvento;
import Mvc.Model.entityPackage.OffertaPernotto;
import Mvc.Model.entityPackage.OffertaTrasporto;
import Mvc.TipoOfferta;
import Mvc.View.AggiornaCostiView;
import Patterns.DAOFactory.DAOFactory;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Simone on 29/12/2015.
 */
public class AggiornaCostiControl extends Application implements GpColleague {

    private static AggiornaCostiControl instance;
    private AggiornaCostiView aggiornaCostiView;
    private GridPane gridPane;
    private GpMediator gpMediator;

    private AggiornaCostiControl(){

    }

    public static AggiornaCostiControl getInstance()
    {
        if (instance == null)
        {
            instance = new AggiornaCostiControl();
        }

        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        gpMediator = new GpMediatorImpl();
        gpMediator.addColleague(this);

        aggiornaCostiView = new AggiornaCostiView(primaryStage,this,gpMediator);

    }

    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return gpMediator;}

    public void receive(GridPane gp) {
        gridPane = gp;
    }




    public void aggiorna(ActionEvent event)
    {
        Integer val =(  (Spinner<Integer>) gridPane.getChildren().get(1)).getValue();



        this.aggiornaCosti( val);

        ((Node)(event.getSource())).getScene().getWindow().hide();

    }

    public void plus(ActionEvent event)
    {

        String text =(  (TextField) gridPane.getChildren().get(1)).getText();

        int val = Integer.parseInt(text) + 5;

        ((TextField) gridPane.getChildren().get(1)).setText( Integer.toString(val));

    }

    public void minus(ActionEvent event)
    {

        String text =(  (TextField) gridPane.getChildren().get(1)).getText();

        int val = Integer.parseInt(text) - 5;

        ((TextField) gridPane.getChildren().get(1)).setText( Integer.toString(val));

    }

    public void aggiornaCosti(int val)
    {

        List<OffertaPernotto> offertaPernottos =(List<OffertaPernotto>) DAOFactory.getDAOFactory(TipoOfferta.OffertaPernotto).getOffertaDAO().getList();
        for(OffertaPernotto offerta : offertaPernottos)
            DAOFactory.getDAOFactory(TipoOfferta.OffertaPernotto).getOffertaDAO().modPrice(offerta.getPerID(),val);


        List<OffertaTrasporto> offertaTrasportos =(List<OffertaTrasporto>) DAOFactory.getDAOFactory(TipoOfferta.OffertaTrasporto).getOffertaDAO().getList();
        for(OffertaTrasporto offerta : offertaTrasportos)
            DAOFactory.getDAOFactory(TipoOfferta.OffertaTrasporto).getOffertaDAO().modPrice(offerta.getTrasID(),val);



        List<OffertaEvento> offertaEventos =(List<OffertaEvento>) DAOFactory.getDAOFactory(TipoOfferta.OffertaEvento).getOffertaDAO().getList();
        for(OffertaEvento offerta : offertaEventos)
            DAOFactory.getDAOFactory(TipoOfferta.OffertaEvento).getOffertaDAO().modPrice(offerta.getEveID(),val);
    }


}
