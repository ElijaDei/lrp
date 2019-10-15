package com.github.elijadei.lrp.model;

import java.awt.geom.Point2D;
import java.util.List;

public class GroupWithDepot {

    private Point depot;
    private List<Point> group;

    private Point2D depot2D;
    private List<Point2D> group2D;

    public GroupWithDepot(Point depot, List<Point> group) {
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

    public Point2D getDepot2D() {
        return depot2D;
    }
    public void setDepot2D(Point2D depot2D) {
        this.depot2D = depot2D;
    }
    public List<Point2D> getGroup2D() {
        return group2D;
    }
    public void setGroup2D(List<Point2D> group) {
        this.group2D = group;
    }
}
