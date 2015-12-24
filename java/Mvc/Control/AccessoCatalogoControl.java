package Mvc.Control;

import Mvc.Model.AccessoCatalogoModel;
import Mvc.View.AccessoCatalogoView;
import Patterns.CbMediator.CbColleague;
import Patterns.CbMediator.CbMediator;
import Patterns.CbMediator.CbMediatorImpl;
import Patterns.OffObserver.OffObserver;
import Patterns.PackObserver.PackObserver;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;
import entityPackage.Pacchetto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 07/12/2015.
 */
public class AccessoCatalogoControl extends Application implements OffObserver,CbColleague,PackObserver{

    String utente;
    private AccessoCatalogoView accessoCatalogoView;
    private InserimentoOfferteControl inserimentoOfferteControl;
    private AccessoCatalogoModel accessoCatalogoModel;
    private AggregazioneOfferteControl aggregazioneOfferteControl;
    private CbMediatorImpl mediator;
    private List<CheckBox> checkBoxes;
    private List<OffertaEvento> offertaEvento;
    private OffertaPernotto offertaPernotto;
    private OffertaTrasporto offertaTrasporto;



    public AccessoCatalogoControl(String user){

        utente = user;
        inserimentoOfferteControl = new InserimentoOfferteControl(this);
        accessoCatalogoModel = new AccessoCatalogoModel();

        mediator = new CbMediatorImpl();
        mediator.addColleague(this);

        offertaEvento = new ArrayList<OffertaEvento>();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        accessoCatalogoView = new AccessoCatalogoView(primaryStage,utente,this,mediator);
    }

    public void send(List<CheckBox> checkBoxes) {
        //Never Reached
    }

    public CbMediator getCbMediator() {return mediator;}

    public void receive(List<CheckBox> checkBoxes) {

       this.checkBoxes = checkBoxes;
    }



    @Override
    public void addOfferta (Object offerta) {

        accessoCatalogoView.addOff(offerta);
    }

    @Override
    public void addPack (Pacchetto pacchetto) {
        accessoCatalogoView.addPack(pacchetto);

    }



    public void inserimentoOfferte (ActionEvent event)  {

        try {
            inserimentoOfferteControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aggregazioneOfferte(ActionEvent event)
    {
        accessoCatalogoView.showCheckBox(false);
        accessoCatalogoView.showCheckBox(true);
    }


    public void radioTras(ActionEvent event)
    {

        RadioButton o = (RadioButton) event.getSource();
        offertaTrasporto = (OffertaTrasporto) (accessoCatalogoModel.findOff(o.getId(),"OffertaTrasporto"));
        accessoCatalogoView.setEffect(3,offertaTrasporto.getCittà());

    }

    public void radioPern(ActionEvent event)
    {

        RadioButton o = (RadioButton) event.getSource();
        offertaPernotto = (OffertaPernotto)  accessoCatalogoModel.findOff(o.getId(),"OffertaPernotto");

        accessoCatalogoView.setEffect(2,offertaPernotto.getCittà());
    }

    public void addPack(ActionEvent event){
        accessoCatalogoView.setEffect(0,null);
        for(CheckBox checkBox : checkBoxes)
            if(checkBox.isSelected()) {
                offertaEvento.add((OffertaEvento) (accessoCatalogoModel.findOff(checkBox.getId(),"OffertaEvento")));
            }

        accessoCatalogoView.showCheckBox(false);

        aggregazioneOfferteControl = new AggregazioneOfferteControl(offertaPernotto,offertaTrasporto,offertaEvento,this);

        try {
            aggregazioneOfferteControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public void visualizzaOfferteTrasporto (MouseEvent event)  {

        ImageView o = (ImageView) event.getSource();


        accessoCatalogoView.mostraOfferta(accessoCatalogoModel.findOff(o.getId(),"OffertaTrasporto"));


    }

    public void visualizzaOfferteEvento (MouseEvent event)  {

        ImageView o = (ImageView) event.getSource();



        accessoCatalogoView.mostraOfferta(accessoCatalogoModel.findOff(o.getId(),"OffertaEvento"));


    }

    public void visualizzaOffertePernotto (MouseEvent event)  {

        ImageView o = (ImageView) event.getSource();

        accessoCatalogoView.mostraOfferta(accessoCatalogoModel.findOff(o.getId(),"OffertaPernotto"));

    }

    public Object getAllOff(String offerta)
    {
        return accessoCatalogoModel.getOff(offerta);
    }

    public List<Pacchetto> getPack(){ return accessoCatalogoModel.getPack();}

    public void okListener (ActionEvent event)  {

        ((Node)(event.getSource())).getScene().getWindow().hide();

    }


    public void okButton(ActionEvent event)
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        accessoCatalogoView.setEffect(0,null);

        accessoCatalogoView.showCheckBox(false);

    }

}
