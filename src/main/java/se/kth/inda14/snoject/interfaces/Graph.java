package se.kth.inda14.snoject.interfaces;

import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.Node;

import java.util.Iterator;
import java.util.Set;

/**
 * Based on Stefan Nilsson's Graph interface from
 * Introduction to Computer Science 2013/2014
 */
public interface Graph
{
    /**
     * Returns the number of nodes in this graph.
     *
     * @return          number of nodes in graph
     */
    int numNodes();

    /**
     * Returns the number of edges in this graph.
     *
     * @return          number of edges in this graph
     */
    int numEdges();

    /**
     * Returns the degree of node n.
     *
     * @param n         Node
     * @return          the degree of Node n
     * @throws          IllegalArgumentException if n is out of range
     */
    int degree(Node n);

    /**
     * Returns an iterator of vertices adjacent to v.
     *
     * @param n         Node
     * @return          an iterator of Nodes adjacent to n
     * @throws          IllegalArgumentException if n is out of range
     */
    Iterator<Node> neighbors(Node n);

    /**
     * Returns the Edge set between from and to, or null if no edges exist.
     *
     * @param from      node
     * @param to        node
     * @return          Set of all Edge objects between from and to
     */
    Set<Edge> getEdges(Node from, Node to);

    /**
     * Returns the edge cost if n and m are adjacent and an edge cost has been
     * assigned, NO_COST otherwise.
     *
     * @param from      node
     * @param to        node
     * @return          edge cost
     */
    Set<Edge> cost(Node from, Node to);

    /**
     * Inserts a directed edge.
     *
     * @param edge      Edge object
     * @throws          IllegalArgumentException if from or to are out of range
     */
    void add(Edge edge);
}
