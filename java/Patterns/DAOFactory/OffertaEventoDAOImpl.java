package Patterns.DAOFactory;

import DAO.OffertaDAO;
import DAO.OffertaEventoDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public class OffertaEventoDAOImpl extends DAOFactory {

    public OffertaDAO getOffertaDAO(){

        return new OffertaEventoDAO();
    }


}