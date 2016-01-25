package Patterns.DAOFactory;

import Mvc.Model.DAO.OffertaDAO;
import Mvc.Model.DAO.OffertaPernottoDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public class ConcreteFactoryOffertaPernottoDAO extends DAOFactory {

    private static ConcreteFactoryOffertaPernottoDAO instance;


    private ConcreteFactoryOffertaPernottoDAO() {}

    public static ConcreteFactoryOffertaPernottoDAO getInstance()
    {
        if (instance == null)
        {
            instance = new ConcreteFactoryOffertaPernottoDAO();
        }

        return instance;
    }

    public  OffertaDAO getOffertaDAO(){

        return new OffertaPernottoDAO();
    }


}
