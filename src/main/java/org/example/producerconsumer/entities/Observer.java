package org.example.producerconsumer.entities;
public class Observer implements IObserver { //queue
    private boolean isFinished;
    private ISubject subject;
    public Observer(ISubject subject){
        this.subject = subject;
        subject.register(this);
    }
    public void update(boolean status) {
        this.isFinished = status;
    }
    public boolean isFinished() {
        return isFinished;
    }
}
