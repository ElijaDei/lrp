package com.github.elijadei.lrp.model;

import org.apache.commons.math3.ml.clustering.CentroidCluster;

import java.util.List;

import static com.github.elijadei.lrp.Constants.DEPOT_COST;

public class Group {

    private Point depot;
    private List<Point> group;
    private Integer demand;

    public Group(Point depot, List<Point> group) {
        this.depot = depot;
        this.group = group;
    }

    public Integer getDemand() {
        return demand;
    }

    public void setDemand(Integer demand) {
        this.demand = demand;
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
        Group group = new Group(Point.fromMathPoint(cluster.getCenter().getPoint()), cluster.getPoints());
        group.setDemand(group.getGroup().stream().map(Point::getDemand).reduce(Integer::sum).get() + DEPOT_COST);
        return new Group(Point.fromMathPoint(cluster.getCenter().getPoint()), cluster.getPoints());
    }

}
