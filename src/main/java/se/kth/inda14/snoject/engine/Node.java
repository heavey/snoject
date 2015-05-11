package se.kth.inda14.snoject.engine;

/**
 * Represents a Graph Node (i.e. a destination in the graph)
 */
public class Node
{
    private final int id;
    private final String name;

    public Node(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
