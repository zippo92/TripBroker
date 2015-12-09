package Mvc.View;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Simone on 07/12/2015.
 */
public class AccessoCatalogoView {

    BorderPane componentLayout;
    Scene scene;
    String dep;

    public AccessoCatalogoView(Stage primaryStage,String utente ) throws IOException {
        dep = utente;
        double percentageWidth = 0.98;
        double percentageHeight = 0.90;
        componentLayout = new BorderPane();
        componentLayout.setPadding(new Insets(20,0,20,20));

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();

        this.scene = new Scene(componentLayout, percentageWidth, percentageHeight);
        buildLeft();
        buildCenter();
        buildTop();


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



    private void buildCenter()
    {
        VBox exContainer = new VBox();
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-fill: black, white ;\n" +
                "-fx-background-insets: 1, 1, 1;");
        gridPane.setAlignment(Pos.TOP_CENTER);
        ColumnConstraints cc = new ColumnConstraints(100, 100, Double.MAX_VALUE,
                Priority.ALWAYS, HPos.CENTER, true);
        gridPane.getColumnConstraints().addAll(cc, cc);

        RowConstraints rc = new RowConstraints(20, 20, Double.MAX_VALUE,
                Priority.ALWAYS, VPos.CENTER, true);
        gridPane.getRowConstraints().addAll(rc, rc);


        Label off_label = new Label("Offerte");
        off_label.setFont(new Font("Arial",30));

        Label pack_label = new Label("Pacchetti");
        pack_label.setFont(new Font("Arial",30));



        gridPane.add(off_label,0,0);
        gridPane.add(pack_label,1,0);

        CheckBox pippo = new CheckBox("pippo");
        CheckBox baudo = new CheckBox("baudo");

        gridPane.add(pippo,0,2);
        gridPane.add(baudo,1,2);

        //Add GridPane to container.
        exContainer.getChildren().add(gridPane);
        exContainer.setStyle("-fx-background-color: lightsteelblue;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");
        componentLayout.setCenter(exContainer);



    }

    private void buildTop() {

        BorderPane topLayout = new BorderPane();

        Label divider = new Label();
        divider.setMaxHeight(1);
        divider.setMinHeight(1);
        divider.setMinWidth(Screen.getPrimary().getBounds().getWidth());
        topLayout.setBottom(divider);

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.TOP_CENTER);
        Label title = new Label();
        title.setText("TRIP BROKER");
        title.setFont(new Font("Comic Sans MS",50));

        //Add Title label to titleBox
        titleBox.getChildren().add(title);

        //Add Title Box (with label) to topLayout
        topLayout.setCenter(titleBox);
        topLayout.setStyle("-fx-background-color: lightgrey;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");


        //Add topLayout (a BorderPane Manager) to App Layout.
        componentLayout.setTop(topLayout);

    }


    private VBox setButtonBox(String dep)
    {
        VBox buttonBox = new VBox();

        buttonBox.setAlignment(Pos.TOP_CENTER);

        buttonBox.setSpacing(10);

//        Dipendente dipendente = Dipendente.valueOf(dep);

        switch (dep) {

            case "Scout":

                    //Add Click Event.
//        btnExample1.setOnAction(new EventHandler() e){
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Exampletton Clicked.");
//                layout.setCenter(example1());
//            }
//

                    Button offerte = new Button() ;
                offerte.setText("Inserimento offerte");
                offerte.setMaxWidth(Double.MAX_VALUE);

                Button contratti = new Button();
                contratti.setText("Gestione contratti");
                contratti.setMaxWidth(Double.MAX_VALUE);


                Button ricerca = new Button();
                ricerca.setText("Ricerca offerte");
                ricerca.setMaxWidth(Double.MAX_VALUE);

                buttonBox.getChildren().addAll(offerte, contratti, ricerca);
                break;

            case "Designer":

                Button aggrega = new Button() ;
                aggrega.setText("Aggregazione Offerte");
//                aggrega.setMaxWidth(Double.MAX_VALUE);

                Button costi = new Button();
                costi.setText("Aggiorna costi offerte");
//                costi.setMaxWidth(Double.MAX_VALUE);

                buttonBox.getChildren().addAll(aggrega, costi);
                break;

            case "Admin":
                Button aggiorna = new Button() ;
                aggiorna.setText("Aggiorna offerte con criterio");
                aggiorna.setMaxWidth(Double.MAX_VALUE);

                Button andamento = new Button();
                andamento.setText("Visualizza andamento economico");
                andamento.setMaxWidth(Double.MAX_VALUE);


                Button log = new Button();
                log.setText("Visualizza log");
                log.setMaxWidth(Double.MAX_VALUE);

                buttonBox.getChildren().addAll(aggiorna,andamento,log);

            case "agente":
                Button prenotazione = new Button() ;
                prenotazione.setText("Prenotazione viaggio");
                prenotazione.setMaxWidth(Double.MAX_VALUE);
                buttonBox.getChildren().addAll(prenotazione);

        }
        return buttonBox;
    }

    private void buildLeft() {

        BorderPane leftLayout = new BorderPane();

        leftLayout.setStyle("-fx-background-color: cadetblue;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");


        // Create a faux border-right effect using a Label.
        Label divider = new Label();
        divider.setPrefWidth(1);
        divider.setMinHeight(Screen.getPrimary().getBounds().getHeight());
        leftLayout.setRight(divider);

        //Place all demonstration buttons in a Vercial Box.





//


        //Add VBox to leftLayout.
        leftLayout.setCenter(setButtonBox(dep));

        //Place into Application.
        componentLayout.setLeft(leftLayout);

    }


    private enum Dipendente {
        Scout, Admin, Designer, orange
    }



}
