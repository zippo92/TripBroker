package Mvc.View;

import Mvc.Control.AccessoCatalogoAdminControl;
import Mvc.Control.AccessoCatalogoControl;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * Created by Simone on 26/01/2016.
 */
public class AdminSideBar extends SideBar{

    public AdminSideBar(AccessoCatalogoControl accessoCatalogoControl)
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

        Button conferma = new Button() ;
        conferma.setText("Conferma pacchetti");
        conferma.setOnAction(((AccessoCatalogoAdminControl)accessoCatalogoControl)::confermaPacchetti);
        conferma.setFont(new Font(18));
        conferma.setMaxWidth(Double.MAX_VALUE);


        Button aggiorna = new Button() ;
        aggiorna.setText("Aggiorna offerte con criterio");
        aggiorna.setOnAction(((AccessoCatalogoAdminControl)accessoCatalogoControl)::aggiornaCosti);
        aggiorna.setFont(new Font(18));
        aggiorna.setMaxWidth(Double.MAX_VALUE);

        Button andamento = new Button();
        andamento.setText("Visualizza andamento economico");
        andamento.setFont(new Font(18));
        andamento.setMaxWidth(Double.MAX_VALUE);

        Button log = new Button();
        log.setText("Visualizza log");
        log.setFont(new Font(18));
        log.setMaxWidth(Double.MAX_VALUE);
        log.setOnAction(((AccessoCatalogoAdminControl)accessoCatalogoControl)::visualizzalog);

        buttonBox.getChildren().addAll(logout,conferma,aggiorna,andamento,log);
    }
}
