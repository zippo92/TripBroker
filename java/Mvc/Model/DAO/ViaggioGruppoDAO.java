package Mvc.Model.DAO;

import Mvc.Model.DBResourcesManager;
import Mvc.Model.entityPackage.Pacchetto;
import Mvc.Model.entityPackage.ViaggioGruppo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alessandro on 15/01/2016.
 */
public class ViaggioGruppoDAO {

    private static ViaggioGruppoDAO instance;

    private ViaggioGruppoDAO(){}

    public static ViaggioGruppoDAO getInstance()
    {
        if (instance == null)
        {
            instance = new ViaggioGruppoDAO();
        }

        return instance;
    }


    public void store(ViaggioGruppo e) {
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

    private void modTrip(int id , int numreserved, ArrayList<String> ls)
    {
        Session s = DBResourcesManager.getSession();

        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            ViaggioGruppo viaggioGruppo = (ViaggioGruppo) s.get(ViaggioGruppo.class , id);

            viaggioGruppo.setNumreserved(viaggioGruppo.getNumreserved() + numreserved);
            if(ls != null){
                if(viaggioGruppo.getParticipants() == null)
                    viaggioGruppo.setParticipants(new ArrayList<String>());
                viaggioGruppo.concatParticipants(ls);
            }
            s.update(viaggioGruppo);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    public boolean addReserve(int id, int numreserved,ArrayList<String> ls){
        if(numreserved > 0){
            this.modTrip(id,numreserved,ls);
            return true;
        }
        else
            return false;
    }

    public  List<ViaggioGruppo> findBuyTrip() {
        Session s = DBResourcesManager.getSession();
        String query = "from ViaggioGruppo viaggioGruppo where viaggioGruppo.numreserved >= viaggioGruppo.minP and viaggioGruppo.numreserved <= viaggioGruppo.maxP";
        @SuppressWarnings("unchecked")
        List<ViaggioGruppo> trips = s.createQuery(query).list();
        if (trips.size() > 0)
            return trips;
        else
            return null;
    }

    public void delTrip(int id)
    {
        Session s = DBResourcesManager.getSession();

        Transaction tx = null;
        tx = s.beginTransaction();
        ViaggioGruppo viaggioGruppo = (ViaggioGruppo) s.get(ViaggioGruppo.class , id);

        s.delete(viaggioGruppo);
        tx.commit();

    }

    public boolean deleteReserve(int id, int numreserved,ArrayList<String> ls){
        if(numreserved > 0){
            int negativenumreserved = -numreserved;
            this.modTrip(id,negativenumreserved,ls);
            return true;
        }
        else
            return false;
    }

    public List<ViaggioGruppo> getList() {
        Session s = DBResourcesManager.getSession();
        String query = "from ViaggioGruppo ";
        @SuppressWarnings("unchecked")
        List<ViaggioGruppo> viaggoGruppo = s.createQuery(query).list();
        if(viaggoGruppo.size()>0)
            return viaggoGruppo;
        else
            return null;

    }


}
