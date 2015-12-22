package Patterns.CbMediator;

import javafx.scene.control.CheckBox;

import java.util.List;

/**
 * Created by Simone on 21/12/2015.
 */
public interface CbColleague {

    void send(List<CheckBox> checkBoxes);

    CbMediator getCbMediator();

    void receive(List<CheckBox> checkBoxes);
}
