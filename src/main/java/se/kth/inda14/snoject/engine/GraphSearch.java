package se.kth.inda14.snoject.engine;

import se.kth.inda14.snoject.interfaces.Graph;
import se.kth.inda14.snoject.interfaces.NodeAction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Performs various search operations on a Graph structure,
 * more specifically finding nodes and possible routes.
 */
public class GraphSearch
{
	/**
	 * Package local class to keep track of a vertex, and a previously visited vertex
	 */
	private class GraphPath
	{
		public Node current, previous;

		public GraphPath(Node current, Node previous)
		{
			this.current = current;
			this.previous = previous;
		}
	}

	/**
	 * Private instance variable which holds the route between nodes with the least
	 * amount of routes taken.
	 */
	private List<Node> path;

	public enum Priority { COST, TIME, ENVIRONMENT }

	public Set<Node> getNodesByName(Map<Integer, Node> nodes, String startsWith)
	{
		return nodes
				.values()
				.stream()
				.filter(n -> n.getName() != null)
				.filter(n -> n.getName().toLowerCase().startsWith(startsWith.toLowerCase()))
				.collect(Collectors.toSet());
	}

	private List<Node> getRouteNodes(Graph g, Node from, Node to)
	{
		Map<Node, List<Node>> paths = new HashMap<>();
		// ArrayList[] paths = new ArrayList[g.numNodes()];
		paths.put(from, new ArrayList<>());

		bfs(g, from, (graph, current, previous) -> {

			List<Node> previousPath = paths.get(previous);

			if (previousPath == null)
				previousPath = new ArrayList<>();

			previousPath.add(current);

			paths.put(current, previousPath);
			// paths[current.getId()] = new ArrayList(paths[previous.getId()]);
			// paths[current.getId()].add(current);

			// If a path is found, report it and exit the program
			if (current == to) {
				path = paths.get(current);
			}
		});

		return path;
	}

	/**
	 * Search through a Graph object using Breadth-First Search.
	 *
	 * @param g			Graph object to search
	 * @param v			Vertex to begin search from
	 * @param action	Visit action to perform
	 */
	private void bfs(Graph g, Node v, NodeAction action)
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