package Mvc.Control;

import Mvc.TipoOfferta;
import Mvc.View.AccessoCatalogoView;
import Patterns.CbMediator.CbColleague;
import Patterns.CbMediator.CbMediator;
import Patterns.CbMediator.CbMediatorImpl;
import Patterns.DAOFactory.DAOFactory;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 07/12/2015.
 */
public class AccessoCatalogoControl extends Application implements OffObserver,CbColleague,PackObserver{

    private static AccessoCatalogoControl instance ;
    String utente;
    private AccessoCatalogoView accessoCatalogoView;
    private InserimentoOfferteControl inserimentoOfferteControl;
    private ConfermaPacchettiControl confermaPacchettiControl;
//    private AccessoCatalogoModel accessoCatalogoModel;
    private AggregazioneOfferteControl aggregazioneOfferteControl;
    private CbMediatorImpl mediator;
    private List<CheckBox> checkBoxes;
    private List<OffertaEvento> offertaEvento;
    private OffertaPernotto offertaPernotto;
    private OffertaTrasporto offertaTrasporto;
    private AggiornaCostiControl aggiornaCostiControl;

    private Stage primaryStage;

/*
*
*  Instanzia i vari componenti e il mediator per le CheckBox
* */
    private AccessoCatalogoControl(String user){


        utente = user;
        inserimentoOfferteControl = InserimentoOfferteControl.getInstance(this);
//        accessoCatalogoModel = new AccessoCatalogoModel();

        mediator = new CbMediatorImpl();
        mediator.addColleague(this);

        offertaEvento = new ArrayList<OffertaEvento>();


    }


    public static AccessoCatalogoControl getInstance(String user)
    {
        if (instance == null)
        {
            instance = new AccessoCatalogoControl(user);
        }

        return instance;
    }




    @Override
    public void start(Stage primaryStage) throws Exception {
        accessoCatalogoView = new AccessoCatalogoView(primaryStage,utente,this,mediator);

        this.primaryStage = primaryStage;

        if(utente.equals("Admin")) {

            confermaPacchettiControl = new ConfermaPacchettiControl(this,primaryStage);
        }

    }



    /*
    * funzioni per il mediator delle checkbox, che permette al controllore di ricevere la lista delle checkbox
     * delle OfferteEvento utili al designer
    *
    * */
    public void send(List<CheckBox> checkBoxes) {
        //Never Reached
    }

    public CbMediator getCbMediator() {return mediator;}

    public void receive(List<CheckBox> checkBoxes) {

       this.checkBoxes = checkBoxes;
    }



    /*
    * Observer delle offerte
    * */
    @Override
    public void addOfferta (Object offerta) {

        accessoCatalogoView.addOff(offerta);
    }


    /*
    * Observer dei pacchetti
    * */
    @Override
    public void addPack (Pacchetto pacchetto) {
        accessoCatalogoView.addPack(pacchetto);

    }

    @Override
    public void upPack(Pacchetto pacchetto)
    {
        accessoCatalogoView.upPack(pacchetto);
    }


    /*
    * Listener del pulsante "inserimento offerte" dello scout , fa partire inserimentoOfferteControl
    * */
    public void inserimentoOfferte (ActionEvent event)  {

        try {
            inserimentoOfferteControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * Listener del pulsante "Aggregazione Offerte" , mostra i radiobutton e le checkbox per selezionare quali
    *
    * offerte aggregare (per sicurezza prima elimina le checkbox e radiobutton se gia esistono )
    *
    * */
    public void aggregazioneOfferte(ActionEvent event)
    {
        accessoCatalogoView.showCheckBox(false);
        accessoCatalogoView.showCheckBox(true);
    }


    /*
    * Listener del primo radiobutton, cioè relativo alle offerte Pernotto
    *
    * preleva l'offerta relativa,rileva la colonna relativa alle offerte Trasporto e oscura le altre due
    * */
    public void radioTras(ActionEvent event)
    {

        RadioButton o = (RadioButton) event.getSource();
//        offertaTrasporto = (OffertaTrasporto) (accessoCatalogoModel.findOff(o.getId(),TipoOfferta.OffertaTrasporto));
        offertaTrasporto = (OffertaTrasporto)DAOFactory.getDAOFactory(TipoOfferta.OffertaTrasporto).getOffertaDAO().findOff(o.getId());
        accessoCatalogoView.setEffect(3,offertaTrasporto.getCittà());

    }

   /*
    * Listener del secondo radiobutton, cioè relativo alle offerte Trasporto
    *
    * preleva l'offerta relativa, rileva la colonna relativa alle offerte Evento e oscura le altre due
    * */

    public void radioPern(ActionEvent event)
    {

        RadioButton o = (RadioButton) event.getSource();
//        offertaPernotto = (OffertaPernotto)  accessoCatalogoModel.findOff(o.getId(),TipoOfferta.OffertaPernotto);
        offertaPernotto = (OffertaPernotto) DAOFactory.getDAOFactory(TipoOfferta.OffertaPernotto).getOffertaDAO().findOff(o.getId());
        accessoCatalogoView.setEffect(2,offertaPernotto.getCittà());
    }


    /*
    *
    * Listener del pulsante "Aggiungi pacchetto" ,preleva le offerte relative alle checkbox selezionate
    *
    * e istanzia il AggregazioneOfferteControl che mostrerà il necessario per l'aggregazione delle offerte
    *
    * */
    public void addPack(ActionEvent event){
        accessoCatalogoView.setEffect(0,null);
        for(CheckBox checkBox : checkBoxes)
            if(checkBox.isSelected()) {
//                offertaEvento.add((OffertaEvento) (accessoCatalogoModel.findOff(checkBox.getId(),TipoOfferta.OffertaEvento)));
                  offertaEvento.add((OffertaEvento) DAOFactory.getDAOFactory(TipoOfferta.OffertaEvento).getOffertaDAO().findOff(checkBox.getId()));
            }

        accessoCatalogoView.showCheckBox(false);


        aggregazioneOfferteControl = AggregazioneOfferteControl.getInstance(offertaPernotto,offertaTrasporto,offertaEvento,this);


        try {
            aggregazioneOfferteControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /*
    *
    *   Listener dell'immagine con la lente d'ingrandimento , serve a mostrare le informazioni complete relative
    *   a quell'offerta
    * */
    public void visualizzaOfferteTrasporto (MouseEvent event)  {

        ImageView o = (ImageView) event.getSource();


//        accessoCatalogoView.mostraOfferta(accessoCatalogoModel.findOff(o.getId(),TipoOfferta.OffertaTrasporto));
          accessoCatalogoView.mostraOfferta(DAOFactory.getDAOFactory(TipoOfferta.OffertaTrasporto).getOffertaDAO().findOff(o.getId()));

    }
    /*
        *
        *   Listener dell'immagine con la lente d'ingrandimento , serve a mostrare le informazioni complete relative
        *   a quell'offerta
        * */
    public void visualizzaOfferteEvento (MouseEvent event)  {

        ImageView o = (ImageView) event.getSource();



//        accessoCatalogoView.mostraOfferta(accessoCatalogoModel.findOff(o.getId(),TipoOfferta.OffertaEvento));

        accessoCatalogoView.mostraOfferta(DAOFactory.getDAOFactory(TipoOfferta.OffertaEvento).getOffertaDAO().findOff(o.getId()));

    }
    /*
        *
        *   Listener dell'immagine con la lente d'ingrandimento , serve a mostrare le informazioni complete relative
        *   a quell'offerta
        * */
    public void visualizzaOffertePernotto (MouseEvent event)  {

        ImageView o = (ImageView) event.getSource();

//        accessoCatalogoView.mostraOfferta(accessoCatalogoModel.findOff(o.getId(),TipoOfferta.OffertaPernotto));
        accessoCatalogoView.mostraOfferta(DAOFactory.getDAOFactory(TipoOfferta.OffertaPernotto).getOffertaDAO().findOff(o.getId()));

    }

    /*
    * Richiama il model per ottenere tutte le offerte del tipo di offerta ,
    *
    * offerta può essere o "OffertaPernotto" o "OffertaTrasporto" o "OffertaEvento"
    * */
    public Object getAllOff(TipoOfferta offerta)
    {

//        return accessoCatalogoModel.getOff(offerta);
        return DAOFactory.getDAOFactory(offerta).getOffertaDAO().getList();
    }

    /*
    *
    * Richiama il model per ottenere tutti i pacchetti
    *
    * */
    public List<Pacchetto> getPack(){ return DAOFactory.getPacchettoDAO().getList();}



    public void aggiornaCosti(ActionEvent event)
    {
        aggiornaCostiControl = AggiornaCostiControl.getInstance();

        try {
            aggiornaCostiControl.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void confermaPacchetti(ActionEvent event)
    {
        confermaPacchettiControl = new ConfermaPacchettiControl(this,primaryStage);

    }



    public void okListener (ActionEvent event)  {

        ((Node)(event.getSource())).getScene().getWindow().hide();

    }




    public void okButton(ActionEvent event)
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        accessoCatalogoView.setEffect(0,null);

        accessoCatalogoView.showCheckBox(false);

    }


    public void logout(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();

        LogInControl logInControl = new LogInControl();

        try {
            logInControl.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
