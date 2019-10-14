package com.github.elijadei.lrp.Point2D_Clustering;

import com.github.elijadei.lrp.model.Point;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Point2Point2DConverter {

    private List<Point> points;
    private List<Point2D> list;
    private List<Point2D.Double> listDouble;




    public List<Point2D> getCoordinates( List<Point> points ) {
        this.list = points.stream()
                .map(point -> new Point2D.Double(point.getX(), point.getY()))
                .collect(Collectors.toList());
        return list;
    }


    public List<Point> getPoints(List<Point2D> list ) {
        this.points = list.stream()
                .map(point -> new Point(point.getX(), point.getY()))
                .collect(Collectors.toList());
        return points;
    }

    public List<Point> getPointsDouble(List<Point2D.Double> listDouble ) {
        this.points = listDouble.stream()
                .map(point -> new Point(point.getX(), point.getY()))
                .collect(Collectors.toList());
        return points;
    }

}
