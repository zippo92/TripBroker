package Mvc.Model;

/**
 * Created by Alessandro on 01/12/2015.
 */
public class LogInModelNew {

    public boolean CheckAuth(String req)
    {
        System.out.println(req);

        return req.equals("John");
    }
}
