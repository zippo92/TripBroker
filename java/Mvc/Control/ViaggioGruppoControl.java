package Mvc.Control;

import Mvc.Model.entityPackage.Pacchetto;
import Mvc.Model.entityPackage.ViaggioGruppo;
import Mvc.View.ViaggioGruppoView;
import Patterns.DAOFactory.DAOFactory;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Alessandro on 15/01/2016.
 */
public class ViaggioGruppoControl extends Application implements GpColleague{

    private static ViaggioGruppoControl instance;
    private ViaggioGruppoView viaggioGruppoView;
    private GpMediator mediator;
    private GridPane gp;

    private ViaggioGruppoControl(){
        mediator = new GpMediatorImpl();
        mediator.addColleague(this);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        viaggioGruppoView = new ViaggioGruppoView(primaryStage,this,mediator);

    }

    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return mediator;}

    public void receive(GridPane gp) {
        this.gp = gp;
    }

    public List<Pacchetto> getPack(){ return DAOFactory.getPacchettoDAO().getList();}

    public static ViaggioGruppoControl getInstance(){
        if(instance == null){
            instance = new ViaggioGruppoControl();
        }
        return instance;
    }

    public void addTrip(ActionEvent event){
        ViaggioGruppo vg = new ViaggioGruppo();
        vg.setMinP(((Spinner<Integer>)this.gp.getChildren().get(1)).getValue());
        vg.setMaxP(((Spinner<Integer>)this.gp.getChildren().get(3)).getValue());
        vg.setDiscount(((Spinner<Double>)this.gp.getChildren().get(5)).getValue());
        vg.setNumreserved(0);

        Pacchetto pacchetto = DAOFactory.getPacchettoDAO().getPackByID(Integer.parseInt(((Button)event.getSource()).getId()));
        vg.setNumPacket(pacchetto);

        DAOFactory.getViaggioGruppoDAO().store(vg);
        ((Node)(event.getSource())).getScene().getWindow().hide();


    }

}
