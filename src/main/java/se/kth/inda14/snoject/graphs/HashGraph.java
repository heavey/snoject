package se.kth.inda14.snoject.graphs;

import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.Node;
import se.kth.inda14.snoject.interfaces.Graph;

import java.util.*;

public class HashGraph implements Graph
{
    private final Map<Node, Map<Node, Set<Edge>>> edges = new HashMap<>();
    private int numEdges = 0;

    /**
     * {@inheritDoc Graph}
     */
    public int numNodes()
    {
        return edges.size();
    }

    /**
     * {@inheritDoc Graph}
     */
    public int numEdges()
    {
        return numEdges;
    }

    /**
     * {@inheritDoc Graph}
     */
    public int degree(Node n)
    {
        if (edges.get(n) != null)
            return edges.get(n).size();
        else
            return 0;
    }

    /**
     * {@inheritDoc Graph}
     */
    public Iterator<Node> neighbors(Node n)
    {
        if (edges.get(n) == null)
            return new Iterator<Node>()
            {
                @Override
                public boolean hasNext()
                {
                    return false;
                }

                @Override
                public Node next()
                {
                    throw new NoSuchElementException("Reached end of iteration");
                }
            };

        return edges.get(n).keySet().iterator();
    }

    public boolean hasEdge(Node from, Node to)
    {
        if (from == null || to == null)
            return false;

        Map<Node, Set<Edge>> links = edges.get(from);

        if (links == null)
            return false;

        // If a Set exists and is not empty, the Graph has an Edge at this point
        if (links.get(to) != null)
            if (!links.get(to).isEmpty())
                return true;

        return false;
    }

    public Set<Edge> cost(Node from, Node to)
    {
        if (from == null || to == null)
            return null;

        Map<Node, Set<Edge>> links = edges.get(from);

        if (links == null)
            return null;

        // If a Set exists, return it
        if (links.get(to) != null)
            return links.get(to);

        return null;
    }

    public void add(Edge edge)
    {
        if (edge == null)
            return;

        Node from = edge.getFrom();
        Node to = edge.getTo();

        // Create HashMap if it does not exist
        if (edges.get(from) == null)
            edges.put(from, new HashMap<>());

        // Create Set if it does not exist
        Set<Edge> edgeSet = edges.get(from).get(to);

        if (edgeSet == null)
        {
            edgeSet = new HashSet<>();
            edges.get(from).put(to, edgeSet);
        }

        // Add the edge
        edgeSet.add(edge);
        numEdges++;
    }
}
