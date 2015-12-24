package Patterns.DAOFactory;

import DAO.OffertaDAO;
import DAO.OffertaPernottoDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public class OffertaPernottoDAOImpl extends DAOFactory {



    public  OffertaDAO getOffertaDAO(){

        return new OffertaPernottoDAO();
    }


}
