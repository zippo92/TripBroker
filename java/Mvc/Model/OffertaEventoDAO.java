package Mvc.Model;

import entityPackage.OffertaEvento;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Created by Simone on 16/12/2015.
 */
public class OffertaEventoDAO {

    public static void store(OffertaEvento e) {
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
}
