package Mvc.View;

import Mvc.Control.AccessoCatalogoControl;
import Mvc.Control.AccessoCatalogoScoutControl;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * Created by Simone on 26/01/2016.
 */
public class ScoutSideBar extends SideBar {

    
    public ScoutSideBar(AccessoCatalogoControl accessoCatalogoControl)
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


        Button offerte = new Button() ;
        offerte.setText("Inserimento offerte");
        offerte.setFont(new Font(18));
        offerte.setMaxWidth(Double.MAX_VALUE);
        offerte.setOnAction(((AccessoCatalogoScoutControl)accessoCatalogoControl)::inserimentoOfferte);

        Button contratti = new Button();
        contratti.setText("Gestione contratti");
        contratti.setFont(new Font(18));
        contratti.setMaxWidth(Double.MAX_VALUE);


        Button ricerca = new Button();
        ricerca.setText("Ricerca offerte");
        ricerca.setFont(new Font(18));
        ricerca.setMaxWidth(Double.MAX_VALUE);
        buttonBox.getChildren().addAll(logout,offerte, contratti, ricerca);
        
    }

}
