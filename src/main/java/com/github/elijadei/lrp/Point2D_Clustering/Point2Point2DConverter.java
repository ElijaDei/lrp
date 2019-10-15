package com.github.elijadei.lrp.Point2D_Clustering;

import com.github.elijadei.lrp.model.Point;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.stream.Collectors;

public class Point2Point2DConverter {

    public static List<Point2D> getCoordinates( List<Point> points ) {
        return points.stream()
                .map(point -> new Point2D.Double(point.getX(), point.getY()))
                .collect(Collectors.toList());
    }


    public static List<Point> getPoints(List<Point2D> list ) {
        return list.stream()
                .map(point -> new Point(point.getX(), point.getY()))
                .collect(Collectors.toList());
    }

    public static List<Point> getPointsDouble(List<Point2D.Double> listDouble ) {
        return listDouble.stream()
                .map(point -> new Point(point.getX(), point.getY()))
                .collect(Collectors.toList());
    }

}
