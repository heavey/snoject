package se.kth.inda14.snoject.engine;

/**
 * Represents a Graph Node (i.e. a destination in the graph)
 *
 * A Node contains a node ID, and a human readable name.
 *
 * Nodes are immutable objects.
 */
public class Node
{
    // Node core data
    private final int id;
    private final String name;

    /**
     * Construct a new Node with given properties.
     */
    public Node(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId()          { return id; }
    public String getName()     { return name; }

    @Override
    public String toString()
        { return String.format("Node{id=%d, name=%s}", id, name); }
}
