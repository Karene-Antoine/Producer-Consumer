package org.example.producerconsumer.services;

import org.example.producerconsumer.entities.Mnode;
import org.example.producerconsumer.entities.Qnode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Simulator {
    public void createProductQueue(int productCount, GraphBuilder graphBuilder){
        Queue<String> products = new LinkedList<>();
        for(int i = 0; i < productCount; i++){
            int random = (int)(Math.random() * 100 + 1);
            products.add(String.valueOf(random));
        }
        graphBuilder.queues.get(0).setQueue(products);
    }
    public void  simulate(GraphBuilder graphBuilder){
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
//        graphBuilder.queues.get(0).start();
    }
}
