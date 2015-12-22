package DAO;

import Mvc.Model.DBResourcesManager;
import entityPackage.OffertaEvento;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Simone on 16/12/2015.
 */
public class OffertaEventoDAO extends OffertaDAO {

//    public static void store(OffertaEvento e) {
//        // start a session
//        Session s = DBResourcesManager.getSession();
//        // in the given session, start a transaction
//        s.beginTransaction();
//
//        // within the transaction, save the event
//        try {
//            s.save(e); //might throw exception
//        } catch(HibernateException ex) {
//        }
//
//        // commit the current transaction of the session
//        s.getTransaction().commit();
//
//        // close session
//        s.close();
//    }

    public static List<OffertaEvento> getList() {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaEvento";
        @SuppressWarnings("unchecked")
        List<OffertaEvento> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }

    public static List<OffertaEvento> find(String id) {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaEvento offertaEvento where offertaEvento.eveID = '"+id+"'";
        @SuppressWarnings("unchecked")
        List<OffertaEvento> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }

}
