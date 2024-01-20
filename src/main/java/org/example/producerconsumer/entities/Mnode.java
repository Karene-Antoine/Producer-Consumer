package org.example.producerconsumer.entities;
public class Mnode  extends Thread{
    private int Mid;
    private String color; //product color
    private Qnode next;
    private Subject sub;
    public int getMid() {
        return Mid;
    }
    public void setMid(int mid) {
        this.Mid = mid;
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
        this.Mid = id;
    }
    public void setNext(Qnode next) {
        this.next = next;
    }
    @Override
    public void run() {
        while(true){
            if(this.color == null){
                continue;
            }
            if(!this.sub.isFinished()){
                int waitTime = (int)(Math.random() * 5000 + 1000);
                try {
                    Thread.sleep(waitTime);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.next.getQueue().add(this.color);
                sub.setF(true);
                System.out.println(this);

            }
        }
    }
    @Override
    public String toString() {
        return "Mnode{" +
                "id=" + (Mid+800) +
                ", color='" + color + '\'' +
                '}';
    }
}
