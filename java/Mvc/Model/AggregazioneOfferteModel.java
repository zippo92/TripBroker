package Mvc.Model;

import DAO.PacchettoDAO;
import entityPackage.Pacchetto;

/**
 * Created by Simone on 22/12/2015.
 */
public class AggregazioneOfferteModel {


    public void creaPacchetto(Pacchetto pacchetto)
    {
        PacchettoDAO.store(pacchetto);

    }

}
