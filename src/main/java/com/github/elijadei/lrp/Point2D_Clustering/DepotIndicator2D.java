package com.github.elijadei.lrp.Point2D_Clustering;

import com.github.elijadei.lrp.model.GroupWithDepot;
import com.github.elijadei.lrp.model.Point;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DepotIndicator2D {

    private List<List<Point>> groups;

    public DepotIndicator2D(List<List<Point>> groups) {
        this.groups = groups;
    }

    public List<GroupWithDepot> groupWithDepot() {
        KMeansPlusPlusClusterer<Point> clusterer = new KMeansPlusPlusClusterer<>(10);

         return this.groups.stream().map(group -> {
             List<CentroidCluster<Point>> cluster = clusterer.cluster(group);
             CentroidCluster<Point> pointCentroidCluster = cluster.get(0);
       return new GroupWithDepot(Point.fromMathPoint(pointCentroidCluster.getCenter().getPoint()), group);
    }).collect(Collectors.toList());
        }


}