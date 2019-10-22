package com.github.elijadei.lrp.model;

import org.apache.commons.math3.ml.clustering.CentroidCluster;

import java.util.List;

public class Group {

    private Point depot;
    private List<Point> group;

    public Group(Point depot, List<Point> group) {
        this.depot = depot;
        this.group = group;
    }

    public Point getDepot() {
        return depot;
    }

    public void setDepot(Point depot) {
        this.depot = depot;
    }

    public List<Point> getGroup() {
        return group;
    }

    public void setGroup(List<Point> group) {
        this.group = group;
    }

    public static Group fromCluster(CentroidCluster<Point> cluster) {
        return new Group(Point.fromMathPoint(cluster.getCenter().getPoint()), cluster.getPoints());
    }
}
