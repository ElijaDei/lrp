package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.Node;
import com.github.elijadei.lrp.Nodes;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.List;
import java.util.stream.Collectors;

public class DBSCAN {

    private List<Node> nodes;
    private int clusterPoints;

    public DBSCAN(List<Node> nodes, int clusterPoints) {
        this.nodes = nodes;
        this.clusterPoints = clusterPoints;
    }


    public List<Nodes> getClusteredNodes() {
        DBSCANClusterer clustererDB = new DBSCANClusterer<>(2.0,50);
        List<Cluster<Node>> cluster = clustererDB.cluster(nodes);
        return mapToNodes(cluster);
    }



    private List<Nodes> mapToNodes(List<Cluster<Node>> cluster) {
        return cluster.stream()
                .map(Nodes::fromClusterDB)
                .collect(Collectors.toList());
    }

}
