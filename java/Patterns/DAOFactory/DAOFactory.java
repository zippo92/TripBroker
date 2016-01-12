package Patterns.DAOFactory;

import DAO.LogDAO;
import DAO.OffertaDAO;
import DAO.PacchettoDAO;
import DAO.UserDAO;
import Mvc.TipoOfferta;

/**
 * Created by Simone on 23/12/2015.
 */
public  abstract class DAOFactory {

    public static DAOFactory getDAOFactory(TipoOfferta tipo) {

        switch (tipo) {
            case OffertaPernotto:
                return OffertaPernottoDAOImpl.getInstance();
            case OffertaTrasporto:
                return  OffertaTrasportoDAOImpl.getInstance();
            case OffertaEvento:
                return  OffertaEventoDAOImpl.getInstance();
            default:
                return null;
        }
    }

    public static PacchettoDAO getPacchettoDAO()
    {
        return PacchettoDAO.getInstance();
    }

    public static UserDAO getUserDAO()
    {
        return UserDAO.getInstance();
    }

    public static LogDAO getLogDAO()
    {
        return LogDAO.getInstance();
    }

    public abstract OffertaDAO getOffertaDAO();
}
