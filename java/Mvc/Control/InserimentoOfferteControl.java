package Mvc.Control;

import Mvc.View.InserimentoOfferteView;
import javafx.application.Application;
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
        inserimentoOfferteView = new InserimentoOfferteView(primaryStage);
    }


}
