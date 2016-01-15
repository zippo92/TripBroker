package Mvc.Model.DAO; /**
 * Created by Alessandro on 09/12/2015.
 */

import Mvc.Model.DBResourcesManager;
import Mvc.Model.entityPackage.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class UserDAO {

    private static UserDAO instance;

    private UserDAO(){}

    public static UserDAO getInstance()
    {
        if (instance == null)
        {
            instance = new UserDAO();
        }

        return instance;
    }



    public void store(User e) {
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

    public List<User> findSelectedUser(String name,String password) {
        Session s = DBResourcesManager.getSession();

//        String query = "from User user where user.usID = '" + name + "' and user.password = '" + password + "'";
        String query = "from User user where user.usID = '" + name + "'";
        System.out.println(query);
        @SuppressWarnings("unchecked")
        List<User> users = s.createQuery(query).list();
        if (users.size() > 1){
            System.out.println("Query ha ritornato più di un oggetto");
            return null;
        }
        else if(users.size() == 1){
            System.out.println("Query eseguita correttamente");
            return users;
        }
        else{
            return null;
        }
    }

}
