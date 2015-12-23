package DAO;

import Mvc.Model.DBResourcesManager;
import entityPackage.Pacchetto;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Simone on 22/12/2015.
 */
public class PacchettoDAO {


    public static void store(Pacchetto e) {
        // start a session
        Session s = DBResourcesManager.getSession();
        // in the given session, start a transaction
        s.beginTransaction();

        // within the transaction, save the event
        try {
            s.save(e); //might throw exception
        } catch (HibernateException ex) {
        }

        // commit the current transaction of the session
        s.getTransaction().commit();

        // close session
        s.close();
    }

    public static List<Pacchetto> getList() {
        Session s = DBResourcesManager.getSession();
        String query = "from Pacchetto";
        @SuppressWarnings("unchecked")
        List<Pacchetto> pacchetto = s.createQuery(query).list();
        if(pacchetto.size()>0)
            return pacchetto;
        else
            return null;

    }

}
