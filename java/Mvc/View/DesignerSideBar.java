package Mvc.View;

import Mvc.Control.AccessoCatalogoControl;
import Mvc.Control.AccessoCatalogoDesignerControl;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * Created by Simone on 26/01/2016.
 */
public class DesignerSideBar extends SideBar {

    public DesignerSideBar(AccessoCatalogoControl accessoCatalogoControl)
    {
        super(accessoCatalogoControl);
        instance = this;
    }

    public void draw()
    {

        Button logout = new Button() ;
        logout.setText("Log out");
        logout.setFont(new Font(18));
        logout.setMaxWidth(Double.MAX_VALUE);
        logout.setOnAction(accessoCatalogoControl::logout);


        Button aggrega = new Button() ;
        aggrega.setText("Aggregazione Offerte");
        aggrega.setFont(new Font(18));
        aggrega.setMaxWidth(Double.MAX_VALUE);
        aggrega.setOnAction(((AccessoCatalogoDesignerControl)accessoCatalogoControl)::aggregazioneOfferte);


        Button costi = new Button();
        costi.setText("Aggiorna costi offerte");
        costi.setFont(new Font(18));
        costi.setMaxWidth(Double.MAX_VALUE);

        buttonBox.getChildren().addAll(logout,aggrega, costi);
    }

}
