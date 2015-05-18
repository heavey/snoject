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

	public List<Edge> getRoute(Graph g, Map<Integer, Node> nodes, String fromString,
							   String toString, Priority priority) throws NumberFormatException
	{
		// Parse input
		int fromID = Integer.parseInt(fromString);
		int toID = Integer.parseInt(toString);

		// Retrieve Node references
		Node from = nodes.get(fromID);
		Node to = nodes.get(toID);

		// Set up List for route and previous Node
		List<Edge> route = new ArrayList<>();
		Node previous = null;
		boolean firstRun = true;

		// Make sure Nodes exist
		if (from == null || to == null)
			return null;

		// Perform BFS
		List<Node> routeNodes = getRouteNodes(g, from, to);

		for (Node n : routeNodes)
		{
			if (firstRun)
			{
				firstRun = false;
				previous = n;
				continue;
			}

			Set<Edge> edges = g.getEdges(previous, n);

			if (edges == null)
				return null;

			Edge edge = getCheapestEdge(edges, priority);
			route.add(edge);

			previous = n;
		}

		return route;
	}

	private Edge getCheapestEdge(Set<Edge> edges, Priority priority)
	{
		Iterator<Edge> it = edges.iterator();

		Edge leastEdge = null;
		int leastCost = Integer.MAX_VALUE;

		while (it.hasNext())
		{
			int edgeCost;
			Edge edge = it.next();

			switch (priority)
			{
				case COST:
					edgeCost = edge.getCost();
					break;
				case TIME:
					edgeCost = edge.getTime();
					break;
				case ENVIRONMENT:
					edgeCost = edge.getEnvironment();
					break;
				default:
					throw new IllegalArgumentException("Priority is null");
			}

			if (edgeCost < leastCost)
			{
				leastCost = edgeCost;
				leastEdge = edge;
			}
		}

		return leastEdge;
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
		Set<Node> visited = new HashSet<>();

		visited.add(v);
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
				if (!visited.contains(i))
				{
					visited.add(i);
					q.add(new GraphPath(i, n.current));
				}
			}
		}
	}
}