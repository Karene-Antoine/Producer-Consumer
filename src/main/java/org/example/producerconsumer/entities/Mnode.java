package org.example.producerconsumer.entities;
import java.util.ArrayList;
public class Mnode {
    private int id;
    private String color; //product color
    private Qnode next;
    private Subject sub;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Qnode getNext() {
        return next;
    }

    public Subject getSub() {
        return sub;
    }

    public void setSub(Subject sub) {
        this.sub = sub;
    }

    public Mnode(int id){
        this.sub = new Subject();
        this.id = id;
    }
    public void setNext(Qnode next) {
        this.next = next;
    }
}
