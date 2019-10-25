package com.github.elijadei.lrp;

import com.github.elijadei.lrp.data.DataProvider;
import com.github.elijadei.lrp.data.FileDataProvider;
import com.github.elijadei.lrp.implementations.Grouping;
import com.github.elijadei.lrp.implementations.RoutingInterfaceImpl;
import com.github.elijadei.lrp.implementations.SuperClusterer;
import com.github.elijadei.lrp.model.Point;

import java.util.List;

public class Start {
    public static void main(String[] args) {
        DataProvider dataProvider = new FileDataProvider("input/r5000_2_test.dat");
        List<Point> points = dataProvider.getPoints();
        ProgramBuilder programBuilder = new ProgramBuilder(new Grouping(points.size() / 10),
                new RoutingInterfaceImpl(), new SuperClusterer(2d, points));
        System.out.println(programBuilder.calcCost());
    }
}

