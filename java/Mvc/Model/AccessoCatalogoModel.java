package Mvc.Model;

import DAO.OffertaEventoDAO;
import DAO.OffertaPernottoDAO;
import DAO.OffertaTrasportoDAO;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;

import java.util.List;

/**
 * Created by Simone on 17/12/2015.
 */
public class AccessoCatalogoModel {


    public AccessoCatalogoModel()
    {

    }


    public List<OffertaEvento> getOffEve()
    {
        return OffertaEventoDAO.getList();
    }

    public List<OffertaPernotto> getOffPern()
    {
        return OffertaPernottoDAO.getList();
    }
    public List<OffertaTrasporto> getOffTras()
    {
        return OffertaTrasportoDAO.getList();
    }

    public List<OffertaEvento> findEven(String id)
    {
        return OffertaEventoDAO.find(id);
    }
    public List<OffertaPernotto> findPern(String id)
    {
        return OffertaPernottoDAO.find(id);
    }
    public List<OffertaTrasporto> findTras(String id)
    {
        return OffertaTrasportoDAO.find(id);
    }


}
