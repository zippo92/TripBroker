package Patterns.DAOFactory;

import DAO.OffertaDAO;
import DAO.OffertaEventoDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public class OffertaEventoDAOImpl extends DAOFactory {




    private static OffertaEventoDAOImpl instance;

    private OffertaEventoDAOImpl(){}

    public static OffertaEventoDAOImpl getInstance()
    {
        if (instance == null)
        {
            instance = new OffertaEventoDAOImpl();
        }

        return instance;
    }

    public OffertaDAO getOffertaDAO(){

        return new OffertaEventoDAO();
    }


}