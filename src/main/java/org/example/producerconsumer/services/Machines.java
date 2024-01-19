package org.example.producerconsumer.services;
import org.example.producerconsumer.entities.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Machines {
    public void createMachineAndQueue (ArrayList<ArrayList<String>> graph){
        ArrayList<QueueNode> queues = new ArrayList<>();
        HashMap<Integer,MachineNode> machines = new HashMap<>();
        for(int i=0; i<graph.size(); i++){
                if(graph.get(i).contains("Q")) {
                    ArrayList<MachineNode> next = new ArrayList<>();
                    for(int j=0; j<graph.size(); j++) {
                        if(graph.get(i).get(j).equals("Q")){
                            boolean alreadyExists = false;
                            int k;
                            for(k=0; k<i; k++){
                                if(graph.get(k).get(j).equals("Q")){
                                    alreadyExists = true;
                                    break;
                                }
                            }
                            if(!alreadyExists){
                                MachineNode newMachine = new MachineNode();
                                machines.put(i,newMachine);
                                next.add(j,newMachine);
                            }
                            else{
                                next.add(j,queues.get(k).getNext().get(j));
                            }
                        }
                    }
                    QueueNode newQueue = new QueueNode(i, next);
                    queues.add(i,newQueue);
                }
        }
        for(int i=0; i<graph.size(); i++) {
            int k=0;
            if(graph.get(i).contains("M")){
                for(int j=0; j<graph.size(); j++) {
                    if(graph.get(i).get(j).equals("M")){
//                        queues.get(j).getNext().get(j).setNext();
                        queues.get(k).getNext().get(i).setNext(queues.get(j));

                    }

                }
            }
            else if(!graph.get(i).contains("Q") && !graph.get(i).contains("M")){

            }
        }
    }

}
/*
k = 0 , i = 1
k = 2 , i = 3
k = 2 , i = 4

i = 4
el row eli fih el jQ = 4
   [
    [0,Q,0,0,0,0]
   ,[0,0,M,0,0,0]
   ,[0,0,0,Q,Q,0]
   ,[0,0,0,0,0,M]
   ,[0,0,0,0,0,M]
   ,[0,0,0,0,0,0]
   ]
next[] = [0 , newMachineNode]
*/
