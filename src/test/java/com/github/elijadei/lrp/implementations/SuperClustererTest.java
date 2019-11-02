package com.github.elijadei.lrp.implementations;

import com.github.elijadei.lrp.ProgramBuilder;
import com.github.elijadei.lrp.builder.Plotter;
import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.InitialGroupPoints;
import com.github.elijadei.lrp.model.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SuperClustererTest {

    private static final List<Point> POINTS = new ArrayList<>(Arrays.asList(
            new Point(1d,1d, 2),
            new Point(1d,2d, 1),
            new Point(2d,1d, 1),
            new Point(3d,3d, 1),
            new Point(1d,5d, 1),
            new Point(4d,2d, 1),
            new Point(2d,3d, 2)
    )) ;

    @Test
    public void minimizePoints() {
        SuperClusterer superClusterer = new SuperClusterer(2d, POINTS);
        superClusterer.setMaxDemand(3);
        List<Point> points= superClusterer.minimizePoints();
        Grouping groups = new Grouping(2);
        List<Group> group= groups.groupPoints(points);
        new Plotter(group).plot();

       // assertEquals(3, superClusterer.minimizePoints().size());
    }
}