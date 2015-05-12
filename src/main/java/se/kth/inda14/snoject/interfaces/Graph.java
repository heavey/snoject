package se.kth.inda14.snoject.interfaces;

import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.Node;

import java.util.Iterator;

/**
 * Based on Stefan Nilsson's Graph interface from
 * Introduction to Computer Science 2013/2014
 */
public interface Graph
{
    /**
     * Enum to select what type of cost is requested.
     */
    enum CostType { TIME, COST, ENVIRONMENT }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return          number of vertices in this graph
     */
    int numVertices();

    /**
     * Returns the number of edges in this graph.
     *
     * @return          number of edges in this graph
     */
    int numEdges();

    /**
     * Returns the degree of vertex v.
     *
     * @param v         vertex
     * @return          the degree of vertex v
     * @throws          IllegalArgumentException if v is out of range
     */
    int degree(int v) throws IllegalArgumentException;

    /**
     * Returns an iterator of vertices adjacent to v.
     *
     * @param v         vertex
     * @return          an iterator of vertices adjacent to v
     * @throws          IllegalArgumentException if v is out of range
     */
    Iterator<Node> neighbors(int v) throws IllegalArgumentException;

    /**
     * Returns true if there is an edge from v to w.
     *
     * @param v         vertex
     * @param w         vertex
     * @return          true if there is an edge from v to w.
     * @throws          IllegalArgumentException if v or w are out of range
     */
    boolean hasEdge(int v, int w) throws IllegalArgumentException;

    /**
     * Returns the edge cost if v and w are adjacent and an edge cost has been
     * assigned, NO_COST otherwise.
     *
     * @param v         vertex
     * @param w         vertex
     * @return          edge cost
     * @throws          IllegalArgumentException if v or w are out of range
     */
    int cost(int v, int w) throws IllegalArgumentException;

    /**
     * Inserts a directed edge. (No edge cost is assigned.)
     *
     * @param edge      Edge object
     * @throws          IllegalArgumentException if from or to are out of range
     */
    void add(Edge edge) throws IllegalArgumentException;

    /**
     * Removes the edge.
     *
     * @param edge      Edge object to remove
     * @throws          IllegalArgumentException if from or to are out of range
     */
    void remove(Edge edge) throws IllegalArgumentException;
}
