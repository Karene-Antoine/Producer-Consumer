package org.example.producerconsumer.services;
import org.example.producerconsumer.entities.Mnode;
import org.example.producerconsumer.entities.Qnode;
import org.example.producerconsumer.entities.Snapshot;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphBuilder {
    ArrayList<Qnode> queues = new ArrayList<>();
    ArrayList<Mnode> machines = new ArrayList<>();
    ArrayList<ArrayList<String>> graph;
    public void createMandQ (ArrayList<ArrayList<String>> graph){
        queues.clear();
        machines.clear();
        this.graph = graph;
        for(int i=0; i<graph.size(); i++){
            if(graph.get(i).contains("Q")){
                Qnode newQ = new Qnode(i);
                queues.add( newQ);
            }
            else if(graph.get(i).contains("M")){
                Mnode newM = new Mnode(i);
                machines.add( newM);
            }
            else if(!graph.get(i).contains("M") && !graph.get(i).contains("Q")){
                Qnode newQ = new Qnode(i);
                queues.add( newQ);
            }
        }
    }
    public void linkGraph(ArrayList<ArrayList<String>> graph){
        for(int i=0; i<graph.size(); i++){
            if(graph.get(i).contains("Q")){
                ArrayList<Mnode> linkedMachines = new ArrayList<>();
                for(int j=0; j< graph.size(); j++){
                    if(graph.get(i).get(j).equals("Q")){
                        int finalJ = j;
                        machines.forEach(
                                mnode -> {
                                    if(mnode.getMid()== finalJ){
                                        linkedMachines.add(mnode);
                                    }
                                }
                        );
                    }
                }
                int finalI = i;
                queues.forEach(
                        qnode -> {
                            if(qnode.getQId()== finalI){
                                qnode.setNext(linkedMachines);
                            }
                        }
                );
            }
            if(graph.get(i).contains("M")){
                for(int j=0; j< graph.size(); j++){
                    if(graph.get(i).get(j).equals("M")){
                        int finalJ = j;
                        int finalI = i;
                        machines.forEach(
                                mnode -> {
                                    if(mnode.getMid() == finalI){
                                        queues.forEach(
                                                qnode -> {
                                                    if(qnode.getQId()== finalJ){
                                                        mnode.setNext(qnode);
                                                    }
                                                }
                                        );
                                    }
                                }
                        );
                    }
                }
            }
        }
    }
    public void createObservers(){
        queues.forEach(Qnode::initObserver);
        queues.forEach(
                qnode -> {
                    System.out.println();
                    System.out.println(qnode.toString());
                }
        );
    }
    public Snapshot storeInSnapshot() {
        GraphBuilder graphBuilder = this.deepCopy();
        return new Snapshot(graphBuilder);
    }
    public GraphBuilder restoreFromSnapshot(Snapshot snapshot) {
        return snapshot.getSavedGraphBuilder();
    }
    public GraphBuilder deepCopy(){
        GraphBuilder graphBuilder = new GraphBuilder();
        graphBuilder.graph = this.graph;
        /*graphBuilder.queues = new ArrayList<>();
        graphBuilder.machines = new ArrayList<>();*/
        this.queues.forEach(
                qnode -> {
                    Qnode qnode1 = new Qnode(qnode.getQId());
                    qnode1.setQueue(new LinkedList<>(qnode.getQueue()));
                    qnode1.setObserver(qnode.getObserver());
                    graphBuilder.queues.add(qnode1);
                }
        );
        this.machines.forEach(
                mnode -> {
                    Mnode mnode1 = new Mnode(mnode.getMid(), mnode.getWaitTime());
                    mnode1.setColor(mnode.getColor());
                    mnode1.setSub(mnode.getSub());
                    graphBuilder.machines.add(mnode1);
                }
        );
        graphBuilder.linkGraph(this.graph);
        return graphBuilder;
    }
    @Override
    public String toString() {
        return "GraphBuilder{" +
                "Product=" + queues.get(0).getQueue() +
                "queues=" + queues +
                ", machines=" + machines +
                ", graph=" + graph +
                '}';
    }
}
// [
//         [0,Q,0,0,0,0]
//        ,[0,0,M,0,0,0]
//        ,[0,0,0,Q,Q,0]
//        ,[0,0,0,0,0,M]
//        ,[0,0,0,0,0,M]
//        ,[0,0,0,0,0,0]
//        ]
