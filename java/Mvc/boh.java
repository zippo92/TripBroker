package Mvc;

import Mvc.Model.*;
import Mvc.View.*;
import Mvc.Control.*;
/**
 * Created by Simone on 01/12/2015.
 */
public class boh {

    public static void main(String[] args)
    {

        LogInModel logInModel;
        LogInView logInView;
        LogInControl logInControl;

        logInModel = new LogInModel();
        logInView = new LogInView();
        logInControl = new LogInControl();
        logInControl.addView(logInView);
        logInControl.addModel(logInModel);

        logInControl.initModel();

        System.out.println(logInModel.GetNome());

    }
}
