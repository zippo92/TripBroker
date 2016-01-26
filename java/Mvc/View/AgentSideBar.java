package Mvc.View;

import Mvc.Control.AccessoCatalogoAgentControl;
import Mvc.Control.AccessoCatalogoControl;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * Created by Simone on 26/01/2016.
 */
public class AgentSideBar extends SideBar {

    public AgentSideBar(AccessoCatalogoControl accessoCatalogoControl)
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

        Button viaggioGruppo = new Button() ;
        viaggioGruppo.setText("Crea viaggio di gruppo");
        viaggioGruppo.setMaxWidth(Double.MAX_VALUE);
        viaggioGruppo.setFont(new Font(18));
        viaggioGruppo.setOnAction(((AccessoCatalogoAgentControl)accessoCatalogoControl)::creaViaggioGruppo);

        Button prenotazione = new Button() ;
        prenotazione.setText("Prenotazione viaggio");
        prenotazione.setMaxWidth(Double.MAX_VALUE);
        prenotazione.setFont(new Font(18));
        prenotazione.setOnAction(((AccessoCatalogoAgentControl)accessoCatalogoControl)::prenotaViaggioGruppo);

        Button acquisto = new Button() ;
        acquisto.setText("Acquisto viaggio");
        acquisto.setMaxWidth(Double.MAX_VALUE);
        acquisto.setFont(new Font(18));
        acquisto.setOnAction(((AccessoCatalogoAgentControl)accessoCatalogoControl)::acquistaViaggioGruppo);

        buttonBox.getChildren().addAll(logout,viaggioGruppo,prenotazione,acquisto);
    }
}
