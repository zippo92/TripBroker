package Mvc.Control;

import Mvc.Model.entityPackage.User;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Created by Simone on 25/01/2016.
 */
public class AccessoCatalogoAdminControl extends AccessoCatalogoControl {

    private AccessoCatalogoAdminControl(User myUser)
    {
        super();
        user = myUser;

        addLog(null,"login");


    }


    public static AccessoCatalogoControl getInstance(User user)
    {
        if(instance==null)
            instance = new AccessoCatalogoAdminControl(user);

        return instance;
    }

    public void confermaPacchetti(ActionEvent event)
    {
        confermaPacchettiControl = new ConfermaPacchettiControl(this,primaryStage);

    }


    public void aggiornaCosti(ActionEvent event)
    {
        aggiornaCostiControl = AggiornaCostiControl.getInstance();

        try {
            aggiornaCostiControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void visualizzalog(ActionEvent event)
    {
        visualizzaLogControl = new VisualizzaLogControl();

        try {
            visualizzaLogControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}
