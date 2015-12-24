package Mvc.Model;

import Patterns.DAOFactory.DAOFactory;
import entityPackage.Pacchetto;

/**
 * Created by Simone on 22/12/2015.
 */
public class AggregazioneOfferteModel {


    public void creaPacchetto(Pacchetto pacchetto)
    {
        DAOFactory.getPacchettoDAO().store(pacchetto);

    }

}
