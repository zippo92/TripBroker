package Mvc.Model.DAO;

import Mvc.Model.DBResourcesManager;
import Mvc.Model.entityPackage.OffertaEvento;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Simone on 16/12/2015.
 */
public class OffertaEventoDAO extends OffertaDAO {

    @Override
    public  Object getList() {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaEvento";
        @SuppressWarnings("unchecked")
        List<OffertaEvento> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }

    @Override
    public Object findOff(String id) {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaEvento offertaEvento where offertaEvento.eveID = '"+id+"'";
        @SuppressWarnings("unchecked")
        List<OffertaEvento> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte.get(0);
        else
            return null;

    }

    @Override
    public Object findByCity(String city) {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaEvento offertaEvento where offertaEvento.città = '"+city+"'";
        @SuppressWarnings("unchecked")
        List<OffertaEvento> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }


    @Override
    public void modPrice(int eveID, int percent)
    {
        Session s = DBResourcesManager.getSession();
        int val;
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            OffertaEvento offertaEvento = (OffertaEvento)s.get(OffertaEvento.class,eveID);


            val = offertaEvento.getPrezzo();

            offertaEvento.setPrezzo(val + val*percent/100);
            s.update(offertaEvento);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }

    }

    public boolean checkToBuy(int id){
        Session s = DBResourcesManager.getSession();

        Transaction tx = null;
        try{
            tx = s.beginTransaction();


            Object res = (Object) s.get(OffertaEvento.class, id);

            OffertaEvento p = (OffertaEvento) res;

            if (!p.isToBuy()) {
                s.close();
                return false;
            }
            p.setToBuy(false);
            s.update(p);
            tx.commit();
            s.close();
            return true;

        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

}
