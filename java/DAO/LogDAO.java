package DAO;

import Mvc.Model.DBResourcesManager;
import entityPackage.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Simone on 12/01/2016.
 */
public class LogDAO {

    private static LogDAO instance;


    private LogDAO()
    {}

    public static LogDAO getInstance()
    {
        if (instance == null)
        {
            instance = new LogDAO();
        }

        return instance;
    }


    public static void store(Log e) {
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

    public List<Log> getList() {
        Session s = DBResourcesManager.getSession();
        String query = "from Log";
        @SuppressWarnings("unchecked")
        List<Log> logs = s.createQuery(query).list();
        if(logs.size()>0)
            return logs;
        else
            return null;

    }

    public  Object findPerId(String id) {
        Session s = DBResourcesManager.getSession();
        String query = "from Log log where log.user.usID = '"+id+"'";
        @SuppressWarnings("unchecked")
        List<Log> logs = s.createQuery(query).list();
        if(logs.size()>0)
            return logs ;
        else
            return null;

    }

    public  Object findPerRole(String role) {
        Session s = DBResourcesManager.getSession();
        String query = "from Log log where log.user.ruolo = '"+role+"'";
        @SuppressWarnings("unchecked")
        List<Log> logs = s.createQuery(query).list();
        if(logs.size()>0)
            return logs ;
        else
            return null;

    }

}
