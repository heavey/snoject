package se.kth.inda14.snoject.engine;

import java.util.Map;
import java.util.Set;
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
}
