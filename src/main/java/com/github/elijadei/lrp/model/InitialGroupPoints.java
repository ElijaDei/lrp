package com.github.elijadei.lrp.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InitialGroupPoints {

    private List<Point> collapsedPoints;
    private List<List<Point>> initialPoints;

    public void setCollapsedPoints(List<Point> collapsedPoints) {
        this.collapsedPoints = collapsedPoints;
    }

    public void setInitialPoints(List<List<Point>> initialPoints) {
        this.initialPoints = initialPoints;
    }

    public List<Point> getCollapsedPoints() {
        return collapsedPoints;
    }

    public List<List<Point>> getInitialPoints() {
        return initialPoints;
    }

    public InitialGroupPoints(List<Point> collapsedPoints, List<List<Point>> initialPoints) {
        this.collapsedPoints = collapsedPoints;
        this.initialPoints = initialPoints;
    }



}
