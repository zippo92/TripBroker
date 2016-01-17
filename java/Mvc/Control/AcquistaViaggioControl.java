package Mvc.Control;

import Mvc.Model.entityPackage.ViaggioGruppo;
import Mvc.View.AcquistaViaggioView;
import Mvc.View.PrenotaViaggioView;
import Patterns.DAOFactory.DAOFactory;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alessandro on 17/01/2016.
 */
public class AcquistaViaggioControl extends Application implements GpColleague {

    private static AcquistaViaggioControl instance;
    private AcquistaViaggioView acquistaViaggioView;
    private GpMediatorImpl mediator;
    private GridPane gp;

    private AcquistaViaggioControl(){
        mediator = new GpMediatorImpl();
        mediator.addColleague(this);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        acquistaViaggioView = new AcquistaViaggioView(primaryStage,this,mediator);

    }

    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return mediator;}

    public void receive(GridPane gp) {
        this.gp = gp;
    }

    public List<ViaggioGruppo> getTrips(){ return DAOFactory.getViaggioGruppoDAO().getList();}

    public List<ViaggioGruppo> getBuyTrips(){ return DAOFactory.getViaggioGruppoDAO().findBuyTrip();}

    public static AcquistaViaggioControl getInstance(){
        if(instance == null){
            instance = new AcquistaViaggioControl();
        }
        return instance;
    }
//TODO Applicare sconto

    public void buyTrip(ActionEvent event){

        try {
            String idCard = ((TextField) gp.getChildren().get(1)).getText();

            int id = Integer.parseInt(((Button) event.getSource()).getId());
            DAOFactory.getViaggioGruppoDAO().delTrip(id);
            System.out.println("Pagamento eseguito");
        }
        catch (Exception e){
            if(e instanceof HibernateException)
                System.out.println("Carta rifiutata o non esistente");
            if(e instanceof NumberFormatException)
                System.out.println("Errore nell'id del viaggio");
        }
        finally {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }

}

