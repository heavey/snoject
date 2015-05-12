package se.kth.inda14.snoject.graphs;

import org.junit.Before;
import org.junit.Test;
import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.Node;
import se.kth.inda14.snoject.engine.Provider;
import se.kth.inda14.snoject.interfaces.Graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.Assert.*;

public class HashGraphTest
{
    private final Node arlanda = new Node(1, "Arlanda Flygplats");
    private final Node bromma = new Node(2, "Bromma Flygplats");
    private Graph graph;
    private Edge[] edges;

    @Before
    public void setUp() throws Exception
    {
        graph = new HashGraph();

        Provider fridolinjen = new Provider(1, "Fridolinjen", "Beprövad av utbildningsministern", 1, -1, 1);

        edges = new Edge[]{
                new Edge(arlanda, bromma, "Fridolinjen Arlanda-Bromma", fridolinjen, 125000, 10, 220000),
                new Edge(bromma, arlanda, "Fridolinjen Bromma-Arlanda", fridolinjen, 125000, 10, 220000)
        };
    }

    @Test
    public void testNumNodes() throws Exception
    {
        // Test empty Graph
        assertEquals(0, graph.numNodes());

        // Test populated Graph
        graph.add(edges[0]);
        graph.add(edges[1]);
        assertEquals(2, graph.numNodes());
    }

    @Test
    public void testNumEdges() throws Exception
    {
        // Test empty Graph
        assertEquals(0, graph.numEdges());

        // Test populated Graph
        graph.add(edges[0]);
        graph.add(edges[1]);
        assertEquals(2, graph.numEdges());
    }

    @Test
    public void testDegree() throws Exception
    {
        // Test empty Graph
        assertEquals(0, graph.degree(arlanda));

        // Test populated Graph
        graph.add(edges[0]);
        graph.add(edges[1]);

        assertEquals(1, graph.degree(arlanda));
        assertEquals(1, graph.degree(bromma));
    }

    @Test
    public void testNeighbors() throws Exception
    {
        // Test empty Graph
        Iterator<Node> neighbors = graph.neighbors(arlanda);
        assertFalse(neighbors.hasNext());

        // Test populated Graph
        graph.add(edges[0]);
        graph.add(edges[1]);

        // Get new Iterator and make assertions
        neighbors = graph.neighbors(arlanda);
        assertTrue(neighbors.hasNext());
        assertEquals(bromma, neighbors.next());
    }

    @Test
    public void testHasEdge() throws Exception
    {
        // Test empty Graph
        assertFalse(graph.hasEdge(arlanda, bromma));

        // Test populated Graph
        graph.add(edges[0]);
        assertTrue(graph.hasEdge(arlanda, bromma));
    }

    @Test
    public void testCost() throws Exception
    {
        // Test empty Graph
        assertEquals(null, graph.cost(arlanda, bromma));

        // Test populated Graph
        graph.add(edges[0]);
        graph.add(edges[1]);
        assertEquals(new HashSet<>(Collections.singletonList(edges[0])), graph.cost(arlanda, bromma));
        assertEquals(new HashSet<>(Collections.singletonList(edges[1])), graph.cost(bromma, arlanda));
    }

    @Test
    public void testAdd() throws Exception
    {
        // Test add null
        graph.add(null);

        // Test populated Graph
        graph.add(edges[0]);
        assertTrue(graph.hasEdge(arlanda, bromma));
    }
}