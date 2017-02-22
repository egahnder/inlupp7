package alda.graph;

import javax.management.timer.Timer;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by loxtank on 2017-02-22.
 */
public class MyUndirectedGraph<T> implements UndirectedGraph<T> {

    private HashMap<T, Node<T>> nodes = new HashMap<>();


    @Override
    public int getNumberOfNodes() {
        return nodes.size();
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
    public boolean connect(T node1, T node2, int cost) {
        Node firstNode = nodes.get(node1);
        Node secondNode = nodes.get(node2);
        if (cost < 1) {
            return false;
        } else if (!nodes.containsKey(node1) || !nodes.containsKey(node2)) {
            return false;
        } else if (isConnected(node1, node2)) {
            firstNode.updateCost(secondNode, cost);
            secondNode.updateCost(firstNode, cost);
            return true;

        } else {

            firstNode.addConnection(secondNode, cost);
            secondNode.addConnection(firstNode, cost);


            return true;
        }
    }

    @Override
    public boolean isConnected(T node1, T node2) {
        Node nodeToCheck = nodes.get(node1);
        Node node2ToCheck = nodes.get(node2);

        return nodeToCheck != null && node2ToCheck != null && nodeToCheck.hasConnection(node2ToCheck);
    }

    @Override
    public int getCost(T node1, T node2) {
        Node firstNode = nodes.get(node1);
        Node secondNode = nodes.get(node2);
        if (firstNode != null && secondNode != null) {
            return firstNode.getCost(secondNode);
        } else {
            return -1;
        }

    }

    @Override
    public List<T> depthFirstSearch(T start, T end) {
        HashSet<Node<T>> visitedNodes = new HashSet<>();
        Node currentNode = nodes.get(start);
        Node goalNode = nodes.get(end);
        if (currentNode == null || goalNode == null) {
            return new ArrayList<T>();

        } else {
            Stack<Node<T>> stack = new Stack();

            stack.push(currentNode);
            if (checkAndMarkVisited(currentNode, goalNode, visitedNodes)) {
                return stack.stream().map(n -> n.getValue()).collect(Collectors.toList());
            }
            while (!stack.isEmpty()) {
                Node<T> nextNeighbour = currentNode.nextNeighbour(visitedNodes);
                if (nextNeighbour == null) {
                    stack.pop();
                    if (!stack.isEmpty()) {
                        currentNode = stack.peek();
                    }
                } else {
                    stack.push(nextNeighbour);
                    currentNode = nextNeighbour;
                    boolean isGoal = checkAndMarkVisited(currentNode, goalNode, visitedNodes);
                    if (isGoal) {
                        return stack.stream().map(n -> n.getValue()).collect(Collectors.toList());
                    }

                }
            }
        }
        return new ArrayList<T>();
    }


    private boolean checkAndMarkVisited(Node start, Node end, HashSet<Node<T>> visitedNodes) {
        if (visitedNodes.contains(start)) {
            return false;
        }

        visitedNodes.add(start);
        if (start.equals(end)) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    public List<T> breadthFirstSearch(T start, T end) {
        HashMap<Node<T>, Node<T>> parents = new HashMap<>();
        Queue<Node<T>> queue = new LinkedList<>();
        HashSet<Node<T>> visitedNodes = new HashSet<>();

        Node<T> currentNode = nodes.get(start);
        Node<T> goalNode = nodes.get(end);
        if (currentNode == null || goalNode == null) {
            return new ArrayList<T>();

        } else {
            queue.add(currentNode);
            while (!queue.isEmpty()) {
                currentNode = queue.poll();
                if (checkAndMarkVisited(currentNode, goalNode, visitedNodes)) {
                    List<T> path = new ArrayList<>();
                    while (currentNode != null) {
                        path.add(currentNode.value);
                        currentNode = parents.get(currentNode);
                    }
                    Collections.reverse(path);
                    return path;
                } else {
                    Set<Node<T>> children = currentNode.connections.keySet();
                    for (Node<T> child : children) {
                        if (!visitedNodes.contains(child) && !parents.containsKey(child)) {
                            queue.add(child);
                            parents.put(child, currentNode);
                        }
                    }
                }

            }
        }
        return new ArrayList<T>();
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
        private HashMap<Node<U>, Integer> connections = new HashMap<>();

        public boolean hasConnection(Node<U> node) {
            return connections.containsKey(node);
        }

        public boolean addConnection(Node other, Integer cost) {
            connections.put(other, cost);
            return true;
        }

        public void updateCost(Node otherNode, Integer cost) {
            connections.put(otherNode, cost);
        }

        public int getCost(Node otherNode) {
            Integer cost = connections.get(otherNode);

            return cost != null ? cost : -1;
        }

        public U getValue() {
            return value;
        }


        private Node<U> nextNeighbour(HashSet<Node<T>> visitedNodes) {
            for (Node<U> neighbour : connections.keySet()) {
                if (!visitedNodes.contains(neighbour)) {
                    return neighbour;
                }
            }
            return null;
        }

    }


}
