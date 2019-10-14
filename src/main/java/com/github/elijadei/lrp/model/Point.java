package com.github.elijadei.lrp.model;

import org.apache.commons.math3.ml.clustering.Clusterable;

public class Point implements Clusterable {

    private Double x;

    private Double y;

    private Integer demand;

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



    @Override
    public double[] getPoint() {
        return new double[]{this.x, this.y};
    }


    public static Point fromMathPoint(double[] mathPoint) {
        return new Point(mathPoint[0], mathPoint[1]);
    }
}
