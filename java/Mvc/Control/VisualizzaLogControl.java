package Mvc.Control;

import Mvc.View.VisualizzaLogView;
import Patterns.DAOFactory.DAOFactory;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import entityPackage.Log;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Simone on 12/01/2016.
 */
public class VisualizzaLogControl extends Application implements GpColleague{

    VisualizzaLogView visualizzaLogView;
    GpMediatorImpl mediator;
    GridPane filtro;
    GridPane gp;

    @Override
    public void start(Stage primaryStage) throws Exception {

        mediator = new GpMediatorImpl();

        mediator.addColleague(this);

        visualizzaLogView = new VisualizzaLogView(primaryStage,mediator,this);


    }

    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return mediator;}

    public void receive(GridPane gp) {

        if(gp.getId().equals("filtro"))
            filtro = gp;
        else if(gp.getId().equals("gp"))
            this.gp = gp;
    }

    public void idfilter(ActionEvent event)
    {
        System.out.println(((TextField)filtro.getChildren().get(1)).getText());

        String id = (((TextField)filtro.getChildren().get(1)).getText());

        List<Log> logs = (List<Log>) DAOFactory.getLogDAO().findPerId(id);



        gp.getChildren().remove(5,gp.getChildren().size());

        visualizzaLogView.fillPane(logs);

    }

    public void rolefilter(ActionEvent event)
    {
        System.out.println(((TextField)filtro.getChildren().get(1)).getText());

        String role = (((ComboBox)filtro.getChildren().get(3)).getSelectionModel().getSelectedItem().toString());

        List<Log> logs = (List<Log>) DAOFactory.getLogDAO().findPerRole(role);



        gp.getChildren().remove(5,gp.getChildren().size());

        visualizzaLogView.fillPane(logs);

    }


    public void showall(ActionEvent event)
    {

        List<Log> logs = DAOFactory.getLogDAO().getList();

        gp.getChildren().remove(5,gp.getChildren().size());

        visualizzaLogView.fillPane(logs);


    }



}
