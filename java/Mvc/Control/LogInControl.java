package Mvc.Control;

import Mvc.Control.AccessoCatalogoControl;
import Mvc.Model.LogInModel;
import Mvc.View.LogInView;
import entityPackage.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInControl  extends Application {

    private LogInView logInView;
    private LogInModel logInModel;
    private AccessoCatalogoControl accessoCatalogoControl;
    private String utente;
    @FXML
    TextField nameField;

    @FXML
    TextField passwordField;

    @Override
    public void start(Stage primaryStage) throws IOException {
        logInView = new LogInView(primaryStage);
        logInModel = new LogInModel();
    }

    @FXML
        private void LogInAction(ActionEvent actionEvent) throws IOException {
        String user = nameField.getText();
        String pass = passwordField.getText();
        List<User> users = logInModel.searchForName(user,pass);
        if(users != null){
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            accessoCatalogoControl = new AccessoCatalogoControl(users.get(0).getRuolo());
        }
        else{
            System.out.println("Errore in LogIn");
        }

        try {
            accessoCatalogoControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}




