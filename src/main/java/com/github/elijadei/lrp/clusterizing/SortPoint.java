package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.model.Point;
import smile.neighbor.KDTree;

import java.util.List;

public class SortPoint {

    private List<Point> points;

    public SortPoint(List<Point> points) {
        this.points = points;
    }

    public KDTree<Point> sort() {
        double[][] keys = new double[points.size()][2];
        Point[] data = new Point[points.size()];
        for (int i = 0; i < points.size(); i++) {
            data[i] = points.get(i);
        }

        for (int i = 0; i < data.length; i++) {
            keys[i] = coordinates(data[i]);
        }
        return new KDTree<>(keys, data);
    }

    private double[] coordinates(Point Point) {
        return new double[]{Point.getX(), Point.getY()};
    }

}
