package se.kth.inda14.snoject.engine;

import se.kth.inda14.snoject.interfaces.Graph;
import se.kth.inda14.snoject.interfaces.NodeAction;

import java.util.*;
import java.util.stream.Collectors;

public class GraphSearch
{
	public Set<Node> getNodesByName(Map<Integer, Node> nodes, String startsWith)
	{
		return nodes
				.values()
				.stream()
				.filter(n -> n.getName() != null)
				.filter(n -> n.getName().toLowerCase().startsWith(startsWith.toLowerCase()))
				.collect(Collectors.toSet());
	}

	/**
	 * Search through a Graph object using Breadth-First Search.
	 *
	 * @param g			Graph object to search
	 * @param v			Vertex to begin search from
	 * @param action	Visit action to perform
	 */
	public void bfs(Graph g, Node v, NodeAction action)
	{
		// Use a Queue for BFS
		Queue<GraphPath> q = new LinkedList<>();
		Map<Node, Boolean> visited = new HashMap<>();

		visited.put(v, true);
		q.add(new GraphPath(v, v));

		while(!q.isEmpty())
		{
			// De-queue a GraphPath and perform visit action
			GraphPath n = q.remove();
			action.act(g, n.current, n.previous);

			Iterator<Node> it = g.neighbors(n.current);

			while (it.hasNext())
			{
				Node i = it.next();

				// If the Vertex is not visited, visit and add it to queue
				if (!visited.get(i))
				{
					visited.put(i, true);
					q.add(new GraphPath(i, n.current));
				}
			}
		}
	}


}

/**
 * Package local class to keep track of a vertex, and a previously visited vertex
 */
class GraphPath
{
	public Node current, previous;

	public GraphPath(Node current, Node previous)
	{
		this.current = current;
		this.previous = previous;
	}
}