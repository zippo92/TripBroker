package Mvc.Control;

import Mvc.Model.ConfermaPacchettiModel;
import Mvc.View.ConfermaPacchettiView;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import entityPackage.Pacchetto;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Simone on 27/12/2015.
 */
public class ConfermaPacchettiControl implements GpColleague{

    ConfermaPacchettiModel confermaPacchettiModel;
    ConfermaPacchettiView confermaPacchettiView;
    AccessoCatalogoControl accessoCatalogoControl;
    GpMediatorImpl gpMediator;
    GridPane confGp;


    public ConfermaPacchettiControl(AccessoCatalogoControl control,Stage primaryStage)
    {
        accessoCatalogoControl = control;
        confermaPacchettiModel = new ConfermaPacchettiModel();

        gpMediator = new GpMediatorImpl();

        gpMediator.addColleague(this);


        confermaPacchettiView = new ConfermaPacchettiView(this,gpMediator);
        confermaPacchettiView.adminPopup(primaryStage);




    }


    public void send(GridPane gp) {
        //Never Reached
    }

    public GpMediator getGpMediator() {return gpMediator;}

    public void receive(GridPane gp) {
        confGp = gp;
    }

    public List<Pacchetto> findNotApproved()
    {

        return confermaPacchettiModel.findNotApproved();
    }


    /*
   *
   *   Listener dell'immagine con la lente d'ingrandimento , serve a mostrare le informazioni complete relative
   *   a quell'offerta
   * */
    public void visualizzaOfferteTrasporto (MouseEvent event)  {

        accessoCatalogoControl.visualizzaOfferteTrasporto(event);


    }
    /*
        *
        *   Listener dell'immagine con la lente d'ingrandimento , serve a mostrare le informazioni complete relative
        *   a quell'offerta
        * */
    public void visualizzaOfferteEvento (MouseEvent event)  {

        accessoCatalogoControl.visualizzaOfferteEvento(event);


    }
    /*
        *
        *   Listener dell'immagine con la lente d'ingrandimento , serve a mostrare le informazioni complete relative
        *   a quell'offerta
        * */
    public void visualizzaOffertePernotto (MouseEvent event)  {

        accessoCatalogoControl.visualizzaOffertePernotto(event);

    }



    public void confirmPack(ActionEvent event)
    {
        Button o = (Button) event.getSource();

        confermaPacchettiView.delPack(o.getId());
    }


    public void modPack(ActionEvent event)
    {

        Button o = (Button) event.getSource();

        String id = o.getId();

        int i=0;
        int from =0;
        for(Node node : confGp.getChildren()){
            if(node instanceof Separator)
                from = i;

            if(node instanceof Button)
                if(node.getId().equals(id) && i%2==0) {



                    System.out.println(((TextField)confGp.getChildren().get(from+5)).getText());

                    System.out.println(((TextField)confGp.getChildren().get(from+6)).getText());

                    System.out.println(((TextField)confGp.getChildren().get(from+7)).getText());

                    break;
                }
            i++;
        }

    }


}
