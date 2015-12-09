package Mvc.Control;

import Mvc.View.AccessoCatalogoView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Simone on 07/12/2015.
 */
public class AccessoCatalogoControl extends Application{

    String utente;
    private AccessoCatalogoView accessoCatalogoView;

    public AccessoCatalogoControl(String user){

        utente = user;
    }





    @Override
    public void start(Stage primaryStage) throws Exception {
        accessoCatalogoView = new AccessoCatalogoView(primaryStage,utente);
    }

}
