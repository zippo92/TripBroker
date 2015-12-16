import Mvc.Model.DBResourcesManager;
import entityPackage.OffertaTrasporto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Alessandro on 09/12/2015.
 */
public class TestMain{

    private static SessionFactory factory;
    public static void main(String[] args) {
        System.out.println("Intializing Hibernate...");

        // initialize Hibernate
        DBResourcesManager.initHibernate();

        System.out.println("Retrieving events...");

        // retrieve events
//        List<User> selectedUser;


//        Offerta offerta = new Offerta();
//
//        offerta.setNome("pippo");
        OffertaTrasporto offertaTrasporto = new OffertaTrasporto();
        offertaTrasporto.setCittà("Londra");
        offertaTrasporto.setNome("namo a Londra yeeee");



//        OffertaPernotto offertaPernotto = new OffertaPernotto();
//
//        offertaPernotto.setStelle(5);

//        OffertaPernotto offertaPernotto = new OffertaPernotto();
//        offertaPernotto.setNome("paperino");


        System.out.println("session ");
        Session s = DBResourcesManager.getSession();
        // in the given session, start a transaction
        s.beginTransaction();

        System.out.println("begin T");
        // within the transaction, save the event
        try {
            s.save(offertaTrasporto); //might throw exception
        } catch(HibernateException ex) {
            ex.printStackTrace();
            System.out.println("Ciao");
        }

        // commit the current transaction of the session
        s.getTransaction().commit();

        // close session
        s.close();


//         Session s = DBResourcesManager.getSession();
//        // in the given session, start a transaction
//        s.beginTransaction();
//
//        // within the transaction, save the event
//        try {
//            s.save(offfertaPernotto); //might throw exception
//        } catch(HibernateException ex) {
//        }
//
//        // commit the current transaction of the session
//        s.getTransaction().commit();
//
//        System.out.println("commit");
//        // close session
//        s.close();
//        System.out.println("\nUtenti si registrano...");
//        // create and store events
//        User u1 = new User("agent","Agente");
//        UserDAO.store(u1);
//        User u2 = new User("scout","Scout");
//        UserDAO.store(u2);
//        User u3 = new User("design","Designer");
//        UserDAO.store(u3);
//        User u4 = new User("admin","Amministratore");
//        UserDAO.store(u4);

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
        System.out.println("close");
        DBResourcesManager.shutdown();
    }
}
