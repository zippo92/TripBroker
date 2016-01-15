package Mvc.Model;

import Mvc.Model.entityPackage.Pacchetto;
import Patterns.DAOFactory.DAOFactory;

import java.util.List;

/**
 * Created by Simone on 27/12/2015.
 */
public class ConfermaPacchettiModel {

    public List<Pacchetto> findNotApproved()
    {
        return DAOFactory.getPacchettoDAO().findNotApproved();

    }

    public void updatePack(int id,String nome,int prezzo,boolean stato)
    {
        DAOFactory.getPacchettoDAO().modPack(id,nome,prezzo,stato);

    }
}
