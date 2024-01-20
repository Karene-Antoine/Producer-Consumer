package org.example.producerconsumer.services;
import org.example.producerconsumer.entities.Mnode;
import org.example.producerconsumer.entities.Qnode;
import java.util.ArrayList;
public class GraphBuilder {
    ArrayList<Qnode> queues = new ArrayList<>();
    ArrayList<Mnode> machines = new ArrayList<>();
    public void createMandQ (ArrayList<ArrayList<String>> graph){
        queues.clear();
        machines.clear();
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
//                       linkedMachines.add(machines.get(j));
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
//                queues.get(i).setNext(linkedMachines);
            }
            if(graph.get(i).contains("M")){
                for(int j=0; j< graph.size(); j++){
                    if(graph.get(i).get(j).equals("M")){
//                        machines.get(i).setNext(queues.get(j));
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
}


// [
//         [0,Q,0,0,0,0]
//        ,[0,0,M,0,0,0]
//        ,[0,0,0,Q,Q,0]
//        ,[0,0,0,0,0,M]
//        ,[0,0,0,0,0,M]
//        ,[0,0,0,0,0,0]
//        ]
