package DAO;

import Mvc.Model.DBResourcesManager;
import entityPackage.OffertaTrasporto;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Simone on 16/12/2015.
 */
public class OffertaTrasportoDAO extends OffertaDAO {

    @Override
    public Object getList() {
        Session s = DBResourcesManager.getSession();

        String query = "from OffertaTrasporto";
        @SuppressWarnings("unchecked")
        List<OffertaTrasporto> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }

    @Override
    public  Object findOff(String id) {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaTrasporto offertaTrasporto where offertaTrasporto.trasID = '"+id+"'";
        @SuppressWarnings("unchecked")
        List<OffertaTrasporto> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte.get(0);
        else
            return null;

    }

}
