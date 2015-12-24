package Patterns.DAOFactory;

import DAO.OffertaDAO;
import DAO.OffertaTrasportoDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public class OffertaTrasportoDAOImpl extends DAOFactory {

    public OffertaDAO getOffertaDAO(){

        return new OffertaTrasportoDAO();
    }


}