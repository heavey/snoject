package se.kth.inda14.snoject;

public class Bootstrap
{
    // Bootstrap variables:
    // Set up dataSource
    // Set up graphEngine

    public static void main(String[] args)
    {
        Bootstrap
            .newInstance()
            .bootDataSource()
            .buildGraph()
            .startServer();
    }

    public static Bootstrap newInstance()
    {
        return new Bootstrap();
    }

    public Bootstrap bootDataSource()
    {
        // TODO
        return this;
    }

    public Bootstrap buildGraph()
    {
        // TODO
        return this;
    }

    public Bootstrap startServer()
    {
        // TODO
        return this;
    }

}
