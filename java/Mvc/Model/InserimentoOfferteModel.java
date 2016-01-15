package Mvc.Model;

import Mvc.Model.DAO.OffertaEventoDAO;
import Mvc.Model.DAO.OffertaPernottoDAO;
import Mvc.Model.DAO.OffertaTrasportoDAO;
import Mvc.Model.entityPackage.OffertaEvento;
import Mvc.Model.entityPackage.OffertaPernotto;
import Mvc.Model.entityPackage.OffertaTrasporto;

/**
 * Created by Simone on 16/12/2015.
 */
public class InserimentoOfferteModel {

/*
*  inserisce l'evento il trasporto il pernotto
* */

    public void InsertEvento(OffertaEvento offertaEvento){
        OffertaEventoDAO.store(offertaEvento);
    }

    public void InsertTrasporto(OffertaTrasporto offertaTrasporto){
        OffertaTrasportoDAO.store(offertaTrasporto);
    }

    public void InsertPernotto(OffertaPernotto offertaPernotto){
        OffertaPernottoDAO.store(offertaPernotto);
    }
}
