package Mvc.Control;

import Mvc.Model.entityPackage.User;
import Mvc.View.AccessoCatalogoView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Simone on 25/01/2016.
 */
public class AccessoCatalogoScoutControl extends AccessoCatalogoControl{


    private AccessoCatalogoScoutControl(User myUser)
    {
        super();
        user = myUser;

        addLog(null,"login");

    }


    public static AccessoCatalogoControl getInstance(User user)
    {
        if(instance==null)
            instance = new AccessoCatalogoScoutControl(user);

        return instance;
    }


    public void inserimentoOfferte (ActionEvent event)  {
        inserimentoOfferteControl = InserimentoOfferteControl.getInstance(this);


        try {
            inserimentoOfferteControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
