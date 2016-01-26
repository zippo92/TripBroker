package Mvc.Control;

import Mvc.Model.entityPackage.User;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Created by Simone on 25/01/2016.
 */
public class AccessoCatalogoAgentControl  extends AccessoCatalogoControl{



    private AccessoCatalogoAgentControl(User myuser)
    {
        super();
        user = myuser;

        addLog(null,"login");

    }

    public static AccessoCatalogoControl getInstance(User user)
    {
        if(instance==null)
            instance = new AccessoCatalogoAgentControl(user);

        return instance;
    }


    public void creaViaggioGruppo(ActionEvent event){
        ViaggioGruppoControl viaggioGruppoControl = ViaggioGruppoControl.getInstance();
        try {
            viaggioGruppoControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prenotaViaggioGruppo(ActionEvent event){
        PrenotaViaggioControl prenotaViaggioControl = PrenotaViaggioControl.getInstance();
        try {
            prenotaViaggioControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void acquistaViaggioGruppo(ActionEvent event){
        AcquistaViaggioControl acquistaViaggioControl = AcquistaViaggioControl.getInstance();
        try {
            acquistaViaggioControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
