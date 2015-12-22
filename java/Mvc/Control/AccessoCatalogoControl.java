package Mvc.Control;

import Mvc.Model.AccessoCatalogoModel;
import Mvc.View.AccessoCatalogoView;
import Patterns.CbMediator.CbColleague;
import Patterns.CbMediator.CbMediator;
import Patterns.CbMediator.CbMediatorImpl;
import Patterns.OffObserver.OffObserver;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;
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
public class AccessoCatalogoControl extends Application implements OffObserver,CbColleague{

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

    public void send(List<CheckBox> checkBoxes) {
        //Never Reached
    }

    public CbMediator getCbMediator() {return mediator;}

    public void receive(List<CheckBox> checkBoxes) {

       this.checkBoxes = checkBoxes;
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        accessoCatalogoView = new AccessoCatalogoView(primaryStage,utente,this,mediator);
    }



    @Override
    public void addOfferta (Object offerta) {

        accessoCatalogoView.addRow(offerta);
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
        accessoCatalogoView.showCheckBox(true);
    }


    public void radioTras(ActionEvent event)
    {

        RadioButton o = (RadioButton) event.getSource();
        offertaTrasporto = (accessoCatalogoModel.findTras(o.getId())).get(0);
        accessoCatalogoView.setEffect(3,offertaTrasporto.getCittà());

    }

    public void radioPern(ActionEvent event)
    {

        RadioButton o = (RadioButton) event.getSource();
        offertaPernotto = (accessoCatalogoModel.findPern(o.getId())).get(0);
        accessoCatalogoView.setEffect(2,offertaPernotto.getCittà());
    }

    public void addPack(ActionEvent event){
        accessoCatalogoView.setEffect(0,null);

        for(CheckBox checkBox : checkBoxes)
            if(checkBox.isSelected()) {
                offertaEvento.add((accessoCatalogoModel.findEven(checkBox.getId())).get(0));
            }
        accessoCatalogoView.showCheckBox(false);

        aggregazioneOfferteControl = new AggregazioneOfferteControl(offertaPernotto,offertaTrasporto,offertaEvento);

        try {
            aggregazioneOfferteControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public void visualizzaOfferteTrasporto (MouseEvent event)  {

        ImageView o = (ImageView) event.getSource();

        List<OffertaTrasporto> offerta = accessoCatalogoModel.findTras(o.getId());

        accessoCatalogoView.mostraOfferta(offerta.get(0));

    }

    public void visualizzaOfferteEvento (MouseEvent event)  {

        ImageView o = (ImageView) event.getSource();

        List<OffertaEvento> offerta = accessoCatalogoModel.findEven(o.getId());

        accessoCatalogoView.mostraOfferta(offerta.get(0));


    }

    public void visualizzaOffertePernotto (MouseEvent event)  {

        ImageView o = (ImageView) event.getSource();

        List<OffertaPernotto> offerta = accessoCatalogoModel.findPern(o.getId());

        accessoCatalogoView.mostraOfferta(offerta.get(0));

    }
    public List<OffertaEvento> getOffEven()
    {
        return accessoCatalogoModel.getOffEve();
    }
    public List<OffertaTrasporto> getOffTras()
    {
        return accessoCatalogoModel.getOffTras();
    }
    public List<OffertaPernotto> getOffPern()
    {
        return accessoCatalogoModel.getOffPern();
    }

    public void okListener (ActionEvent event)  {

        ((Node)(event.getSource())).getScene().getWindow().hide();

    }


}
