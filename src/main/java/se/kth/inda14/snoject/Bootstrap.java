package se.kth.inda14.snoject;

import se.kth.inda14.snoject.datasources.XMLDataSource;
import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.GraphSearch;
import se.kth.inda14.snoject.engine.Node;
import se.kth.inda14.snoject.graphs.HashGraph;
import se.kth.inda14.snoject.interfaces.DataSource;
import se.kth.inda14.snoject.interfaces.Graph;

import java.util.Map;
import java.util.Set;

import static se.kth.inda14.snoject.engine.JSONUtil.json;
import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class Bootstrap
{
    /**
     * Bootstrap variables
      */
    // Set up dataSource
    DataSource ds = new XMLDataSource();

    // Set up graphEngine
    Graph g = new HashGraph();
	private Map<Integer, Node> nodes;

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
        // Retrieving all Edge objects will automatically retrieve all Nodes and Providers
		Set<Edge> edges = ds.getEdges();
        edges.forEach(g::add);

		// Retrieve Nodes from DS cache for auto complete
		nodes = ds.getNodes();

		return this;
    }

    public Bootstrap startServer()
    {
		GraphSearch gs = new GraphSearch();
        
		staticFileLocation("/web");

		get("/api/nodes/", (req, res) -> "{}");
		get("/api/route/", (req, res) -> "{}");

		get("/api/nodes/:name", (req, res) ->
				gs.getNodesByName(nodes, req.params(":name")), json());

		get("/api/route/:from/:to", ((req, res) ->
				gs.getRoutes(g, nodes, req.params(":from"), req.params(":to"))), json());

        after(((req, res) -> res.type("application/json;charset=utf8")));
		exception(Exception.class, ((e, req, res) -> e.getMessage()));

        return this;
    }
}
