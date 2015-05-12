package se.kth.inda14.snoject.engine;

// TODO: Documentation
public class Edge
{
    private final Node from;
    private final Node to;

    private final String name;
    private final Provider provider;

    private final int cost;
    private final int time;
    private final int environment;

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

    public Node getFrom()
    {
        return from;
    }

    public Node getTo()
    {
        return to;
    }

    public String getName()
    {
        return name;
    }

    public Provider getProvider()
    {
        return provider;
    }

    public int getCost()
    {
        return cost;
    }

    public int getTime()
    {
        return time;
    }

    public int getEnvironment()
    {
        return environment;
    }

    @Override
    public String toString()
    {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", name='" + name + '\'' +
                ", provider=" + provider +
                ", cost=" + cost +
                ", time=" + time +
                ", environment=" + environment +
                '}';
    }
}
