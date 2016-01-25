package Mvc.Model.DAO;

import Mvc.Model.DBResourcesManager;
import Mvc.Model.entityPackage.Offerta;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Created by Simone on 18/12/2015.
 */
public abstract class OffertaDAO {


    public static void store(Offerta e) {
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

    public abstract Object getList();

    public abstract Object findOff(String id);

    public abstract void modPrice(int id, int prezzo);

}
