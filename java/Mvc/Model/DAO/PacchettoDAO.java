package Mvc.Model.DAO;

import Mvc.Model.DBResourcesManager;
import Mvc.Model.entityPackage.Pacchetto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Simone on 22/12/2015.
 */
public class PacchettoDAO {


    private static PacchettoDAO instance;

    private PacchettoDAO(){}

    public static PacchettoDAO getInstance()
    {
        if (instance == null)
        {
            instance = new PacchettoDAO();
        }

        return instance;
    }


    public void store(Pacchetto e) {
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

    public  List<Pacchetto> getList() {
        Session s = DBResourcesManager.getSession();
        String query = "from Pacchetto";
        @SuppressWarnings("unchecked")
        List<Pacchetto> pacchetto = s.createQuery(query).list();
        if(pacchetto.size()>0)
            return pacchetto;
        else
            return null;

    }

    public  List<Pacchetto> findNotApproved() {
        Session s = DBResourcesManager.getSession();
        String query = "from Pacchetto pacchetto where pacchetto.stato=false";
        @SuppressWarnings("unchecked")
        List<Pacchetto> packs = s.createQuery(query).list();
        if (packs.size() > 0)
            return packs;
        else
            return null;
    }


     public void modPack(int id , String nome, int prezzo,boolean stato)
    {
        Session s = DBResourcesManager.getSession();

        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            Pacchetto pacchetto = (Pacchetto)s.get(Pacchetto.class , id);

            pacchetto.setNome(nome);
            pacchetto.setPrezzo(prezzo);
            pacchetto.setStato(stato);

            s.update(pacchetto);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }

    }

    public void delPack(int id)
    {
        Session s = DBResourcesManager.getSession();

        Transaction tx = null;
        tx = s.beginTransaction();
        Pacchetto pacchetto = (Pacchetto)s.get(Pacchetto.class , id);

        s.delete(pacchetto);
        tx.commit();

    }

}
