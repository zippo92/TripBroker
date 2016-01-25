package Patterns.DAOFactory;

import Mvc.Model.DAO.OffertaDAO;
import Mvc.Model.DAO.OffertaTrasportoDAO;

/**
 * Created by Simone on 23/12/2015.
 */
public class ConcreteFactoryOffertaTrasportoDAO extends DAOFactory {

    private static ConcreteFactoryOffertaTrasportoDAO instance;

    private ConcreteFactoryOffertaTrasportoDAO(){}

    public static ConcreteFactoryOffertaTrasportoDAO getInstance()
    {
        if (instance == null)
        {
            instance = new ConcreteFactoryOffertaTrasportoDAO();
        }

        return instance;
    }


    public OffertaDAO getOffertaDAO(){

        return new OffertaTrasportoDAO();
    }


}