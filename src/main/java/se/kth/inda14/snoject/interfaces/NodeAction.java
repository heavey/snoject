package se.kth.inda14.snoject.interfaces;

import se.kth.inda14.snoject.engine.Node;

/**
 * This interface contains a single act method intended to be called during a
 * traversal of a graph.
 *
 * @author Stefan Nilsson
 * @version 2012-12-30
 */
public interface NodeAction
{
	/**
	 * An action performed when a search of the graph g visits node current.
	 *
	 * @param g			a graph
	 * @param current	current Node in the graph
	 * @param previous	previous Node in the graph
	 */
	void act(Graph g, Node current, Node previous);
}
