package se.kth.inda14.snoject.interfaces;

import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.Node;
import se.kth.inda14.snoject.engine.Provider;

import java.util.Set;

/**
 * Represents an arbitrary Graph data source.
 */
// TODO: Documentation
public interface DataSource
{
    void init() throws Exception;

    Set<Node> getNodes();

    Set<Edge> getEdges();

    Set<Provider> getProviders();
}