package Mvc.Control;

import Mvc.Model.*;
import Mvc.View.*;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInControl {

            LogInModel model;
            LogInView view;

    public LogInControl()
    {
        System.out.println("SOno il control");
    }

    public void addModel(LogInModel m){
        System.out.println("Controller: adding model");
        this.model = m;
    }

    public void addView(LogInView v){
        System.out.println("Controller: adding view");
        this.view = v;
    }

    public void initModel(){
        view.LogIn();
        model.SetNome(view.mostranome());
    } //initModel()


}
