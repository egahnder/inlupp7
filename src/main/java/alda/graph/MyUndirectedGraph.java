package alda.graph;

import java.util.List;

/**
 * Created by loxtank on 2017-02-22.
 */
public class MyUndirectedGraph<T> implements UndirectedGraph {

    @Override
    public int getNumberOfNodes() {
        return 0;
    }

    @Override
    public int getNumberOfEdges() {
        return 0;
    }

    @Override
    public boolean add(Object newNode) {
        return false;
    }

    @Override
    public boolean connect(Object node1, Object node2, int cost) {
        return false;
    }

    @Override
    public boolean isConnected(Object node1, Object node2) {
        return false;
    }

    @Override
    public int getCost(Object node1, Object node2) {
        return 0;
    }

    @Override
    public List depthFirstSearch(Object start, Object end) {
        return null;
    }

    @Override
    public List breadthFirstSearch(Object start, Object end) {
        return null;
    }

    @Override
    public UndirectedGraph minimumSpanningTree() {
        return null;
    }
}
