package Patterns.CbMediator;

import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 21/12/2015.
 */
public class CbMediatorImpl implements CbMediator{

    private ArrayList<CbColleague> cbColleagues;
    public CbMediatorImpl() {
        cbColleagues = new ArrayList<CbColleague>();
    }
    public void addColleague(CbColleague cbColleague) {
        System.out.println("aggiunto collega");
        cbColleagues.add(cbColleague);
    }
    public void send(List<CheckBox> checkBoxes, CbColleague originator) {
        //let all other screens know that this screen has changed
        for(CbColleague cbColleague : cbColleagues) {
            //don't tell ourselves
            if(cbColleague != originator) {
                cbColleague.receive(checkBoxes);
            }
        }
    }
}
