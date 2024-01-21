package org.example.producerconsumer.services;

import org.example.producerconsumer.entities.Mnode;
import org.example.producerconsumer.entities.Qnode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulator {
    int productCount;
    public void createProductQueue(int productCount, GraphBuilder graphBuilder){
        this.productCount = productCount;
        Queue<String> products = new LinkedList<>();
        for(int i = 0; i < productCount; i++){
            int random = (int)(Math.random() * 100 + 1);
            products.add(String.valueOf(random));
        }
        graphBuilder.queues.get(0).setQueue(products);
    }
    public void simulate(GraphBuilder graphBuilder){
        graphBuilder.queues.get(0).getObserver().update(true);
        ArrayList<Mnode> mnode = graphBuilder.machines;
        ArrayList<Qnode> qnode = graphBuilder.queues;
        for(Mnode m: mnode){
            m.getSub().setF(true);
            m.start();
        }
        for(Qnode q: qnode){
            q.start();
        }
        boolean flag = true;
        ArrayList<Qnode> q = graphBuilder.queues;
        long sumP = 0;
        while(flag){
            for(Qnode qnode1: q){
                if(qnode1.getObserver() == null) {
                    sumP += qnode1.getQueue().size();
                }
            }
            if(sumP != this.productCount)
                sumP = 0;
            else
                flag = false;
        }
        for(Mnode m: mnode){
            m.stopThread();
            System.out.print(m + " ");
            System.out.print(m.isAlive());
            System.out.println();
        }
        for(Qnode queue: qnode){
            queue.stopThread();
            System.out.print(queue + " ");
            System.out.print(queue.isAlive());
            System.out.println();
        }
    }
    public HashMap<Integer,Queue<String>> getQueues(GraphBuilder graphBuilder){
        HashMap<Integer, Queue<String>> queuesMap = new HashMap<>();
        graphBuilder.queues.forEach(
                qnode -> {
                    queuesMap.put(qnode.getQId(),qnode.getQueue());
                }
        );
        return queuesMap;
    }
    public HashMap<Integer,String> getMachines(GraphBuilder graphBuilder){
        HashMap<Integer, String> machinesMap = new HashMap<>();
        graphBuilder.machines.forEach(
                mnode -> {
                    machinesMap.put(mnode.getMid(),mnode.getColor());
                }
        );
        return machinesMap;
    }
}
