package Mvc.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entityPackage.User;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInModel {

    public LogInModel()
    {
        DBResourcesManager.initHibernate();

    }

    public List<User> searchForName(String name,String password){
        return UserDAOHibernate.findSelectedUser(name,password);
    }

    public void closeDBManager(){
        DBResourcesManager.shutdown();
    }

}
