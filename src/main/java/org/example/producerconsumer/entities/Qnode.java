package org.example.producerconsumer.entities;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class Qnode {
    private int id;
    private Queue<String> queue;  //queue pf products
    private ArrayList<Mnode> next ;
    private Observer observer;
    public Qnode(int id ){
        this.id = id;
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
        this.next = next;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
