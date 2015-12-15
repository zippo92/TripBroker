import Mvc.Model.DBResourcesManager;
import Mvc.Model.UserDAO;
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
        UserDAO.store(u1);
        User u2 = new User("scout","Scout");
        UserDAO.store(u2);
        User u3 = new User("design","Designer");
        UserDAO.store(u3);
        User u4 = new User("admin","Amministratore");
        UserDAO.store(u4);

//        System.out.println("Login di ..."+u1.getRuolo());
//        // retrieve events again
//        selectedUser = UserDAO.findSelectedUser(u1);
//        if(selectedUser != null){
//            System.out.println(u1.getUsID() + " è loggato");
//        }
//        else{
//            System.out.println(u1.getUsID() + " deve registrarsi");
//        }
//
//        selectedUser = UserDAO.findSelectedUser(u2);
//        if(selectedUser != null){
//            System.out.println(u2.getUsID() + " è loggato");
//        }
//        else{
//            System.out.println(u2.getUsID() + " deve registrarsi");
//        }
//        //System.out.println( selectedUser.get(0).getUsername() + "è loggato... e ci sono"+ selectedUser.size() + "John");

        DBResourcesManager.shutdown();
    }
}
