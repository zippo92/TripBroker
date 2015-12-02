package Mvc.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Alessandro on 01/12/2015.
 */
public class LogInViewNew {

    private String in_message;
    private String req_message;
    public LogInViewNew(){
        this.in_message = "Benvenuto";
        this.req_message = "Inserire credenziali";


    }

    public String get_auth(){
        System.out.println(this.in_message + " " + this.req_message);
        String req = null;
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader myInput = new BufferedReader(reader);
        try {
            req = myInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return req;
    }


}
