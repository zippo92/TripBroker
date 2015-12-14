package Mvc.Control;

import Mvc.View.AccessoCatalogoView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Created by Simone on 07/12/2015.
 */
public class AccessoCatalogoControl extends Application{

    String utente;
    private AccessoCatalogoView accessoCatalogoView;
    private InserimentoOfferteControl inserimentoOfferteControl;

    public AccessoCatalogoControl(String user){

        utente = user;
        inserimentoOfferteControl = new InserimentoOfferteControl();
    }





    @Override
    public void start(Stage primaryStage) throws Exception {
        accessoCatalogoView = new AccessoCatalogoView(primaryStage,utente,this);
    }


    public void inserimentoOfferte (ActionEvent event)  {

        try {
            inserimentoOfferteControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
