package Mvc.Model;

import Mvc.TipoOfferta;
import Patterns.DAOFactory.DAOFactory;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;

import java.util.List;

/**
 * Created by Simone on 29/12/2015.
 */
public class AggiornaCostiModel {



    public void aggiornaCosti(int val)
    {

       List<OffertaPernotto> offertaPernottos =(List<OffertaPernotto>) DAOFactory.getDAOFactory(TipoOfferta.OffertaPernotto).getOffertaDAO().getList();
        for(OffertaPernotto offerta : offertaPernottos)
         DAOFactory.getDAOFactory(TipoOfferta.OffertaPernotto).getOffertaDAO().modPrezzo(offerta.getPerID(),val);


        List<OffertaTrasporto> offertaTrasportos =(List<OffertaTrasporto>) DAOFactory.getDAOFactory(TipoOfferta.OffertaTrasporto).getOffertaDAO().getList();
        for(OffertaTrasporto offerta : offertaTrasportos)
            DAOFactory.getDAOFactory(TipoOfferta.OffertaTrasporto).getOffertaDAO().modPrezzo(offerta.getTrasID(),val);



        List<OffertaEvento> offertaEventos =(List<OffertaEvento>) DAOFactory.getDAOFactory(TipoOfferta.OffertaEvento).getOffertaDAO().getList();
        for(OffertaEvento offerta : offertaEventos)
            DAOFactory.getDAOFactory(TipoOfferta.OffertaEvento).getOffertaDAO().modPrezzo(offerta.getEveID(),val);
    }
}
