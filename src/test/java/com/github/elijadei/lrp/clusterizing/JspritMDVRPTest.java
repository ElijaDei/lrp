package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.implementations.RoutingInterfaceImpl;
import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JspritMDVRPTest {
    private static List<Point> POINTS = new ArrayList<>(Arrays.asList(
            new Point(34.5793,67.3827),
            new Point(88.6644,77.5776),
            new Point(82.0941,56.9276),
            new Point(21.6825,38.0499),
            new Point(3.32826,87.8929),
            new Point(24.1258,84.8492),
            new Point(31.6377,30.5457)
    )) ;

    private static List<Point> POINTS2 = new ArrayList<>(Arrays.asList(
            new Point(38.0951,72.6843),
            new Point(11.1993,6.89077),
            new Point(86.5005,74.1122),
            new Point(46.4449,58.0235),
            new Point(60.6455,29.2618),
            new Point(89.126,97.9663),
            new Point(18.1384,93.273)
    )) ;

    private static List<Group> groups = new ArrayList<>(Arrays.asList(
            new Group(new Point (45.53,42.22),POINTS),
            new Group(new Point (42.13,43.22),POINTS2)));



    @Test
    public void RoutingImpl() {
        RoutingInterfaceImpl impliment = new RoutingInterfaceImpl();
        Double cost = impliment.calcRouteCost(groups);
System.out.println(cost);
    }

}
