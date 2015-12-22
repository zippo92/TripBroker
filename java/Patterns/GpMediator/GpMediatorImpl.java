package Patterns.GpMediator;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * Created by Simone on 17/12/2015.
 */
public class GpMediatorImpl implements GpMediator {
    private ArrayList<GpColleague> gpColleagues;
    public GpMediatorImpl() {
        gpColleagues = new ArrayList<GpColleague>();
    }
    public void addColleague(GpColleague gpColleague) {
        System.out.println("aggiunto collega");
        gpColleagues.add(gpColleague);
    }
    public void send(GridPane gp, GpColleague originator) {
        //let all other screens know that this screen has changed
        for(GpColleague gpColleague : gpColleagues) {
            //don't tell ourselves
            if(gpColleague != originator) {
                gpColleague.receive(gp);
            }
        }
    }
}