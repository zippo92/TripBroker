package Mvc.Control;

import Mvc.View.InserimentoOfferteView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 * Created by Simone on 14/12/2015.
 */
public class InserimentoOfferteControl extends Application{

    InserimentoOfferteView inserimentoOfferteView;

    public InserimentoOfferteControl()
    {


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        inserimentoOfferteView = new InserimentoOfferteView(primaryStage,this);
    }


    public void radioListener (ActionEvent event)
    {
        RadioButton o = (RadioButton) event.getSource();

        if(o.getText().equals("Pernotto"))
            inserimentoOfferteView.buildRight(0);
        else if (o.getText().equals("Trasporto"))
            inserimentoOfferteView.buildRight(1);
        else if (o.getText().equals("Eventi"))
            inserimentoOfferteView.buildRight(2);
    }

}
