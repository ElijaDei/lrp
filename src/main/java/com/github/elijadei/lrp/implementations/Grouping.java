package com.github.elijadei.lrp.implementations;

import com.github.elijadei.lrp.intefaces.GroupingInterface;
import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.Point;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.List;
import java.util.stream.Collectors;

public class Grouping implements GroupingInterface {

    private Integer numberOfClusters;

    public Grouping(Integer numberOfClusters) {
        this.numberOfClusters = numberOfClusters;
    }

    @Override
    public List<Group> groupPoints(List<Point> points) {
        KMeansPlusPlusClusterer<Point> kMeansPlusPlusClusterer = new KMeansPlusPlusClusterer<>(numberOfClusters);
        List<CentroidCluster<Point>> cluster = kMeansPlusPlusClusterer.cluster(points);
        return cluster.stream().map(Group::fromCluster).collect(Collectors.toList());
    }
}
