package DAO;

import Mvc.Model.DBResourcesManager;
import entityPackage.OffertaPernotto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Simone on 16/12/2015.
 */
public class OffertaPernottoDAO extends OffertaDAO {


    @Override
    public Object getList() {
        Session s = DBResourcesManager.getSession();

        String query = "from OffertaPernotto";
        @SuppressWarnings("unchecked")
        List<OffertaPernotto> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte;
        else
            return null;

    }


    @Override
    public  Object findOff(String id) {
        Session s = DBResourcesManager.getSession();
        String query = "from OffertaPernotto offertaPernotto where offertaPernotto.perID = '"+id+"'";
        @SuppressWarnings("unchecked")
        List<OffertaPernotto> offerte = s.createQuery(query).list();
        if(offerte.size()>0)
            return offerte.get(0);
        else
            return null;

    }



    @Override
    public void modPrezzo(int perID,int percent)
    {
        Session s = DBResourcesManager.getSession();
        int val;
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            OffertaPernotto offertaPernotto = (OffertaPernotto) s.get(OffertaPernotto.class,perID);


            val = offertaPernotto.getPrezzo();

            offertaPernotto.setPrezzo(val + val*percent/100);
            s.update(offertaPernotto);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }

    }


}
