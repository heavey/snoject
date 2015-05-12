package se.kth.inda14.snoject.engine;

/**
 * Represents a Provider in the Graph space, which is not
 * a Graph building block, but is used by Edge objects to
 * provide additional information on a given provider for
 * the link between two nodes.
 *
 * Providers are immutable objects.
 */
public class Provider
{
    // Provider core data
    private int id;
    private String name;

    // Provider properties
    private String description;
    private int cost;
    private int time;
    private int environment;

    /**
     * Create a Provider object with given properties.
     *
     * Cost, time and environment properties range from -1 (low), 0 (medium) to 1 (high).
     *
     * @throws IllegalArgumentException if cost, time or environment are out of bounds.
     */
    public Provider(int id, String name, String description, int cost, int time, int environment)
    {
        if (cost < -1 || time < -1 || environment < -1)
            throw new IllegalArgumentException("Cost, time or environment less than -1");

        if (cost > 1 || time > 1 || environment > 1)
            throw new IllegalArgumentException("Cost, time or environment greater than 1");

        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.time = time;
        this.environment = environment;
    }

    // Getters
    public int getId()              { return id; }
    public String getName()         { return name; }
    public String getDescription()  { return description; }
    public int getCost()            { return cost; }
    public int getTime()            { return time; }
    public int getEnvironment()     { return environment; }

    @Override
    public String toString()
    {
        return String.format(
                "Provider{id=%d, name=%s, description=%s, cost=%d, time=%d, environment=%d}",
                id, name, description, cost, time, environment);
    }
}
