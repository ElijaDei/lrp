package com.github.elijadei.lrp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "nodes")
public class Nodes {

    @XmlElement(name = "node")
    private List<Node> nodes;

    public Nodes() {
    }

    public Nodes(List<Node> nodes) {
        this.nodes = nodes;
    }


    public List<Node> getNodes() {
        return nodes;
    }
}
