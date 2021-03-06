package Mvc.Model.DAO;

import Mvc.Model.DBResourcesManager;
import Mvc.Model.entityPackage.OffertaEvento;
import Mvc.Model.entityPackage.OffertaTrasporto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Simone on 16/12/2015.
 */
public class OffertaTrasportoDAO extends OffertaDAO {

    @Override
    public Object getList() {
        Session s = DBResourcesManager.getSession();

        String query = "from OffertaTrasporto";
        @SuppressWarnings("unchecked")
        List<OffertaTrasporto> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }

    @Override
    public  Object findOff(String id) {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaTrasporto offertaTrasporto where offertaTrasporto.trasID = '"+id+"'";
        @SuppressWarnings("unchecked")
        List<OffertaTrasporto> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte.get(0);
        else
            return null;

    }

    @Override
    public Object findByCity(String city) {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaTrasporto offertaTrasporto where offertaTrasporto.città = '"+city+"'";
        @SuppressWarnings("unchecked")
        List<OffertaEvento> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }

    @Override
    public void modPrice(int trasID, int percent)
    {
        Session s = DBResourcesManager.getSession();
        int val;

        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            OffertaTrasporto offertaTrasporto = (OffertaTrasporto) s.get(OffertaTrasporto.class,trasID);

            val = offertaTrasporto.getPrezzo();

            offertaTrasporto.setPrezzo(val + val*percent/100);

            s.update(offertaTrasporto);
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


            Object res = (Object) s.get(OffertaTrasporto.class, id);

            OffertaTrasporto p = (OffertaTrasporto) res;

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
