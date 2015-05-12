package se.kth.inda14.snoject.graphs;

import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.Node;
import se.kth.inda14.snoject.interfaces.Graph;

import java.util.Iterator;

public class HashGraph implements Graph
{
    public int numVertices()
    {
        return 0;
    }

    public int numEdges()
    {
        return 0;
    }

    public int degree(int v) throws IllegalArgumentException
    {
        return 0;
    }

    public Iterator<Node> neighbors(int v) throws IllegalArgumentException
    {
        return null;
    }

    public boolean hasEdge(int v, int w) throws IllegalArgumentException
    {
        return false;
    }

    public int cost(int v, int w) throws IllegalArgumentException
    {
        return 0;
    }

    public void add(Edge edge) throws IllegalArgumentException
    {

    }

    public void remove(Edge edge) throws IllegalArgumentException
    {

    }
}
