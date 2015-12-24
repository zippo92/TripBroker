package Patterns.PackObserver;

import entityPackage.Pacchetto;

/**
 * Created by Simone on 24/12/2015.
 */
public interface PackSubject {

    void addPackObserver (PackObserver observer);

    void removePackObserver(PackObserver observer);

    void notifyPackObserver (Pacchetto pacchetto);
}