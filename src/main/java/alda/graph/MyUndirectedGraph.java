package alda.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by loxtank on 2017-02-22.
 */
public class MyUndirectedGraph<T> implements UndirectedGraph<T> {

    private HashMap<T, Node<T>> nodes = new HashMap<>();
    private HashSet<Node<T>> visitedNodes =  new HashSet<>();


    @Override
    public int getNumberOfNodes() {
        return 0;
    }

    @Override
    public int getNumberOfEdges() {
        return 0;
    }

    @Override
    public boolean add(T newNode) {
        if (nodes.containsKey(newNode)) {
            return false;
        } else {

        Node<T> node = new Node(newNode);
        nodes.put(newNode, node);
        return true;
        }
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

    private class Node<U> {
        public Node(U value) {
            this.value = value;
        }
        private U value;
        private List<Connection<U>> connections;

        private class Connection<V> {
            private Node<V> connected;
            private int cost;
        }

    }


}
