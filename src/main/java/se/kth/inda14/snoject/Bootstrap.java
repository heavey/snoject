package se.kth.inda14.snoject;

import se.kth.inda14.snoject.datasources.XMLDataSource;
import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.graphs.HashGraph;
import se.kth.inda14.snoject.interfaces.DataSource;
import se.kth.inda14.snoject.interfaces.Graph;

import java.util.Set;

public class Bootstrap
{
    /**
     * Bootstrap variables
      */
    // Set up dataSource
    DataSource ds = new XMLDataSource();

    // Set up graphEngine
    Graph g = new HashGraph();

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
        Set<Edge> edges = ds.getEdges();
        edges.forEach(g::add);

        return this;
    }

    public Bootstrap startServer()
    {
        // TODO
        return this;
    }

}
