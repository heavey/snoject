package se.kth.inda14.snoject.engine;

// TODO: Documentation
public class Edge
{
    private final int from;
    private final int to;

    private final String name;
    private final int provider;

    private final int cost;
    private final int time;
    private final int environment;

    public Edge(int from, int to, String name, int provider, int cost, int time, int environment)
    {
        this.from = from;
        this.to = to;
        this.name = name;
        this.provider = provider;
        this.cost = cost;
        this.time = time;
        this.environment = environment;
    }

    public int getFrom()
    {
        return from;
    }

    public int getTo()
    {
        return to;
    }

    public String getName()
    {
        return name;
    }

    public int getProvider()
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
}
