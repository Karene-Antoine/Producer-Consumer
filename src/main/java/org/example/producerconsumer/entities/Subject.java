package org.example.producerconsumer.entities;
import java.util.ArrayList;
public class Subject implements ISubject{ //machine
    private ArrayList<IObserver> observers;
    private int color;
    private boolean isFinished;
    public Subject(){
        observers = new ArrayList<IObserver>();
    }
    public void register(IObserver newObserver) {
        observers.add(newObserver);
    }
    public void unregister(IObserver deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);
        observers.remove(observerIndex);
    }
    public void notifyObserver() {
        for(IObserver observer : observers){
            observer.update(isFinished);
        }
    }
    public void setF(boolean f){
        this.isFinished = f;
        notifyObserver();
    }
}

