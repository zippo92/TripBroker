package Mvc.Control;

import Mvc.Model.LogInModel;
import Mvc.View.LogInView;
import entityPackage.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInControl  extends Application {

    @FXML
    TextField nameField;
    @FXML
    TextField passwordField;
    private LogInView logInView;
    private LogInModel logInModel;
    private AccessoCatalogoControl accessoCatalogoControl;
    private String utente;

    @Override
    public void start(Stage primaryStage) throws IOException {
        logInView = new LogInView(primaryStage,this);
        logInModel = new LogInModel();
    }

    @FXML
        private void LogInAction(ActionEvent actionEvent) throws IOException {
        String user = nameField.getText();
        String pass = passwordField.getText();
        List<User> users = LogInModel.searchForName(user,pass);
        if(users != null){
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            accessoCatalogoControl = new AccessoCatalogoControl(users.get(0).getRuolo());

            try {
                accessoCatalogoControl.start(new Stage());
            }    catch (Exception e) {
                e.printStackTrace();
            }


        }
        else{
            System.out.println("Errore in LogIn");
//            logInView.showPopup("Utente sconosciuto");

        }


    }

    public void okListener (ActionEvent event)  {

        ((Node)(event.getSource())).getScene().getWindow().hide();

    }

}




