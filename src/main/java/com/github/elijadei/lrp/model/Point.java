package com.github.elijadei.lrp.model;

import org.apache.commons.math3.ml.clustering.Clusterable;

import java.util.ArrayList;
import java.util.List;

public class Point implements Clusterable {

    private Double x;

    private Double y;

    private Integer demand;

    private List<Point> initialPoints = new ArrayList<>();

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Double x, Double y, Integer demand) {
        this.x = x;
        this.y = y;
        this.demand = demand;
    }


    public Integer getDemand() {
        return demand;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setDemand(Integer demand) {
        this.demand = demand;
    }

    public List<Point> getInitialPoints() {
        return initialPoints;
    }

    public void setInitialPoints(List<Point> initialPoints) {
        this.initialPoints = initialPoints;
    }


    @Override
    public double[] getPoint() {
        return new double[]{this.x, this.y};
    }


    public static Point fromMathPoint(double[] mathPoint) {
        return new Point(mathPoint[0], mathPoint[1]);
    }
}
