package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.model.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GrouparizerTest {

    private static List<Point> POINTS = new ArrayList<>(Arrays.asList(
            new Point(1d,1d),
            new Point(1d,2d),
            new Point(2d,1d),
            new Point(3d,3d),
            new Point(1d,5d),
            new Point(4d,2d),
            new Point(2d,3d)
    )) ;

    @Test
    public void groupPointsByDistance() {
        List<List<Point>> groups = new Grouparizer(2, POINTS).groupPointsByDistance();
        assertEquals(groups.size(), 3);
    }
}