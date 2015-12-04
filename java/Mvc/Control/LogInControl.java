package Mvc.Control;

import Mvc.Model.LogInModel;
import Mvc.View.LogInView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInControl implements ActionListener {

        private  LogInView logInView;
        private LogInModel logInModel;





        public LogInControl() {

            logInView = new LogInView();
            logInView.addController(this);
            logInModel = new LogInModel();
        }

        boolean NamePresent(String nome)
        {
            boolean trovato = false;
            List<String> names = logInModel.getNames();

            Iterator itr= names.iterator();

            while(itr.hasNext() && !trovato)
            {
                if((itr.next()).equals(nome))
                    trovato = true;
            }

            return trovato;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String nome =logInView.getUsername();
            if(NamePresent(nome))
                JOptionPane.showMessageDialog(source,"ciao " + nome + " sei loggato");
            else
                JOptionPane.showMessageDialog(source,"ciao " + nome + " non sei loggato");

        }



    }





