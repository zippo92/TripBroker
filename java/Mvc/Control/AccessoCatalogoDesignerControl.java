package Mvc.Control;

import Mvc.Model.entityPackage.OffertaEvento;
import Mvc.Model.entityPackage.User;
import javafx.event.ActionEvent;

import java.util.ArrayList;

/**
 * Created by Simone on 25/01/2016.
 */
public class AccessoCatalogoDesignerControl extends AccessoCatalogoControl {



    private AccessoCatalogoDesignerControl(User myUser)
    {
        super();
        user = myUser;

        addLog(null,"login");

    }


    public static AccessoCatalogoControl getInstance(User user)
    {
        if(instance==null) {
            instance = new AccessoCatalogoDesignerControl(user);
        }

        return instance;
    }

    /*
    * Listener del pulsante "Aggregazione Offerte" , mostra i radiobutton e le checkbox per selezionare quali
    *
    * offerte aggregare (per sicurezza prima elimina le checkbox e radiobutton se gia esistono )
    *
    * */
    public void aggregazioneOfferte(ActionEvent event)
    {
        offertaEvento = null;
        offertaEvento = new ArrayList<OffertaEvento>();
        offertaPernotto = null;
        offertaTrasporto = null ;
        accessoCatalogoView.showCheckBox(false);
        accessoCatalogoView.showCheckBox(true);
    }
}
