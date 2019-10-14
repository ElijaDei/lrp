package com.github.elijadei.lrp;

import com.github.elijadei.lrp.Point2D_Clustering.DepotIndicator2D;
import com.github.elijadei.lrp.Point2D_Clustering.GrouparizerHC;
import com.github.elijadei.lrp.Point2D_Clustering.Point2Point2DConverter;
import com.github.elijadei.lrp.builder.Plotter;
import com.github.elijadei.lrp.clusterizing.DepotIndicator;
import com.github.elijadei.lrp.data.DataProvider;
import com.github.elijadei.lrp.data.FileDataProvider;
import com.github.elijadei.lrp.model.GroupWithDepot;
import com.github.elijadei.lrp.model.Point;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestStart2 {

    public static void main(String[] args) {

        DataProvider dataProvider = new FileDataProvider("input/r5000_2_test.dat");

        //grouping
        GrouparizerHC grouparizer = new GrouparizerHC(5, dataProvider.getPoints());

        Point2Point2DConverter converter= new  Point2Point2DConverter();
       List<Point2D> list= converter.getCoordinates(dataProvider.getPoints());

        List<List<Point2D>> punkts= grouparizer.points2GroupsByDistance(list);
        List<Point2D.Double> centrs= grouparizer.getCentrs();

        List<Point> converterPointsDouble=converter.getPointsDouble(centrs);

       DepotIndicator2D depotIndicator = new DepotIndicator2D(Collections.singletonList(converterPointsDouble));
        List<GroupWithDepot> groupWithDepots = depotIndicator.groupWithDepot();
        new Plotter(depotIndicator.groupWithDepot()).plot();

        System.out.println("centrs  "+centrs.size());
        System.out.println(Arrays.toString(punkts.get(1).toArray()));


    }
}