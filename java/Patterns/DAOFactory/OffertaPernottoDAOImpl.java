package Patterns.DAOFactory;

import DAO.OffertaDAO;
import DAO.OffertaPernottoDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public class OffertaPernottoDAOImpl extends DAOFactory {

    private static OffertaPernottoDAOImpl instance;


    private OffertaPernottoDAOImpl() {}

    public static OffertaPernottoDAOImpl getInstance()
    {
        if (instance == null)
        {
            instance = new OffertaPernottoDAOImpl();
        }

        return instance;
    }

    public  OffertaDAO getOffertaDAO(){

        return new OffertaPernottoDAO();
    }


}
