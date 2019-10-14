package com.github.elijadei.lrp.clusterizing;

import smile.neighbor.KDTree;

import java.awt.geom.Point2D;
import java.util.List;

public class SortPoint {

    private List<Point2D> points;

    public SortPoint(List<Point2D> points) {
        this.points = points;
    }

    public KDTree<Point2D> sort() {
        double[][] keys = new double[points.size()][2];
        Point2D[] data = new Point2D[points.size()];
        for (int i = 0; i < points.size(); i++) {
            data[i] = points.get(i);
        }

        for (int i = 0; i < data.length; i++) {
            keys[i] = coordinates(data[i]);
        }
        return new KDTree<>(keys, data);
    }

    private double[] coordinates(Point2D point2D) {
        return new double[]{point2D.getX(), point2D.getY()};
    }

}
