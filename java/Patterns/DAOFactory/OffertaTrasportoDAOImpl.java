package Patterns.DAOFactory;

import DAO.OffertaDAO;
import DAO.OffertaTrasportoDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public class OffertaTrasportoDAOImpl extends DAOFactory {

    private static OffertaTrasportoDAOImpl instance;

    private OffertaTrasportoDAOImpl(){}

    public static OffertaTrasportoDAOImpl getInstance()
    {
        if (instance == null)
        {
            instance = new OffertaTrasportoDAOImpl();
        }

        return instance;
    }


    public OffertaDAO getOffertaDAO(){

        return new OffertaTrasportoDAO();
    }


}