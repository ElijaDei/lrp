package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.model.GroupWithDepot;
import com.github.elijadei.lrp.model.Point;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.List;
import java.util.stream.Collectors;

public class DepotIndicator {

    private List<List<Point>> groups;

    public DepotIndicator(List<List<Point>> groups) {
        this.groups = groups;
    }

    public List<GroupWithDepot> groupWithDepot() {
        KMeansPlusPlusClusterer<Point> clusterer = new KMeansPlusPlusClusterer<>(1);
        return this.groups.stream().map(group -> {
            List<CentroidCluster<Point>> cluster = clusterer.cluster(group);
            CentroidCluster<Point> pointCentroidCluster = cluster.get(0);
            return new GroupWithDepot(Point.fromMathPoint(pointCentroidCluster.getCenter().getPoint()), group);
        }).collect(Collectors.toList());
    }
}
