package org.example.producerconsumer.controllers;
import org.example.producerconsumer.services.GraphBuilder;
import org.example.producerconsumer.services.Simulator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

@RestController
public class Controller {
    GraphBuilder buildGraph = new GraphBuilder();
    Simulator simulator = new Simulator();
    @PostMapping("/graph")
    public String createGraph(@RequestBody ArrayList<ArrayList<String>> graph){
        /*ArrayList<ArrayList<String>> graph = new ArrayList<>();
        ArrayList<String> row1 = new ArrayList<>();
        row1.add("0");
        row1.add("Q");
        row1.add("0");
        row1.add("0");
        row1.add("0");
        row1.add("0");
        graph.add(row1);
        ArrayList<String> row2 = new ArrayList<>();
        row2.add("0");
        row2.add("0");
        row2.add("M");
        row2.add("0");
        row2.add("0");
        row2.add("0");
        graph.add(row2);
        ArrayList<String> row3 = new ArrayList<>();
        row3.add("0");
        row3.add("0");
        row3.add("0");
        row3.add("Q");
        row3.add("Q");
        row3.add("0");
        graph.add(row3);
        ArrayList<String> row4 = new ArrayList<>();
        row4.add("0");
        row4.add("0");
        row4.add("0");
        row4.add("0");
        row4.add("0");
        row4.add("M");
        graph.add(row4);
        ArrayList<String> row5 = new ArrayList<>();
        row5.add("0");
        row5.add("0");
        row5.add("0");
        row5.add("0");
        row5.add("0");
        row5.add("M");
        graph.add(row5);
        ArrayList<String> row6 = new ArrayList<>();
        row6.add("0");
        row6.add("0");
        row6.add("0");
        row6.add("0");
        row6.add("0");
        row6.add("0");
        graph.add(row6);*/
        buildGraph.createMandQ(graph);
        buildGraph.linkGraph(graph);
        buildGraph.createObservers();
        return "Graph created";
    }
    @GetMapping("/simulate")
    public String simulate(@RequestParam("productCount") int productCount) {
        simulator.createProductQueue(productCount,buildGraph);
        simulator.simulate(buildGraph);// queue gwaha el products {red, green, blue, red} w tt7t fi awel queue
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
}