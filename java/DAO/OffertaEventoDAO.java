package DAO;

import Mvc.Model.DBResourcesManager;
import entityPackage.OffertaEvento;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Simone on 16/12/2015.
 */
public class OffertaEventoDAO extends OffertaDAO {

    @Override
    public  Object getList() {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaEvento";
        @SuppressWarnings("unchecked")
        List<OffertaEvento> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }

    @Override
    public Object findOff(String id) {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaEvento offertaEvento where offertaEvento.eveID = '"+id+"'";
        @SuppressWarnings("unchecked")
        List<OffertaEvento> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte.get(0);
        else
            return null;

    }


}
