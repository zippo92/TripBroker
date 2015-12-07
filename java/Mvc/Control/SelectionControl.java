package Mvc.Control;

import Mvc.View.SelectionView;

import java.io.IOException;

/**
 * Created by Simone on 07/12/2015.
 */
public class SelectionControl {

    private SelectionView selectionView;

    public SelectionControl() throws IOException {

        selectionView = new SelectionView(this);
    }

//    public void addBotton(String nome)
//    {
//        final Button temp = new Button(nome);
//        temp.setId(nome);
//        gridPane.add(temp,gridPane.getRowConstraints().size()+1,gridPane.getColumnConstraints().size());
//
//    }





}
