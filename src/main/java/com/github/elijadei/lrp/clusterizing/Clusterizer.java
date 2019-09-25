package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.model.Node;
import com.github.elijadei.lrp.model.Nodes;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.List;
import java.util.stream.Collectors;

public class Clusterizer {

    private List<Node> nodes;

    private int clusterSize;

    public Clusterizer(List<Node> nodes, int clusterSize) {
        this.nodes = nodes;
        this.clusterSize = clusterSize;
    }


    public List<Nodes> getClusteredNodes() {
        KMeansPlusPlusClusterer<Node> clusterer = new KMeansPlusPlusClusterer<>(calculateCountOfClusterPoints());
        List<CentroidCluster<Node>> cluster = clusterer.cluster(nodes);
        return mapToNodes(cluster);
    }

    private int calculateCountOfClusterPoints() {
        return (int) Math.sqrt(clusterSize);
    }


    private List<Nodes> mapToNodes(List<CentroidCluster<Node>> cluster) {
        return cluster.stream()
                .map(Nodes::fromCluster)
                .collect(Collectors.toList());
    }

}
