package Mvc.Control;

import Mvc.Model.AggiornaCostiModel;
import Mvc.View.AggiornaCostiView;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Simone on 29/12/2015.
 */
public class AggiornaCostiControl extends Application implements GpColleague {


    private AggiornaCostiModel aggiornaCostiModel;
    private AggiornaCostiView aggiornaCostiView;
    private GridPane gridPane;
    private GpMediatorImpl gpMediator;

    @Override
    public void start(Stage primaryStage) throws Exception {

        aggiornaCostiModel = new AggiornaCostiModel();

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
        String text =(  (TextField) gridPane.getChildren().get(1)).getText();



        aggiornaCostiModel.aggiornaCosti( Integer.parseInt(text));

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


}
