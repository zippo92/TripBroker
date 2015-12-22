package Patterns.CbMediator;

import javafx.scene.control.CheckBox;

import java.util.List;

/**
 * Created by Simone on 21/12/2015.
 */
public interface CbMediator {
    void send(List<CheckBox> checkBoxes, CbColleague cbColleague);

}
