package Patterns.OffObserver;

/**
 * Created by Simone on 18/12/2015.
 */
public interface OffSubject {

    void addOffObserver(OffObserver observer);

    void removeOffObserver(OffObserver observer);

    void notifyOffObserver(Object offerta);


    }
