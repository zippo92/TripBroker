package Patterns.DAOFactory;

import DAO.OffertaDAO;
import DAO.PacchettoDAO;
import DAO.UserDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public  abstract class DAOFactory {

    public static DAOFactory getDAOFactory(String tipo) {

        switch (tipo) {
            case "OffertaPernotto":
                return new OffertaPernottoDAOImpl();
            case "OffertaTrasporto":
                return new OffertaTrasportoDAOImpl();
            case "OffertaEvento":
                return new OffertaEventoDAOImpl();
            default:
                return null;
        }
    }

    public static PacchettoDAO getPacchettoDAO()
    {
        return new PacchettoDAO();
    }

    public static UserDAO getUserDAO()
    {
        return new UserDAO();
    }

    public abstract OffertaDAO getOffertaDAO();
}
