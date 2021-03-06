package Patterns.DAOFactory;

import Mvc.Model.DAO.*;
import Mvc.TipoOfferta;

/**
 * Created by Simone on 23/12/2015.
 */
public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(TipoOfferta tipo) {

        switch (tipo) {
            case OffertaPernotto:
                return ConcreteFactoryOffertaPernottoDAO.getInstance();
            case OffertaTrasporto:
                return  ConcreteFactoryOffertaTrasportoDAO.getInstance();
            case OffertaEvento:
                return  ConcreteFactoryOffertaEventoDAO.getInstance();
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

    public static ViaggioGruppoDAO getViaggioGruppoDAO(){return ViaggioGruppoDAO.getInstance();}

    public abstract OffertaDAO getOffertaDAO();
}
