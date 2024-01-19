package org.example.producerconsumer.entities;
import java.util.LinkedList;
import java.util.Queue;
public class ObserverAfter implements IObserver {
    private boolean isFinished;
    Queue<Integer> queue;
    private ISubject subject;
    public ObserverAfter(ISubject subject){
        this.subject = subject;
        this.queue = new LinkedList<>();
        subject.register(this);
    }
    public void update(boolean status) {
        this.isFinished = status;
    }
    public boolean isFinished() {
        return isFinished;
    }
}
