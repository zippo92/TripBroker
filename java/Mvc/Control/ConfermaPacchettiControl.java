package Mvc.Control;

import Mvc.Model.entityPackage.Pacchetto;
import Mvc.View.ConfermaPacchettiView;
import Patterns.DAOFactory.DAOFactory;
import Patterns.GpMediator.GpColleague;
import Patterns.GpMediator.GpMediator;
import Patterns.GpMediator.GpMediatorImpl;
import Patterns.PackObserver.PackObserver;
import Patterns.PackObserver.PackSubject;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 27/12/2015.
 */
public class ConfermaPacchettiControl implements GpColleague,PackSubject{

//    ConfermaPacchettiModel confermaPacchettiModel;
    ConfermaPacchettiView confermaPacchettiView;
    AccessoCatalogoControl accessoCatalogoControl;
    GpMediator gpMediator;
    GridPane confGp;
    private List<PackObserver> list = new ArrayList<PackObserver>();


    public ConfermaPacchettiControl(AccessoCatalogoControl control,Stage primaryStage)
    {
        accessoCatalogoControl = control;
//        confermaPacchettiModel = new ConfermaPacchettiModel();

        gpMediator = new GpMediatorImpl();

        gpMediator.addColleague(this);

        this.addPackObserver(accessoCatalogoControl);


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

        return DAOFactory.getPacchettoDAO().findNotApproved();
    }




    public void addPackObserver(PackObserver observer) {
        list.add( observer );
    }

    public void removePackObserver(PackObserver observer) {
        list.remove( observer );
    }

    public void notifyPackObserver(boolean delete,Pacchetto pacchetto) {


       if(!delete)
        for(PackObserver observer: list) {
            observer.upPack(pacchetto);
        }

        else
           for(PackObserver observer: list) {
               observer.delPack(pacchetto.getId());
           }

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


        this.updatePack(o.getId(),true);

        confermaPacchettiView.delPack(o.getId());

    }


    public void deletePack(ActionEvent event)
    {

        Button o = (Button) event.getSource();


        Pacchetto pacchetto = new Pacchetto();
        pacchetto.setId(Integer.parseInt(o.getId()));

        notifyPackObserver(true,pacchetto);

        confermaPacchettiView.delPack(o.getId());

        accessoCatalogoControl.addLog(pacchetto,"rimuovi");

        DAOFactory.getPacchettoDAO().delPack(Integer.parseInt(o.getId()));

    }

    public void updatePack(String id,boolean stato)
    {
        int i=0;
        int from =-1;
        int prezzo = 0;
        String nome = null;
        for(Node node : confGp.getChildren()){
            if(node instanceof Separator)
                from = i;

            if(node instanceof Button) {


                if (node.getId().equals(id) && i % 2 != 0) {

                    nome = ((TextField) confGp.getChildren().get(from + 5)).getText();

                    prezzo = Integer.parseInt(((TextField) confGp.getChildren().get(from + 7)).getText());

                    break;
                }
            }
            i++;
        }

//        confermaPacchettiModel.updatePack(Integer.parseInt(id),nome,prezzo,stato);

        DAOFactory.getPacchettoDAO().modPack(Integer.parseInt(id),nome,prezzo,stato);

        Pacchetto pack = new Pacchetto();

        pack.setNome(nome);
        pack.setPrezzo(prezzo);
        pack.setId(Integer.parseInt(id));
        pack.setStato(stato);

        accessoCatalogoControl.addLog(pack,"conferma");

        this.notifyPackObserver(false,pack);

    }


    public void okListener (ActionEvent event)  {

        ((Node)(event.getSource())).getScene().getWindow().hide();

    }
}
