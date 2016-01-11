package Mvc.View;

import Mvc.Control.AggiornaCostiControl;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by Simone on 29/12/2015.
 */
public class AggiornaCostiView implements GpColleague{
    AggiornaCostiControl aggiornaCostiControl;
    GpMediatorImpl gpMediator;
    private Stage dialog;

    public AggiornaCostiView(Stage primaryStage, AggiornaCostiControl control, GpMediatorImpl gpMediator)
    {
        aggiornaCostiControl = control;
        dialog = new Stage();
        dialog.sizeToScene();
        dialog.initModality(Modality.WINDOW_MODAL);

        dialog.getIcons().add(new Image("icon.png"));


        dialog.initOwner(primaryStage);

        this.gpMediator = gpMediator;
        this.gpMediator.addColleague(this);


        GridPane gp = new GridPane();
        gp.setVgap(10);


        Label imp = new Label("Aumentare tutte le offerte del :");
        imp.setFont(new Font("Arial",20));

        Label percent = new Label("%");


//        TextField importo = new TextField("0");
//        importo.setEditable(false);
//        importo.setFocusTraversable(false);
//        importo.setPrefWidth(100);


        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);


        Spinner<Integer> importo = new Spinner<Integer>();
        importo.setValueFactory(svf);
        importo.setEditable(true);
        importo.setPrefWidth(80);
//        Button minus = new Button("-");
//        minus.setOnAction(aggiornaCostiControl::minus);
//
//        Button plus = new Button("+");
//        plus.setOnAction(aggiornaCostiControl::plus);


        gp.add(imp,0,0,3,1);
        gp.add(importo,0,1);
//        gp.add(minus,1,1);
//        gp.add(plus,2,1);

        Button ok = new Button("OK");
        ok.setOnAction(aggiornaCostiControl::aggiorna);

        gp.add(ok,0,2);


        double percentageWidth = 0.15;
        double percentageHeight = 0.15;



        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();

        Scene dialogScene = new Scene(gp, percentageWidth, percentageHeight);

        dialogScene.getStylesheets().add("JMetroLightTheme.css");


        dialog.setScene(dialogScene);
        dialog.show();

        this.send(gp);
    }

    public void send(GridPane gp) {
        gpMediator.send(gp,this);
    }
    public GpMediator getGpMediator() {return gpMediator;}

    public void receive(GridPane gp) {
        //Never Reched
    }

}
