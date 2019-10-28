package com.github.elijadei.lrp.implementations;

import com.github.elijadei.lrp.intefaces.SuperClusterInterface;
import com.github.elijadei.lrp.model.Point;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.util.MathArrays;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SuperClusterer implements SuperClusterInterface {

    // That is test parameter, by default is 2;
    private Integer complexity = 2;
    private Double collapseRadius;
    private Integer maxDemand;
    private List<Point> points;

    public SuperClusterer(Double collapseRadius, List<Point> points) {
        this.collapseRadius = collapseRadius;
        this.points = points;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }

    public void setMaxDemand(Integer maxDemand) {
        this.maxDemand = maxDemand;
    }

    @Override
    public List<Point> minimizePoints() {
        List<Point> result = new ArrayList<>();
        while (!points.isEmpty()) {
            Point startPoint = points.remove(0);
            List<Point> nearest = findNearest(startPoint);
            filterByDemand(nearest, startPoint);
            if (!nearest.isEmpty()) {
                points.removeAll(nearest);
                nearest.add(startPoint);
                KMeansPlusPlusClusterer<Point> clusterer = new KMeansPlusPlusClusterer<>(1);
                List<CentroidCluster<Point>> cluster = clusterer.cluster(nearest);
                Point point = Point.fromMathPoint(cluster.get(0).getCenter().getPoint());
                point.setDemand(nearest.stream().map(Point::getDemand).reduce(Integer::sum).get());
                result.add(point);
            } else {
                result.add(startPoint);
            }
        }
        return result;
    }

    private void filterByDemand(List<Point> nearest, Point startPoint) {
        if (maxDemand != null && !nearest.isEmpty()) {
            while (calculateDemandSum(nearest) + startPoint.getDemand() > maxDemand) {
                if (nearest.isEmpty()) return;
                nearest.remove(findFurthestOne(nearest, startPoint));
            }
        }
    }

    private Integer calculateDemandSum(List<Point> points) {
        return points.isEmpty() ? 0 : points.stream().map(Point::getDemand).reduce(Integer::sum).get();
    }

    private List<Point> findNearest(Point point) {
        return points.stream()
                .filter(p -> MathArrays.distance(point.getPoint(), p.getPoint()) <= collapseRadius)
                .sorted((p1, p2) -> comparePoints(point, p1, p2))
                .limit(complexity)
                .collect(Collectors.toList());
    }

    private Point findFurthestOne(List<Point> points, Point point) {
        return points.stream()
                .sorted((p1, p2) -> comparePoints(point, p2, p1))
                .findFirst().get();
    }

    private int comparePoints(Point main, Point p1, Point p2) {
        double p1Distance = MathArrays.distance(main.getPoint(), p1.getPoint());
        double p2Distance = MathArrays.distance(main.getPoint(), p2.getPoint());
        return Double.compare(p1Distance, p2Distance);
    }

}
