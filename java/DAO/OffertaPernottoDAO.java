package DAO;

import Mvc.Model.DBResourcesManager;
import entityPackage.OffertaPernotto;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Simone on 16/12/2015.
 */
public class OffertaPernottoDAO extends OffertaDAO {


    @Override
    public Object getList() {
        Session s = DBResourcesManager.getSession();

        String query = "from OffertaPernotto";
        @SuppressWarnings("unchecked")
        List<OffertaPernotto> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }


    @Override
    public  Object findOff(String id) {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaPernotto offertaPernotto where offertaPernotto.perID = '"+id+"'";
        @SuppressWarnings("unchecked")
        List<OffertaPernotto> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte.get(0);
        else
            return null;

    }

}
