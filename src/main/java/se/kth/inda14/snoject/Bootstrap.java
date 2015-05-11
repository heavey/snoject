package se.kth.inda14.snoject;

import se.kth.inda14.snoject.datasources.XMLDataSource;
import se.kth.inda14.snoject.interfaces.DataSource;

public class Bootstrap
{
    /**
     * Bootstrap variables
      */
    // Set up dataSource
    DataSource ds = new XMLDataSource();

    // Set up graphEngine

    public static void main(String[] args) throws Exception
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

    public Bootstrap bootDataSource() throws Exception
    {
        ds.init();

        return this;
    }

    public Bootstrap buildGraph()
    {
        ds.getNodes();
        ds.getEdges();
        ds.getProviders();

        return this;
    }

    public Bootstrap startServer()
    {
        // TODO
        return this;
    }

}
