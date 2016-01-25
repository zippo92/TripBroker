package Patterns.DAOFactory;

import Mvc.Model.DAO.OffertaDAO;
import Mvc.Model.DAO.OffertaEventoDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public class ConcreteFactoryOffertaEventoDAO extends DAOFactory {




    private static ConcreteFactoryOffertaEventoDAO instance;

    private ConcreteFactoryOffertaEventoDAO(){}

    public static ConcreteFactoryOffertaEventoDAO getInstance()
    {
        if (instance == null)
        {
            instance = new ConcreteFactoryOffertaEventoDAO();
        }

        return instance;
    }

    public OffertaDAO getOffertaDAO(){

        return new OffertaEventoDAO();
    }


}