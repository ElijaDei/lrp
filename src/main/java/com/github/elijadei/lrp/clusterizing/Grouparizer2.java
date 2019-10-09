package com.github.elijadei.lrp.clusterizing;


import com.github.elijadei.lrp.model.Point;
import org.apache.commons.math3.util.MathArrays;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;


public class Grouparizer2 {

    private List<Point> points;
    private Integer minCountsPoints;
    private Integer minDemand;


    public Grouparizer2( Integer minCountsPoints,List<Point> points, Integer minDemand) {
        this.minCountsPoints = minCountsPoints;
        this.points = points;
        this.minDemand = minDemand;
    }



    //find random point
    public ArrayList<Point> randPoint(){
        ArrayList<Point> newPoint= new ArrayList<>();
        int newSeed =new Random().nextInt(this.points.size());
        newPoint.add(this.points.get(newSeed));
        return newPoint;
    }

    public List<List<Point>> regroup(List<Point> centers) {
        List<List<Point>> groups = new ArrayList<>();
        centers.forEach(center -> {
            List<Point> group = findNearest(center, this.minCountsPoints);
            points.removeAll(group);
            groups.add(group);
        });
        if (!points.isEmpty()) {
            addPointsToNearestGroup(groups);
            this.points.clear();
        }
        return groups;
    }

    public List<List<Point>> groupPointsByDistance() {
        List<List<Point>> groupedPoints = new ArrayList<>();
        Integer groupDemand=0;
        while (!this.points.isEmpty()) {
            List<Point> group;
            if (this.points.size() >= this.minCountsPoints || groupDemand<=this.minDemand) {
                Point mainPoint = this.points.remove(0); //randPoint?
                groupDemand=mainPoint.getDemand();
                group = findNearest(mainPoint, this.minCountsPoints - 1);
                this.points.removeAll(group);
                group.add(mainPoint);
                groupedPoints.add(group);
            } else {
                addPointsToNearestGroup(groupedPoints);
                this.points.clear();
            }
        }
        return groupedPoints;
    }

    private void addPointsToNearestGroup(List<List<Point>> groups) {
        points.forEach(point -> {
            HashMap<Double, List<Point>> distanceMap = new HashMap<>();
            groups.forEach(group -> {
                Point groupedPoint = group.get(0);
                distanceMap.put(MathArrays.distance(point.getPoint(), groupedPoint.getPoint()), group);
            });
            distanceMap.entrySet().stream()
                    .sorted(Comparator.comparingDouble(Map.Entry::getKey))
                    .findFirst()
                    .get()
                    .getValue().add(point);
        });
    }


    private List<Point> findNearest(Point point, Integer count) {
        return points.stream()
                .sorted((p1, p2) -> comparePoints(point, p1, p2))

                .limit(count)
                .collect(Collectors.toList());
    }


    private int comparePoints(Point main, Point p1, Point p2) {
        double p1Distance = MathArrays.distance(main.getPoint(), p1.getPoint());
        double p2Distance = MathArrays.distance(main.getPoint(), p2.getPoint());
        return Double.compare(p1Distance, p2Distance);
    }

}




