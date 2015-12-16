package Mvc.Model;

import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;

/**
 * Created by Simone on 16/12/2015.
 */
public class InserimentoOfferteModel {

    public InserimentoOfferteModel(){

    }

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
