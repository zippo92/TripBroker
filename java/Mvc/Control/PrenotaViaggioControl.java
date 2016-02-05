package Mvc.Control;

import Mvc.Model.entityPackage.Pacchetto;
import Mvc.Model.entityPackage.ViaggioGruppo;
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
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by Alessandro on 15/01/2016.
 */
public class PrenotaViaggioControl extends Application implements GpColleague{

    private static PrenotaViaggioControl instance;
    private PrenotaViaggioView prenotaViaggioView;
    private GpMediator mediator;
    private GridPane gp;
    private PrenotaViaggioControl(){
        mediator = new GpMediatorImpl();
        mediator.addColleague(this);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        prenotaViaggioView = new PrenotaViaggioView(primaryStage,this,mediator);

    }

    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return mediator;}

    public void receive(GridPane gp) {
        this.gp = gp;
    }

    public List<ViaggioGruppo> getTrips(){ return DAOFactory.getViaggioGruppoDAO().getList();}

    public static PrenotaViaggioControl getInstance(){
        if(instance == null){
            instance = new PrenotaViaggioControl();
        }
        return instance;
    }


    public void reserveTrip(ActionEvent event){

        Iterator<Node> iterator = gp.getChildren().iterator();
        ArrayList <String> ls = new ArrayList<String>();
        while(iterator.hasNext()){
            Node node= iterator.next();
            if(node instanceof GridPane) {
                ArrayList<String> templist = new ArrayList<>();
                List<Node> list = ((GridPane) node).getChildren();
                for (Node n : list) {
                    if (n instanceof TextField)
                        templist.add(((TextField)n).getText());
                }

                ls.add(String.join(" ",templist));
            }
        }

        DAOFactory.getViaggioGruppoDAO().addReserve(Integer.parseInt(((Button)event.getSource()).getId()),ls.size(),ls);
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }

}

