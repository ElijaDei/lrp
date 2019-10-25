package com.github.elijadei.lrp.Point2D_Clustering;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class GrouparizerHC {

    private Integer minCountInGroup;
    private  List<Point2D.Double> centrs = new ArrayList<>();

    public List<Point2D.Double> getCentrs() {
        return centrs;
    }

    public GrouparizerHC(Integer minCountInGroup) {
        this.minCountInGroup = minCountInGroup;
    }

    //group coordinates
    public List<List<Point2D>> points2GroupsByDistance(List<Point2D> list) {
        List<List<Point2D>> groupedPoints = new ArrayList<>();

        while (!list.isEmpty()) {

            List<Point2D> group = new ArrayList<>();
            List<Point2D.Double> centroids = new ArrayList<>();

            //to avoid nullPoint Exception
            if (list.size() >= this.minCountInGroup) {

                while (group.size() <= this.minCountInGroup) {
//                    SortPoint sortPoint = new SortPoint(list);
//                    KDTree<Point2D> sortPoints = sortPoint.sort();
                    Point2D point = list.remove(0);
                    group.add(point);

                    //create centr with separate method and add to new Arrraylist centroid
                    Point2D.Double centr = centroid(group);
                    centroids.add(centr);

                    //find nearest points (ot n nearest points) to centroid and add these to the group
//                    Neighbor<double[], Point2D>[] pointNext = sortPoints.knn(new double[]{centr.getX(), centr.getY()}, minCountInGroup);
//                    for (Neighbor<double[], Point2D> nextPoints : pointNext) {
//                        group.add(nextPoints.value);
//                    }

                    list.removeAll(group);
                    group.forEach(a -> System.out.println(a));
                    groupedPoints.add(group);
                    //find only points in distance D

                    list.removeAll(group);
                }
            }
            //if the number in list < countInt to avoid OutOfBount_Exception
            else {
                list.clear();
                break;
            }
            // create centr list. but actually it sets only first point to centr-point
            this.centrs.addAll(centroids);
        }

        return groupedPoints;
    }


    //compute centroids
    public Point2D.Double centroid(List<Point2D> xy) {
        double sumX = 0;
        double sumY = 0;
        for (int i = 0; i < xy.size(); i++) {
            sumX += xy.get(i).getX();
        }
        for (int i = 0; i < xy.size(); i++) {
            sumY += xy.get(i).getY();
        }
        Point2D.Double centroid = new Point2D.Double();
        centroid.setLocation( sumX / xy.size(), sumY / xy.size());
        System.out.println(" centroid " + centroid);
        return centroid;

    }



}



