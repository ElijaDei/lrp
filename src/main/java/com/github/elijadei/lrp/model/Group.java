package com.github.elijadei.lrp.model;

import org.apache.commons.math3.ml.clustering.CentroidCluster;

import java.util.List;


public class Group {

    private Point center;
    private List<Point> group;

    public Group(Point center, List<Point> group) {
        this.center = center;
        this.group = group;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
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
