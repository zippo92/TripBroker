package Mvc.View;

import Mvc.Control.ConfermaPacchettiControl;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import entityPackage.OffertaEvento;
import entityPackage.Pacchetto;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Simone on 27/12/2015.
 */
public class ConfermaPacchettiView implements GpColleague {

    private Image image;
    private ConfermaPacchettiControl confermaPacchettiControl;
    private GridPane gridPane;
    private GpMediatorImpl gpMediator;


    public ConfermaPacchettiView(ConfermaPacchettiControl control,GpMediatorImpl mediator)
    {

        confermaPacchettiControl = control;
        image = new Image("search.png");

        gpMediator = mediator;
        gpMediator.addColleague(this);

    }


    public void send(GridPane gp) {
        gpMediator.send(gp,this);
    }
    public GpMediator getGpMediator() {return gpMediator;}

    public void receive(GridPane gp) {
        //Never Reched
    }


    public void adminPopup(Stage stage)
    {


        List<Pacchetto> daApprovare = confermaPacchettiControl.findNotApproved();

        final Stage dialog = new Stage();
        dialog.sizeToScene();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);

        ScrollPane scrollPane = new ScrollPane();

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label text = new Label("Ci sono questi pacchetti da approvare:");
        text.setFont(new Font("Arial",20));
        gridPane.add(text,0,0,4,1);

        int i=1;
        int j;
        for(Pacchetto pack : daApprovare) {

            gridPane.add(new Label("Nome pacchetto"), 0, i);
            gridPane.add(new Label("Città"), 0, i+1);
            gridPane.add(new Label("Prezzo:"),0,i+2);
            gridPane.add(new Label("Offerte contenute:"), 0, i+3);

            TextField textField = new TextField(pack.getNome());
            textField.setEditable(false);
            textField.setFocusTraversable(false);
            gridPane.add(textField,1,i);

            textField = new TextField(pack.getOffertaPernotto().getCittà());
            textField.setEditable(false);
            textField.setFocusTraversable(false);
            gridPane.add(textField,1,i+1);

            textField = new TextField(Integer.toString(pack.getPrezzo()));
            textField.setEditable(false);
            textField.setFocusTraversable(false);
            gridPane.add(textField,1,i+2);

            gridPane.add(new Label(pack.getOffertaPernotto().getNome()),1,i+3);

            ImageView pernImg = new ImageView(image);
            pernImg.setId(Integer.toString( ( pack.getOffertaPernotto().getPerID())));
            pernImg.setOnMouseClicked(confermaPacchettiControl::visualizzaOffertePernotto);
            gridPane.add(pernImg,2,i+3);

            gridPane.add(new Label(pack.getOffertaTrasporto().getNome()),1,i+4);

            ImageView trasImg = new ImageView(image);
            trasImg.setId(Integer.toString( ( pack.getOffertaTrasporto().getTrasID())));
            trasImg.setOnMouseClicked(confermaPacchettiControl::visualizzaOfferteTrasporto);
            gridPane.add(trasImg,2,i+4);


            j=5;
            for(OffertaEvento evento : pack.getOffertaEvento()) {
                gridPane.add(new Label(evento.getNome()), 1, i + j);

                ImageView eveImg = new ImageView(image);
                eveImg.setId(Integer.toString( (evento.getEveID())));
                eveImg.setOnMouseClicked(confermaPacchettiControl::visualizzaOfferteEvento);
                gridPane.add(eveImg,2,i+j);



                j++;
            }

            Button conferma = new Button("Approva");
            conferma.setId(Integer.toString(pack.getId()));
            conferma.setOnAction(confermaPacchettiControl::confirmPack);



            Button modifica  = new Button("Modifica");
            modifica.setId(Integer.toString(pack.getId()));
            modifica.setOnAction(confermaPacchettiControl::modPack);


            gridPane.add(modifica,0,i+j+1);

            gridPane.add(conferma,1,i+j+1);

            gridPane.add(new Separator(),0,i+j+2,2,1);


            j+=3;
            i+=j;
        }
        scrollPane.setContent(gridPane);

        this.send(gridPane);
        double percentageWidth = 0.25;
        double percentageHeight = 0.30;

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageWidth *= screenSize.getWidth();
        percentageHeight *= screenSize.getHeight();

        Scene dialogScene = new Scene(scrollPane, percentageWidth, percentageHeight);
        dialog.setScene(dialogScene);
        dialog.show();


    }


    public void delPack(String id)
    {

        int i=0;
        int from =0;
        for(Node node : gridPane.getChildren()){
            if(node instanceof Separator)
                from = i;

            if(node instanceof Button)
                if(node.getId().equals(id) && i%2==0) {
                    gridPane.getChildren().remove(from +1, i + 3);
                    break;
                }
            i++;
        }

    }
}
