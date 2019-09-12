package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.Node;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Clusterizer {

    private List<Node> nodes;

    public Clusterizer(List<Node> nodes) {
        this.nodes = nodes;
    }


    public List<Node> getClusteredNodes() {
        KMeansPlusPlusClusterer<Node> clusterer = new KMeansPlusPlusClusterer<>(calculateCountOfClusterPoints());
        List<CentroidCluster<Node>> cluster = clusterer.cluster(nodes);
        return mapToNodes(cluster);
    }

    private int calculateCountOfClusterPoints() {
        return (int) Math.sqrt(nodes.size());
    }


    private List<Node> mapToNodes(List<CentroidCluster<Node>> cluster) {
        return cluster.stream()
                .map(c -> Node.fromPoint(c.getCenter().getPoint()))
                .collect(Collectors.toList());
    }

}
