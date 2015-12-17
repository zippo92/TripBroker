package Mvc.Model;

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

}
