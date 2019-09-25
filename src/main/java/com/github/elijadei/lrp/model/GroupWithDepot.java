package com.github.elijadei.lrp.model;

import java.util.List;

public class GroupWithDepot {

    private Point depot;

    private List<Point> group;

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
}
