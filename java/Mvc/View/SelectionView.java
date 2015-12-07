package Mvc.View;

import Mvc.Control.SelectionControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Simone on 07/12/2015.
 */
public class SelectionView {

    public SelectionView(SelectionControl selectionControl) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SelectionView.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1,300,275));
        stage.show();

    }
}
