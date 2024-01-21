package org.example.producerconsumer.entities;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class Qnode extends Thread{
    private int Qid;
    private Queue<String> queue;  //queue pf products
    private ArrayList<Mnode> next ;
    private Observer observer;
    private volatile boolean keepRunning = true;
    private final Object lock = new Object();
    public Qnode(int Qid){
        this.Qid = Qid;
        queue = new LinkedList<>();
    }
    public Queue<String> getQueue() {
        return queue;
    }
    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }
    public ArrayList<Mnode> getNext() {
        return next;
    }
    public void setNext(ArrayList<Mnode> next) {
        this.next = new ArrayList<>(next);
    }
    public int getQId() {
        return Qid;
    }
    public void setQId(int id) {
        this.Qid = id;
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void initObserver() {
        if(next == null){
            return;
        }
        try{
            for(Mnode mnode: next){
                this.observer = new Observer(mnode.getSub());
            }
        }
        catch (Exception e){
            System.out.println("Error in initObserver");
        }
    }
    @Override
    public void run() {
        while(keepRunning){
            if(observer!=null && observer.isFinished()){
                for(Mnode mnode: next){
                    if(!queue.isEmpty()){
                        if(mnode.getSub().isFinished()){
                            System.out.print(this);
                            System.out.print(queue);
                            System.out.println();
                            mnode.setColor(queue.poll());
                            mnode.getSub().setF(false);
                        }
                    }
                }
            }
        }
    }
    public void stopThread() {
        keepRunning = false;
    }
    @Override
    public String toString() {
        return "Qnode{" +
                "id=" + (Qid+500) +
                '}';
    }
}

