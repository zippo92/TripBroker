package Mvc.Control;

import Mvc.Model.LogInModelNew;
import Mvc.View.LogInViewNew;

/**
 * Created by Alessandro on 01/12/2015.
 */
public class LogInControlNew {

    public static void main(String[] args){
        LogInControlNew licn = new LogInControlNew();
        licn.LogIn();
        LogInModelNew aa = new LogInModelNew();

    }

    private String ProcessAuth(LogInViewNew livn){
            return livn.get_auth();
    }

    public void LogIn(){
        LogInViewNew livn = new LogInViewNew();
        LogInModelNew limn = new LogInModelNew();

        if(limn.CheckAuth(this.ProcessAuth(livn))){
            System.out.println("Tutto bene");
            return;
        }
        System.out.println("Tutto male!");
    }
}
