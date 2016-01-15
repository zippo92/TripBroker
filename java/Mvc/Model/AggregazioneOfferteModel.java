package Mvc.Model;

import Mvc.Model.entityPackage.Pacchetto;
import Patterns.DAOFactory.DAOFactory;

/**
 * Created by Simone on 22/12/2015.
 */
public class AggregazioneOfferteModel {


    /* Salva il pacchetto nel db*/
    public void creaPacchetto(Pacchetto pacchetto)
    {
        DAOFactory.getPacchettoDAO().store(pacchetto);

    }

}
