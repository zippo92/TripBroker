package Mvc.Control;

import Mvc.Model.LogInModel;
import Mvc.View.LogInView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private SelectionControl selectionControl;

    @Override
    public void start(Stage primaryStage) throws IOException {
        logInView = new LogInView(primaryStage);
        //   logInModel = new LogInModel();

    }

    boolean NamePresent(String nome) {
        boolean trovato = false;
        List<String> names = logInModel.getNames();

        Iterator itr = names.iterator();

        while (itr.hasNext() && !trovato) {
            if ((itr.next()).equals(nome))
                trovato = true;
        }

        return trovato;

    }


    @FXML
        private void LogInAction(ActionEvent actionEvent) throws IOException {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        selectionControl = new SelectionControl();
    }



}




