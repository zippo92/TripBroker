package Mvc.Model;

import Patterns.DAOFactory.DAOFactory;
import entityPackage.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Simone on 01/12/2015.
 */

public class LogInModel extends Application {

    public LogInModel()
    {
        DBResourcesManager.initHibernate();

    }

    public static List<User> searchForName(String name,String password){
        return DAOFactory.getUserDAO().findSelectedUser(name,password);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void closeDBManager(){
        DBResourcesManager.shutdown();
    }

}
