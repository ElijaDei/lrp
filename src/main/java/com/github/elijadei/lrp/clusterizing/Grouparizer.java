package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.model.Point;
import org.apache.commons.math3.util.MathArrays;

import java.util.*;
import java.util.stream.Collectors;


public class Grouparizer {

    private Integer minCountInGroup;

    private List<Point> points;

    public Grouparizer(Integer minCountInGroup, List<Point> points) {
        this.minCountInGroup = minCountInGroup;
        this.points = points;
    }

    public List<List<Point>> groupPointsByDistance() {
        List<List<Point>> groupedPoints = new ArrayList<>();
        while (!this.points.isEmpty()) {
            List<Point> group;
            if (this.points.size() >= this.minCountInGroup) {
                Point mainPoint = this.points.remove(0);
                group = findNearest(mainPoint);
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

    private List<Point> findNearest(Point point) {
        return points.stream()
                .sorted((p1, p2) -> comparePoints(point, p1, p2))
                .limit(this.minCountInGroup - 1)
                .collect(Collectors.toList());

    }

    private int comparePoints(Point main, Point p1, Point p2) {
        double p1Distance = MathArrays.distance(main.getPoint(), p1.getPoint());
        double p2Distance = MathArrays.distance(main.getPoint(), p2.getPoint());
        return Double.compare(p1Distance, p2Distance);
    }

}
