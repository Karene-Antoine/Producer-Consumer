package org.example.producerconsumer.controllers;
import org.example.producerconsumer.entities.Snapshot;
import org.example.producerconsumer.services.GraphBuilder;
import org.example.producerconsumer.services.Simulator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
@RestController
public class Controller {
    GraphBuilder buildGraph;
    Simulator simulator;
    Snapshot snapshot;
    @PostMapping("/graph")
    public String createGraph(@RequestBody ArrayList<ArrayList<String>> graph){
        buildGraph = new GraphBuilder();
        simulator = new Simulator();
        buildGraph.createMandQ(graph);
        buildGraph.linkGraph(graph);
        buildGraph.createObservers();
        return "Graph created";
    }
    @GetMapping("/simulate")
    public String simulate(@RequestParam("productCount") int productCount) {
        simulator.createProductQueue(productCount,buildGraph);
        snapshot = buildGraph.storeInSnapshot();
        simulator.simulate(buildGraph);
        return "Congrats";
    }
    @GetMapping("/getQueues")
    public HashMap<Integer,Queue<String>> getQueue(){
        return simulator.getQueues(buildGraph);
    }
    @GetMapping("/getMachines")
    public HashMap<Integer,String> getMachine(){
        return simulator.getMachines(buildGraph);
    }
    @GetMapping("/restart")
    public String restart(){
        GraphBuilder buildGraph2 = buildGraph.restoreFromSnapshot(snapshot).deepCopy();
        System.out.println(buildGraph2.toString());
        System.out.println(buildGraph.restoreFromSnapshot(snapshot).toString());
        buildGraph = buildGraph2;
        simulator.simulate(buildGraph2);
        return "Restarted";
    }
}
