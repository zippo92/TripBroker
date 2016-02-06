package Mvc.Control;

import Mvc.Model.entityPackage.*;
import Mvc.TipoOfferta;
import Mvc.View.AccessoCatalogoView;
import Patterns.CbMediator.CbColleague;
import Patterns.CbMediator.CbMediator;
import Patterns.CbMediator.CbMediatorImpl;
import Patterns.DAOFactory.DAOFactory;
import Patterns.OffObserver.OffObserver;
import Patterns.PackObserver.PackObserver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 07/12/2015.
 */
public abstract class AccessoCatalogoControl extends Application implements OffObserver,CbColleague,PackObserver{

    protected User user;
    protected AccessoCatalogoView accessoCatalogoView;
    protected InserimentoOfferteControl inserimentoOfferteControl;
    protected ConfermaPacchettiControl confermaPacchettiControl;
    protected AggregazioneOfferteControl aggregazioneOfferteControl;
    protected CbMediator mediator;
    protected List<CheckBox> checkBoxes;
    protected List<OffertaEvento> offertaEvento;
    protected OffertaPernotto offertaPernotto;
    protected OffertaTrasporto offertaTrasporto;
    protected AggiornaCostiControl aggiornaCostiControl;
    protected VisualizzaLogControl visualizzaLogControl;
    protected Stage primaryStage;

    protected static AccessoCatalogoControl instance;


    /*
*
*  Instanzia i vari componenti e il mediator per le CheckBox
* */
    protected AccessoCatalogoControl(){




        mediator = new CbMediatorImpl();
        mediator.addColleague(this);

        offertaEvento = new ArrayList<OffertaEvento>();

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        accessoCatalogoView = new AccessoCatalogoView(primaryStage,user.getRuolo(),this,mediator);

        this.primaryStage = primaryStage;


    }

    public static AccessoCatalogoControl getInstance() {
        return instance;
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
    public void delPack (int id) {
        accessoCatalogoView.delPack(id);

    }


    @Override
    public void upPack(Pacchetto pacchetto)
    {
        accessoCatalogoView.upPack(pacchetto);
    }


    /*
    * Listener del pulsante "inserimento offerte" dello scout , fa partire inserimentoOfferteControl
    * */





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


        aggregazioneOfferteControl = new AggregazioneOfferteControl(offertaPernotto,offertaTrasporto,offertaEvento,this);


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

        this.addLog(null,"logout");

        instance = null;
        LogInControl logInControl = new LogInControl();

        try {
            logInControl.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logout()
    {
        instance = null;
    }

    public void addLog(Object object,String other)
    {

        if(user.getUsID().equals("test")) return ;

        Log log = new Log();

        log.setUser(user);

        String azione = null;



        if(object instanceof  Offerta)
            azione = "Lo scout '" + user.getNome() + "' ha inserito l'offerta " + ((Offerta) object).getNome();

        else if(object instanceof Pacchetto) {
            if (other.equals("aggiunto"))
                azione = "Il designer '" + user.getNome() + "' ha inserito il pacchetto " + ((Pacchetto) object).getNome();
            if (other.equals("conferma"))
                azione = "L'admin '" + user.getNome() + "' ha confermato il pacchetto " + ((Pacchetto) object).getNome();
            if (other.equals("rimuovi"))
                azione = "L'admin '" + user.getNome() + "' ha eliminato il pacchetto " + ((Pacchetto) object).getId();
        }
        else {
            if (other.equals("login"))
                azione = user.getRuolo() + " '" + user.getNome() + "' ha effettuato il log in";
            if (other.equals("logout"))
                azione = user.getRuolo() + " '" + user.getNome() + "' ha effettuato il log out";
        }
            log.setAzione(azione);


        log.setDate(Date.valueOf(LocalDate.now()));

        log.setTime(Time.valueOf(LocalTime.now()));

         DAOFactory.getLogDAO().store(log);


    }



}
