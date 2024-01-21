package org.example.producerconsumer.services;
import org.example.producerconsumer.entities.Snapshot;
import java.util.ArrayList;
public class Caretaker {
    ArrayList<Snapshot> savedGB = new ArrayList<Snapshot>();
    public void addMemento(Snapshot m) {
        savedGB.add(m);
    }
    public Snapshot getMemento(int index) {
        return savedGB.get(index);
    }
}
