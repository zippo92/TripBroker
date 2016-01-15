package Patterns.DAOFactory;

import Mvc.Model.DAO.LogDAO;
import Mvc.Model.DAO.OffertaDAO;
import Mvc.Model.DAO.PacchettoDAO;
import Mvc.Model.DAO.UserDAO;
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
