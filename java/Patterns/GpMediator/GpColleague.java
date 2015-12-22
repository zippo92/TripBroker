package Patterns.GpMediator;

import javafx.scene.layout.GridPane;

/**
 * Created by Simone on 17/12/2015.
 */
public interface GpColleague {

        void send(GridPane gp);

    GpMediator getGpMediator();

       void receive(GridPane gp);

}
