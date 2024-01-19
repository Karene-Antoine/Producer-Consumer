package org.example.producerconsumer.entities;
public interface ISubject {
    void register(IObserver o);
    void unregister(IObserver o);
    void notifyObserver();
}
