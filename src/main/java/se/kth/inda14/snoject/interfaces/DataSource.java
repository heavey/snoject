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
    public void init() throws Exception;

    public Set<Node> getNodes();

    public Set<Edge> getEdges();

    public Set<Provider> getProviders();
}