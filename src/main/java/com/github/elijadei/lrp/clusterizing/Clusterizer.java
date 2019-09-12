package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.Node;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Clusterizer {

    private List<Node> nodes;


    public List<Node> getClusteredNodes() {
        KMeansPlusPlusClusterer<Node> clusterer = new KMeansPlusPlusClusterer<>(calculateCountOfClusterPoints());
        List<CentroidCluster<Node>> cluster = clusterer.cluster(nodes);
        return mapToNodes(cluster);
    }

    private int calculateCountOfClusterPoints() {
        return (int) Math.sqrt(nodes.size());
    }


    private List<Node> mapToNodes(List<CentroidCluster<Node>> cluster) {
        List<double[]> centerPoints = cluster.stream().map(c -> c.getCenter().getPoint()).collect(Collectors.toList());
        List<Node> centerNodes = new ArrayList<>();
        centerPoints.forEach(point -> nodes.stream()
                .filter(node -> node.doesItFeet(point))
                .findFirst()
                .ifPresent(centerNodes::add));
        return centerNodes;
    }

}
