package Mvc.View;

import Mvc.Control.AccessoCatalogoControl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Simone on 07/12/2015.
 */
public class AccessoCatalogoView {

    private BorderPane componentLayout;
    private Scene scene;
    private String dep;
    private int Dim_Butt=18;
    private AccessoCatalogoControl accessoCatalogoControl;
    private TabPane tabPane;
    private SplitPane splitOfferte;
    private StackPane of1;
    private StackPane of2;
    private StackPane of3;
    private StackPane pacPane;


    public AccessoCatalogoView(Stage primaryStage, String utente, AccessoCatalogoControl control) throws IOException {
        dep = utente;
        accessoCatalogoControl = control;
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


    private SplitPane buildOfferte(){
        SplitPane sp = new SplitPane();

        Label label1 = new Label("label 1");
        Label label2 = new Label("label 2");
        Label label3 = new Label("label 3");
        Label label4 = new Label("label 4");



        of1 = new StackPane();
        of1.getChildren().add(label1);

        of2 = new StackPane();
        of2.getChildren().add(label2);

        of3 = new StackPane();
        of3.getChildren().add(label3);

        sp.setDividerPositions(0.3f, 0.6f, 0.9f);
        sp.getItems().addAll(of1,of2,of3);


        return sp;
    }







    private StackPane buildPacchetti(){
        StackPane sp = new StackPane();
        Label label1 = new Label("label gdsgsdg");
        sp.getChildren().add(label1);



        return sp;
    }


    private void buildCenter()
    {

        tabPane = new TabPane();
//        tabPane.setStyle("-fx-background-color: lightsteelblue;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1;");
        Tab tabA = new Tab();
        tabA.setText("Offerte");
        tabA.setClosable(false);
        splitOfferte = buildOfferte();
        tabA.setContent(splitOfferte);
        tabPane.getTabs().add(tabA);

        Tab tabB = new Tab();
        tabB.setText("Pacchetti");
        tabB.setClosable(false);
        pacPane = buildPacchetti();
        tabB.setContent(pacPane);
        tabPane.getTabs().add(tabB);



        componentLayout.setCenter(tabPane);



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
        title.setFont(new Font("Segoe Print",50));

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
                offerte.setFont(new Font(Dim_Butt));
                offerte.setMaxWidth(Double.MAX_VALUE);
                offerte.setOnAction(accessoCatalogoControl::inserimentoOfferte);




                Button contratti = new Button();
                contratti.setText("Gestione contratti");
                contratti.setFont(new Font(Dim_Butt));
                contratti.setMaxWidth(Double.MAX_VALUE);


                Button ricerca = new Button();
                ricerca.setText("Ricerca offerte");
                ricerca.setFont(new Font(Dim_Butt));
                ricerca.setMaxWidth(Double.MAX_VALUE);
                buttonBox.getChildren().addAll(offerte, contratti, ricerca);
                break;

            case "Designer":

                Button aggrega = new Button() ;
                aggrega.setText("Aggregazione Offerte");
                aggrega.setFont(new Font(Dim_Butt));
                aggrega.setMaxWidth(Double.MAX_VALUE);


                Button costi = new Button();
                costi.setText("Aggiorna costi offerte");
                costi.setFont(new Font(Dim_Butt));
                costi.setMaxWidth(Double.MAX_VALUE);



                buttonBox.getChildren().addAll(aggrega, costi);
                break;

            case "Admin":
                Button aggiorna = new Button() ;
                aggiorna.setText("Aggiorna offerte con criterio");
                aggiorna.setFont(new Font(Dim_Butt));
                aggiorna.setMaxWidth(Double.MAX_VALUE);

                Button andamento = new Button();
                andamento.setText("Visualizza andamento economico");
                andamento.setFont(new Font(Dim_Butt));
                andamento.setMaxWidth(Double.MAX_VALUE);



                Button log = new Button();
                log.setText("Visualizza log");
                log.setFont(new Font(Dim_Butt));
                log.setMaxWidth(Double.MAX_VALUE);


                buttonBox.getChildren().addAll(aggiorna,andamento,log);

            case "Agente":
                Button prenotazione = new Button() ;
                prenotazione.setText("Prenotazione viaggio");
                prenotazione.setMaxWidth(Double.MAX_VALUE);
                prenotazione.setFont(new Font(20));
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


}
