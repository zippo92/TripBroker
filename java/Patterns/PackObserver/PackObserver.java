package Patterns.PackObserver;

import entityPackage.Pacchetto;

/**
 * Created by Simone on 24/12/2015.
 */
public interface PackObserver {

    void addPack(Pacchetto pacchetto);

    void upPack(Pacchetto pacchetto);

    void delPack(int id);


}
