package Mvc.Model;

import Patterns.DAOFactory.DAOFactory;
import entityPackage.Pacchetto;

import java.util.List;

/**
 * Created by Simone on 17/12/2015.
 */
public class AccessoCatalogoModel {



    public Object getOff(String tipo)
    {
        return DAOFactory.getDAOFactory(tipo).getOffertaDAO().getList();
    }

    public Object findOff(String id,String tipo)
    {
        return DAOFactory.getDAOFactory(tipo).getOffertaDAO().findOff(id);
    }

    public List<Pacchetto> getPack() {
        return DAOFactory.getPacchettoDAO().getList();
    }

}
