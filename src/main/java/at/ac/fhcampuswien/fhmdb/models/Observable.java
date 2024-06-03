package at.ac.fhcampuswien.fhmdb.models;

import java.util.List;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(List<Observer> observers);

}
