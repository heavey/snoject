package se.kth.inda14.snoject.interfaces;

import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.Node;
import se.kth.inda14.snoject.engine.Provider;

import java.util.Map;
import java.util.Set;

/**
 * Represents a Graph data source.
 *
 * Data sources first connect, load or initialize using the init()
 * method. Nodes, Edges and Providers can then be accessed.
 *
 * Method return values must comply with Edge, Node and Provider classes.
 */
public interface DataSource
{
    /**
     * Init the Data Source by loading and connecting to necessary resources.
     *
     * @throws          Exception upon initialization error encounter
     */
    void init() throws Exception;

    /**
     * Get all Node objects by loading them from the data source.
     * Should not return any malformed Node objects.
     *
     * @return          Map with a Node's ID as key and its object as value
     */
    Map<Integer, Node> getNodes();

    /**
     * Get all Edge objects by loading them from the data source.
     * Should not return any malformed Edge objects.
     *
     * Construction of Edge objects is dependant on Nodes and Providers,
     * since an Edge object contains references to both.
     *
     * @return          A Set of all Edge objects
     */
    Set<Edge> getEdges();

    /**
     * Get all Provider objects by loading them from the data source.
     * Should not return any malformed Provider objects.
     *
     * @return          Map with a Provider's ID as key and its object as value
     */
    Map<Integer, Provider> getProviders();
}