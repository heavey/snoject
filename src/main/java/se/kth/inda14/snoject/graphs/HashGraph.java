package se.kth.inda14.snoject.graphs;

import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.Node;
import se.kth.inda14.snoject.interfaces.Graph;

import java.util.*;

/**
 * Represents an implementation of the Graph interface, by
 * placing Nodes and Edges into HashMaps.
 */
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
        // Return empty iterator if no Map exists
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

    /**
     * {@inheritDoc Graph}
     */
    public Set<Edge> getEdges(Node from, Node to)
    {
        // Perform null checks
        if (from == null || to == null)
            return null;

        Map<Node, Set<Edge>> links = edges.get(from);

        // If no HashMap exists, no edges exist
        if (links == null)
            return null;

        // If a Set exists and is not empty, the Graph has Edges between these points
        if (links.get(to) != null)
            if (!links.get(to).isEmpty())
                return links.get(to);

        return null;
    }

    /**
     * {@inheritDoc Graph}
     */
    public Set<Edge> cost(Node from, Node to)
    {
        // Perform null checks
        if (from == null || to == null)
            return null;

        Map<Node, Set<Edge>> links = edges.get(from);

        // If no HashMap exists, return null
        if (links == null)
            return null;

        // If a Set exists, return it
        if (links.get(to) != null)
            return links.get(to);

        return null;
    }

    /**
     * {@inheritDoc Graph}
     */
    public void add(Edge edge)
    {
        // Perform null checks
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
