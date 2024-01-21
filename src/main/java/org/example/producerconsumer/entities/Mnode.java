package org.example.producerconsumer.entities;
public class Mnode  extends Thread{
    private int Mid;
    private String color; //product color
    private Qnode next;
    private Subject sub;
    private final int waitTime;
    private volatile boolean keepRunning = true;
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
        this.waitTime = (int)(Math.random() * 5000 + 1000);
        this.sub = new Subject();
        this.Mid = id;
    }
    public Mnode(int id, int waitTime){
        this.waitTime = waitTime;
        this.sub = new Subject();
        this.Mid = id;
    }
    public void setNext(Qnode next) {
        this.next = next;
    }
    @Override
    public void run() {
        while(keepRunning){
            if(this.color == null){
                continue;
            }
            if(!this.sub.isFinished()){
                try {
                    Thread.sleep(this.waitTime);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.next.getQueue().add(this.color);
                System.out.println(this);
                this.color = null;
                sub.setF(true);
            }
        }
    }
    public void stopThread() {
        keepRunning = false;
    }
    public boolean isKeepRunning() {
        return keepRunning;
    }
    public int getWaitTime() {
        return waitTime;
    }
    @Override
    public String toString() {
        return "Mnode{" +
                "id=" + (Mid+800) +
                "WaitTime=" + waitTime +
                ", color='" + color + '\'' +
                '}';
    }
}
