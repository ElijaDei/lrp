package com.github.elijadei.lrp;

import com.github.elijadei.lrp.builder.Plotter;
import com.github.elijadei.lrp.clusterizing.DepotIndicator;
import com.github.elijadei.lrp.clusterizing.Grouparizer;
import com.github.elijadei.lrp.data.DataProvider;
import com.github.elijadei.lrp.data.FileDataProvider;
import com.github.elijadei.lrp.model.GroupWithDepot;
import com.github.elijadei.lrp.model.Point;

import java.util.List;
import java.util.stream.Collectors;

public class TestStart {

    public static void main(String[] args) {
        DataProvider dataProvider = new FileDataProvider("input/r5000_2_test.dat");

        //grouping
        Grouparizer grouparizer = new Grouparizer(10, dataProvider.getPoints());
        List<List<Point>> lists = grouparizer.groupPointsByDistance();

        //find depots
        DepotIndicator depotIndicator = new DepotIndicator(lists);
        List<GroupWithDepot> groupWithDepots = depotIndicator.groupWithDepot();

        grouparizer = new Grouparizer(10, dataProvider.getPoints());
        lists = grouparizer.regroup(getCenters(groupWithDepots));
        depotIndicator = new DepotIndicator(lists);

        new Plotter(depotIndicator.groupWithDepot()).plot();
    }

    private static List<Point> getCenters(List<GroupWithDepot> groupWithDepots) {
        return groupWithDepots.stream().map(GroupWithDepot::getDepot).collect(Collectors.toList());
    }
}


