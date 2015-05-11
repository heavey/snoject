package se.kth.inda14.snoject.engine;

// TODO: Documentation
public class Provider
{
    private int id;
    private String name;

    private String description;
    private int cost;
    private int time;
    private int environment;

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

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
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
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", time=" + time +
                ", environment=" + environment +
                '}';
    }
}
