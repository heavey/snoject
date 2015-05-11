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
import java.util.HashSet;
import java.util.Set;

public class XMLDataSource implements DataSource
{
    private Document doc;

    public void init() throws Exception
    {
        Path xml = FileSystems.getDefault().getPath("target/classes/graph.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        doc = db.parse(Files.newInputStream(xml));
    }

    public Set<Node> getNodes()
    {
        Set<Node> res = new HashSet<Node>();
        NodeList nodes = doc.getElementsByTagName("node");

        for (int i = 0; i < nodes.getLength(); i++)
        {
            Element e = (Element) nodes.item(i);

            int id = Integer.parseInt(e.getAttribute("id"));
            String name = e.getAttribute("name");

            Node node = new Node(id, name);
            res.add(node);
            System.out.println(node);
        }

        return res;
    }

    public Set<Edge> getEdges()
    {
        Set<Edge> res = new HashSet<Edge>();
        NodeList nodes = doc.getElementsByTagName("edge");

        for (int i = 0; i < nodes.getLength(); i++)
        {
            Element e = (Element) nodes.item(i);

            int from = Integer.parseInt(e.getAttribute("from"));
            int to = Integer.parseInt(e.getAttribute("to"));
            String name = e.getAttribute("name");

            int provider = Integer.parseInt(e.getAttribute("provider"));
            int time = Integer.parseInt(e.getAttribute("time"));
            int cost = Integer.parseInt(e.getAttribute("cost"));
            int environment = Integer.parseInt(e.getAttribute("environment"));

            Edge edge = new Edge(from, to, name, provider, cost, time, environment);
            res.add(edge);
            System.out.println(edge);
        }

        return res;
    }

    public Set<Provider> getProviders()
    {
        return null;
    }
}
