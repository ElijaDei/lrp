package com.github.elijadei.lrp;

import org.apache.commons.math3.ml.clustering.CentroidCluster;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "nodes")
public class Nodes {

    private Node node;

    @XmlElement(name = "node")
    private List<Node> nodes;

    public Nodes() {}

    public Nodes(List<Node> nodes) {
        this.nodes = nodes;
    }


    public List<Node> getNodes() {
        return nodes;
    }

    public void setCenter(Node node) {
        this.node = node;
    }

    public Node getCenter() {
        return node;
    }

    public static Nodes fromCluster(CentroidCluster<Node> cluster) {
        Nodes nodes = new Nodes(cluster.getPoints());
        nodes.setCenter(Node.fromPoint(cluster.getCenter().getPoint()));
        return nodes;
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "node=" + node +
                ", nodes=" + nodes +
                '}';
    }
}
