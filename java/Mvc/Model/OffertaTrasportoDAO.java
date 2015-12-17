package Mvc.Model;

import entityPackage.OffertaTrasporto;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Simone on 16/12/2015.
 */
public class OffertaTrasportoDAO {

    public static void store(OffertaTrasporto e) {
        // start a session
        Session s = DBResourcesManager.getSession();
        // in the given session, start a transaction
        s.beginTransaction();

        // within the transaction, save the event
        try {
            s.save(e); //might throw exception
        } catch(HibernateException ex) {
        }

        // commit the current transaction of the session
        s.getTransaction().commit();

        // close session
        s.close();
    }

    public static List<OffertaTrasporto> getList() {
        Session s = DBResourcesManager.getSession();

        String query = "from OffertaTrasporto";
        @SuppressWarnings("unchecked")
        List<OffertaTrasporto> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }
}
