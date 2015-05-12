package se.kth.inda14.snoject.datasources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import se.kth.inda14.snoject.engine.Edge;
import se.kth.inda14.snoject.engine.Node;
import se.kth.inda14.snoject.engine.Provider;
import se.kth.inda14.snoject.interfaces.DataSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// TODO: Documentation
public class XMLDataSource implements DataSource
{
    private Document doc;
    private Map<Integer, Node> nodeCache;
    private Set<Edge> edgeCache;
    private Map<Integer, Provider> providerCache;

    public void init() throws Exception
    {
        Path xml = FileSystems.getDefault().getPath("target/classes/graph.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        doc = db.parse(Files.newInputStream(xml));
    }

    public Map<Integer, Node> getNodes()
    {
        if (nodeCache != null)
            return nodeCache;

        Map<Integer, Node> res = new HashMap<>();
        NodeList nodes = doc.getElementsByTagName("node");

        for (int i = 0; i < nodes.getLength(); i++)
        {
            Element e = (Element) nodes.item(i);

            int id = Integer.parseInt(e.getAttribute("id"));
            String name = e.getAttribute("name");

            Node node = new Node(id, name);
            res.put(id, node);
        }

        nodeCache = res;
        return res;
    }

    public Set<Edge> getEdges()
    {
        if (edgeCache != null)
            return edgeCache;

        Set<Edge> res = new HashSet<>();
        NodeList nodes = doc.getElementsByTagName("edge");

        for (int i = 0; i < nodes.getLength(); i++)
        {
            Element e = (Element) nodes.item(i);
            String name = e.getAttribute("name");

            int fromID = Integer.parseInt(e.getAttribute("from"));
            Node from = getNodes().get(fromID);

            int toId = Integer.parseInt(e.getAttribute("to"));
            Node to = getNodes().get(toId);

            int providerID = Integer.parseInt(e.getAttribute("provider"));
            Provider provider = getProviders().get(providerID);

            int time = Integer.parseInt(e.getAttribute("time"));
            int cost = Integer.parseInt(e.getAttribute("cost"));
            int environment = Integer.parseInt(e.getAttribute("environment"));

            Edge edge = new Edge(from, to, name, provider, cost, time, environment);
            res.add(edge);
        }

        edgeCache = res;
        return res;
    }

    public Map<Integer, Provider> getProviders()
    {
        if (providerCache != null)
            return providerCache;

        Map<Integer, Provider> res = new HashMap<>();
        NodeList nodes = doc.getElementsByTagName("provider");

        for (int i = 0; i < nodes.getLength(); i++)
        {
            Element e = (Element) nodes.item(i);

            int id = Integer.parseInt(e.getAttribute("id"));
            String name = e.getAttribute("name");
            String description = e.getElementsByTagName("description").item(0).getTextContent().trim();

            int time = Integer.parseInt(e.getAttribute("time"));
            int cost = Integer.parseInt(e.getAttribute("cost"));
            int environment = Integer.parseInt(e.getAttribute("environment"));

            Provider p = new Provider(id, name, description, cost, time, environment);
            res.put(id, p);
        }

        providerCache = res;
        return res;
    }
}
