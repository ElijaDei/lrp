package com.github.elijadei.lrp;

import com.github.elijadei.lrp.Point2D_Clustering.VRPJsprit;
import com.github.elijadei.lrp.builder.Plotter;
import com.github.elijadei.lrp.clusterizing.DepotIndicator;
import com.github.elijadei.lrp.clusterizing.Grouparizer;
import com.github.elijadei.lrp.data.DataProvider;
import com.github.elijadei.lrp.data.FileDataProvider;
import com.github.elijadei.lrp.model.GroupWithDepot;
import com.github.elijadei.lrp.model.Point;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class TestStart {

    public static void main(String[] args) throws FileNotFoundException {
        DataProvider dataProvider = new FileDataProvider("input/r5000_2_test.dat");

        //grouping
        Grouparizer grouparizer = new Grouparizer(500, dataProvider.getPoints());
        List<List<Point>> lists = grouparizer.groupPointsByDistance();

        //find depots
        DepotIndicator depotIndicator = new DepotIndicator(lists);

        //depot with x and y  and group with size
        List<GroupWithDepot> groupWithDepots = depotIndicator.groupWithDepot();

        //List<List<Point>> lists2 = grouparizer.regroup(getCenters(groupWithDepots));
        //depotIndicator = new DepotIndicator(lists2);

        new Plotter(depotIndicator.groupWithDepot()).plot();

        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output500.txt"))));

        groupWithDepots.forEach(points ->
        {
            Point depot = points.getDepot();
            VRPJsprit tsp;
            tsp = new VRPJsprit(depot, points.getGroup());
            tsp.tspBuild();
        });
    }
}


