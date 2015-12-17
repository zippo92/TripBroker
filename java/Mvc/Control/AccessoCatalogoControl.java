package Mvc.Control;

import Mvc.Model.AccessoCatalogoModel;
import Mvc.View.AccessoCatalogoView;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Simone on 07/12/2015.
 */
public class AccessoCatalogoControl extends Application{

    String utente;
    private AccessoCatalogoView accessoCatalogoView;
    private InserimentoOfferteControl inserimentoOfferteControl;
    private AccessoCatalogoModel accessoCatalogoModel;

    public AccessoCatalogoControl(String user){

        utente = user;
        inserimentoOfferteControl = new InserimentoOfferteControl();
        accessoCatalogoModel = new AccessoCatalogoModel();

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

    public List<OffertaEvento> getOffEven()
    {
        return accessoCatalogoModel.getOffEve();
    }
    public List<OffertaTrasporto> getOffTras()
    {
        return accessoCatalogoModel.getOffTras();
    }
    public List<OffertaPernotto> getOffPern()
    {
        return accessoCatalogoModel.getOffPern();
    }
}
