package Patterns.PackObserver;

import Mvc.Model.entityPackage.Pacchetto;

/**
 * Created by Simone on 24/12/2015.
 */
public interface PackSubject {

    void addPackObserver (PackObserver observer);

    void removePackObserver(PackObserver observer);

    void notifyPackObserver (boolean delete,Pacchetto pacchetto);
}
