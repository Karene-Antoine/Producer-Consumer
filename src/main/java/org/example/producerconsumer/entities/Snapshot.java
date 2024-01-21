package org.example.producerconsumer.entities;
import org.example.producerconsumer.services.GraphBuilder;
public class Snapshot {
    private final GraphBuilder gBuilder;
    public Snapshot(GraphBuilder gBuilder) {
        this.gBuilder = gBuilder;
    }
    public GraphBuilder getSavedGraphBuilder() {
        return gBuilder;
    }
}
