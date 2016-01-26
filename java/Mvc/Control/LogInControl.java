package Mvc.Control;

import Mvc.Dipendenti;
import Mvc.Model.DBResourcesManager;
import Mvc.Model.entityPackage.User;
import Mvc.View.LogInView;
import Patterns.DAOFactory.DAOFactory;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInControl  extends Application implements GpColleague{


    private LogInView logInView;
    private AccessoCatalogoControl accessoCatalogoControl;
    private String utente;
    private GpMediatorImpl mediator;
    private GridPane gridPane;

    @Override
    public void start(Stage primaryStage) throws IOException {

        DBResourcesManager.initHibernate();

        mediator = new GpMediatorImpl();

        mediator.addColleague(this);


        logInView = new LogInView(primaryStage,this,mediator);
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
    *  funzioni per ricevere la gridpane della view attraverso il mediator
    * */
    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return mediator;}

    public void receive(GridPane gp) {
        this.gridPane = gp;

    }



    public void logInAction(ActionEvent actionEvent) {


        String user = ((TextField) gridPane.getChildren().get(1)).getText();
        String psw = ((TextField) gridPane.getChildren().get(2)).getText();


        List<User> users = DAOFactory.getUserDAO().findSelectedUser(user,psw);
        if(users != null) {

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();


        try {

            Class<?> classType = Class.forName((Dipendenti.valueOf(users.get(0).getRuolo()).getAccessoCatalogoControl()));
            Method method = classType.getMethod("getInstance",User.class);
            accessoCatalogoControl = (AccessoCatalogoControl) method.invoke(classType.getClass(),users.get(0));

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  catch (NoSuchMethodException e) {
            e.printStackTrace();
        }




//        accessoCatalogoControl = classType.getClass().getInstance(users.get(0));


//        switch (Dipendenti.valueOf(users.get(0).getRuolo())) {
//
//            case Scout:
//                accessoCatalogoControl =  AccessoCatalogoScoutControl.getInstance(users.get(0));
//                break;
//
//            case Designer:
//                accessoCatalogoControl =  AccessoCatalogoDesignerControl.getInstance(users.get(0));
//                break;
//
//            case Admin:
//                accessoCatalogoControl =  AccessoCatalogoAdminControl.getInstance(users.get(0));
//                break;
//
//            case Agent:
//                accessoCatalogoControl =  AccessoCatalogoAgentControl.getInstance(users.get(0));
//                break;
//
//        }

        try {
            accessoCatalogoControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    else{
        System.out.println("Errore in LogIn");
        logInView.showPopup("Utente sconosciuto");

    }


    }

    public void okListener (ActionEvent event)  {

        ((Node)(event.getSource())).getScene().getWindow().hide();

    }

}




