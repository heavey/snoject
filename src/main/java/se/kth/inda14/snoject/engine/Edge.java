package se.kth.inda14.snoject.engine;

/**
 * Represents a Graph edge (i.e. a link between two Nodes in the Graph)
 *
 * An Edge contains a starting and an ending point (Nodes, as well as
 * properties for the Edge. Properties include what Provider provides
 * the link between the two Nodes, and time, cost, and environmental data.
 *
 * Edges are immutable objects.
 */
public class Edge
{
    // Destination data
    private final Node from;
    private final Node to;

    // Edge core data
    private final String name;
    private final Provider provider;

    // Edge properties
    private final int cost;
    private final int time;
    private final int environment;

    /**
     * Construct a new Edge object with given properties.
     */
    public Edge(Node from, Node to, String name, Provider provider, int cost, int time, int environment)
    {
        this.from = from;
        this.to = to;
        this.name = name;
        this.provider = provider;
        this.cost = cost;
        this.time = time;
        this.environment = environment;
    }

    // Getters
    public Node getFrom()           { return from; }
    public Node getTo()             { return to; }
    public String getName()         { return name; }
    public Provider getProvider()   { return provider; }
    public int getCost()            { return cost; }
    public int getTime()            { return time; }
    public int getEnvironment()     { return environment; }

    @Override
    public String toString()
    {
        return String.format(
                "Edge{from=%s, to=%s, name=%s, provider=%s, cost=%d, time=%d, environment=%d}",
                from, to, name, provider, cost, time, environment);
    }
}
