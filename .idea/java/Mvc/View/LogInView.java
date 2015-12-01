package Mvc.View;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Mvc.Model.*;
/**
 * Created by Simone on 01/12/2015.
 */
public class LogInView {
    String nome;
    LogInModel model;
    public LogInView()
    {

        System.out.println("ciao! questa è una LogInView");

    }


    public String LogIn()  {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader myInput = new BufferedReader(reader);
        System.out.println("inserisci nome");
        try {
            nome = myInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nome;
    }

    public String mostranome()
    {
        return nome;
    }
}
