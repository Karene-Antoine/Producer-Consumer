package org.example.producerconsumer.entities;
import java.util.ArrayList;
public class MachineNode {
    int id;
    private String color;
    private QueueNode next;
    private Subject sub;
    public MachineNode(){
        this.sub = new Subject();
    }
    public void setNext(int id, QueueNode next) {
        this.id = id;
        this.next = next;
    }

}
