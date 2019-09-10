package com.github.elijadei.lrp.util;

import com.github.elijadei.lrp.Node;
import com.github.elijadei.lrp.Start;
import scala.util.Random;

import java.util.List;

public class Clusterizer {

    private List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Clusterizer(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void cluster() {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            node.getX();

        }

    }
}