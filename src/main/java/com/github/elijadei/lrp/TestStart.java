package com.github.elijadei.lrp;

import com.github.elijadei.lrp.builder.Plotter;
import com.github.elijadei.lrp.clusterizing.DepotIndicator;
import com.github.elijadei.lrp.clusterizing.Grouparizer;
import com.github.elijadei.lrp.clusterizing.Grouparizer2;
import com.github.elijadei.lrp.data.DataProvider;
import com.github.elijadei.lrp.data.DataProvider2D;
import com.github.elijadei.lrp.data.FileDataProvider;
import com.github.elijadei.lrp.data.Points2Dconverted;
import com.github.elijadei.lrp.model.GroupWithDepot;
import com.github.elijadei.lrp.model.Point;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.stream.Collectors;

public class TestStart {

    public static void main(String[] args) {
        DataProvider dataProvider = new FileDataProvider("input/r5000_2_test.dat");

        // Test Grouporizer2


        DataProvider2D dataProvider2 = new Points2Dconverted("input/r5000_2_test.dat");

        List<Point2D> points= dataProvider2.getPoints2D();
        //grouping
        Grouparizer grouparizer = new Grouparizer(10, dataProvider.getPoints());
        List<List<Point>> lists = grouparizer.groupPointsByDistance();

        //find depots
        DepotIndicator depotIndicator = new DepotIndicator(lists);
        List<GroupWithDepot> groupWithDepots = depotIndicator.groupWithDepot();

        grouparizer = new Grouparizer(6, dataProvider.getPoints());
        lists = grouparizer.regroup(getCenters(groupWithDepots));
        depotIndicator = new DepotIndicator(lists);

        new Plotter(depotIndicator.groupWithDepot()).plot();
    }

    private static List<Point> getCenters(List<GroupWithDepot> groupWithDepots) {
        return groupWithDepots.stream().map(GroupWithDepot::getDepot).collect(Collectors.toList());
    }



}


