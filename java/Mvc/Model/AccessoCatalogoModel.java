package Mvc.Model;

import Mvc.TipoOfferta;
import Patterns.DAOFactory.DAOFactory;
import entityPackage.Pacchetto;

import java.util.List;

/**
 * Created by Simone on 17/12/2015.
 */
public class AccessoCatalogoModel {


    /*
    * restituisce tutte le offerte di quel tipo
    *
    * */
    public Object getOff(TipoOfferta tipo)
    {
        return DAOFactory.getDAOFactory(tipo).getOffertaDAO().getList();
    }

    /*
    * restituisce l'offerta di quel tipo con quel id
    *
    * */
    public Object findOff(String id,TipoOfferta tipo)
    {
        return DAOFactory.getDAOFactory(tipo).getOffertaDAO().findOff(id);
    }

    /*
    * restituisce tutti i pacchetti
    *
    * */
    public List<Pacchetto> getPack() {
        return DAOFactory.getPacchettoDAO().getList();
    }

}
