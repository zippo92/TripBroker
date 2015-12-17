package Mvc.Control;

import Mvc.Model.InserimentoOfferteModel;
import Mvc.View.InserimentoOfferteView;
import Patterns.GpMediatorImpl;
import Patterns.Interface.GpColleague;
import Patterns.Interface.GpMediator;
import entityPackage.OffertaEvento;
import entityPackage.OffertaPernotto;
import entityPackage.OffertaTrasporto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Simone on 14/12/2015.
 */
public class InserimentoOfferteControl extends Application implements GpColleague {

    private InserimentoOfferteView inserimentoOfferteView;
    private InserimentoOfferteModel inserimentoOfferteModel;

    private String nome ;
    private int prezzo;
    private  String città ;
    private String radioButton ;
    private String data ;
    private  String cittaP ;
    private   String trasp ;
    private   int durata ;
    private   int notti ;
    private   String tipo ;
    private   int stelle;
    private  String evento;
    private GpMediatorImpl mediator;

    private GridPane gpLeft;
    private GridPane gpRight;

    public InserimentoOfferteControl()
    {
         mediator = new GpMediatorImpl();
         mediator.addColleague(this);

    }

    public void send(GridPane gp) {
         mediator.send(gp,this);
    }

    public GpMediator getGpMediator() {return mediator;}

    public void receive(GridPane gp) {

        if(gp.getId().equals("Right"))
            gpRight = gp;
        else if(gp.getId().equals("Left"))
            gpLeft = gp;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        inserimentoOfferteView = new InserimentoOfferteView(primaryStage,this,mediator);
        inserimentoOfferteModel = new InserimentoOfferteModel();
    }


    public void radioListener (ActionEvent event)
    {
        RadioButton o = (RadioButton) event.getSource();

        if(o.getText().equals("Pernotto"))
            inserimentoOfferteView.buildRight(0);
        else if (o.getText().equals("Trasporto"))
            inserimentoOfferteView.buildRight(1);
        else if (o.getText().equals("Eventi"))
            inserimentoOfferteView.buildRight(2);
    }

    private OffertaTrasporto creaOffertaTrasporto()
    {
        OffertaTrasporto offerta = new OffertaTrasporto();

        offerta.setNome(this.nome);
        offerta.setCittà(this.città);
        offerta.setPrezzo(this.prezzo);
        offerta.setDataScadenza(this.data);
        offerta.setCittàPartenza(this.cittaP);
        offerta.setTipologia(this.trasp);
        offerta.setDurata(this.durata);
        return offerta;
    }

    private OffertaPernotto creaOffertaPernotto()
    {
        OffertaPernotto offerta = new OffertaPernotto();

        offerta.setNome(this.nome);
        offerta.setCittà(this.città);
        offerta.setPrezzo(this.prezzo);
        offerta.setDataScadenza(this.data);
        offerta.setNumeroNotti(this.notti);
        offerta.setStelle(this.stelle);
        offerta.setTipologia(tipo);
        return offerta;
    }
    private OffertaEvento creaOffertaEvento()
    {
        OffertaEvento offerta = new OffertaEvento();
        offerta.setNome(this.nome);
        offerta.setCittà(this.città);
        offerta.setPrezzo(this.prezzo);
        offerta.setDataScadenza(this.data);
        offerta.setTipologia(evento);
        return offerta;
    }
    public void inserimentoListener (ActionEvent event){

//        final SplitPane sp = (SplitPane) ((Node) (event.getSource())).getScene().getRoot();
//
//
//        final StackPane stackPane1 = (StackPane) sp.getItems().get(0);
//
//        final GridPane gp1 = (GridPane) stackPane1.getChildren().get(0);


        final GridPane gp1 = gpLeft;

        boolean red = false ;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ITALIAN);

        for (Node node : gp1.getChildren()) {
            if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 0) {
                if(!((TextField) node).getText().equals("")) {
                    nome = ((TextField) node).getText();
                    node.setStyle("");
                }
                else
                {
                    red = true;
                    node.setStyle("-fx-border-color: red");
                }            }
            if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 1) {
                if(!((TextField) node).getText().equals("")) {
                   try{
                    prezzo = Integer.parseInt(((TextField) node).getText());
                    node.setStyle("");
                   } catch (NumberFormatException e)
                   {
                       red = true;
                       node.setStyle("-fx-border-color: red");
                   }
                }
                else
                {
                    red = true;
                    node.setStyle("-fx-border-color: red");
                }
            }
            if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 2) {
                if(!((TextField) node).getText().equals("")) {
                    città = ((TextField) node).getText();
                    node.setStyle("");
                }
                else
                {
                    red = true;
                    node.setStyle("-fx-border-color: red");
                }
            }
            if (GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 3) {
                RadioButton chk = (RadioButton) ((RadioButton) node).getToggleGroup().getSelectedToggle();
                radioButton = chk.getText();
            }

            if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 6) {

                data = ((DatePicker) node).getValue().format(formatter);
            }
        }

//        System.out.println(nome + " " + prezzo + " " + città + " " + data);
//
//        StackPane stackPane2 = (StackPane) sp.getItems().get(1);
//
//        GridPane gp2 = (GridPane) stackPane2.getChildren().get(0);

        final GridPane gp2 = gpRight;

        switch (radioButton) {

            case "Trasporto":
                for (Node node : gp2.getChildren()) {
                    if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 0)
                        if(!((TextField) node).getText().equals("")) {
                            cittaP = ((TextField) node).getText();
                            node.setStyle("");
                        }
                        else
                        {
                            red = true;
                            node.setStyle("-fx-border-color: red");
                        }


                    if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 1)
                        if(!((ComboBox) node).getSelectionModel().isEmpty()) {
                            trasp = ((ComboBox) node).getSelectionModel().getSelectedItem().toString();
                            node.setStyle("");
                        }
                        else
                        {
                            red = true;
                            node.setStyle("-fx-border-color: red");
                        }

                    if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 2)
                        if(!((TextField) node).getText().equals("")) {
                            try{
                                durata = Integer.parseInt(((TextField) node).getText());
                                node.setStyle("");
                            } catch (NumberFormatException e)
                            {
                                red = true;
                                node.setStyle("-fx-border-color: red");
                            }
                        }
                        else
                        {
                            red = true;
                            node.setStyle("-fx-border-color: red");
                        }
                }
                if(!red) inserimentoOfferteModel.InsertTrasporto(this.creaOffertaTrasporto());
                break;
            case "Pernotto":
                for (Node node : gp2.getChildren()) {
                    if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 0)
                        if(!((TextField) node).getText().equals("")) {
                            try{
                            notti = Integer.parseInt(((TextField) node).getText());
                            node.setStyle("");
                            } catch (NumberFormatException e)
                            {
                                red=true;
                                node.setStyle("-fx-border-color: red");
                            }
                        }
                        else
                        {
                            red = true;
                            node.setStyle("-fx-border-color: red");
                        }


                    if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 1)
                        if(!((ComboBox) node).getSelectionModel().isEmpty()) {
                            tipo = ((ComboBox) node).getSelectionModel().getSelectedItem().toString();
                            node.setStyle("");
                        }
                        else
                        {
                            red = true;
                            node.setStyle("-fx-border-color: red");
                        }

                    if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 2)
                        if(!((ComboBox) node).getSelectionModel().isEmpty()) {
                            node.setStyle("");
                            stelle = ((ComboBox) node).getSelectionModel().getSelectedIndex() + 1;
                        }
                         else
                        {
                            red = true;
                            node.setStyle("-fx-border-color: red");
                        }
                }
               if(!red) inserimentoOfferteModel.InsertPernotto(this.creaOffertaPernotto());
                break;

            case "Eventi":
                for (Node node : gp2.getChildren()) {
                    if (GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 0)
                        if(!((ComboBox) node).getSelectionModel().isEmpty()) {
                            evento = ((ComboBox) node).getSelectionModel().getSelectedItem().toString();
                            node.setStyle("");
                        }
                        else
                        {
                            red = true;
                            node.setStyle("-fx-border-color: red");
                        }

                }
                if(!red) inserimentoOfferteModel.InsertEvento(this.creaOffertaEvento());
                break;
        }
        if(!red)
            ((Node)(event.getSource())).getScene().getWindow().hide();

    }
}
