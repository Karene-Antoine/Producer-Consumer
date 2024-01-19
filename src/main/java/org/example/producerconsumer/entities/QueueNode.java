package org.example.producerconsumer.entities;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class QueueNode {
    int id;
    private Queue<String> queue;
    private ArrayList<MachineNode> next ;
    private Observer observer;
    public QueueNode(int id, ArrayList<MachineNode> next ){
        this.id = id;
        queue = new LinkedList<>();
    }
    public Queue<String> getQueue() {
        return queue;
    }
    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }
    public ArrayList<MachineNode> getNext() {
        return next;
    }
    public void setNext(ArrayList<MachineNode> next) {
        this.next = next;
    }
}
