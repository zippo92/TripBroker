import Mvc.Model.DBResourcesManager;
import Mvc.Model.UserDAOHibernate;
import entityPackage.User;

import java.util.List;

/**
 * Created by Alessandro on 09/12/2015.
 */
public class TestMain{

    public static void main(String[] args) {
        System.out.println("Intializing Hibernate...");

        // initialize Hibernate
        DBResourcesManager.initHibernate();

        System.out.println("Retrieving events...");

        // retrieve events
        List<User> selectedUser;

        System.out.println("\nUtenti si registrano...");
        // create and store events
        User u1 = new User("agent","Agente");
        //Mvc.Model.UserDAOHibernate.store(u1);
        User u2 = new User("scout","Scout");
        //Mvc.Model.UserDAOHibernate.store(u2);
        User u3 = new User("design","Designer");
        //Mvc.Model.UserDAOHibernate.store(u2);
        User u4 = new User("admin","Amministratore");
        //Mvc.Model.UserDAOHibernate.store(u2);
        /*
        System.out.println("Login di ..."+u1.getRuolo());
        // retrieve events again
        selectedUser = UserDAOHibernate.findSelectedUser(u1);
        if(selectedUser != null){
            System.out.println(u1.getUsID() + " è loggato");
        }
        else{
            System.out.println(u1.getUsID() + " deve registrarsi");
        }

        selectedUser = UserDAOHibernate.findSelectedUser(u2);
        if(selectedUser != null){
            System.out.println(u2.getUsID() + " è loggato");
        }
        else{
            System.out.println(u2.getUsID() + " deve registrarsi");
        }
        //System.out.println( selectedUser.get(0).getUsername() + "è loggato... e ci sono"+ selectedUser.size() + "John");

        DBResourcesManager.shutdown();
    */}
}
