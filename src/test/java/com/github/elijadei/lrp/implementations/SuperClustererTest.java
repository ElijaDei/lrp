package com.github.elijadei.lrp.implementations;

import com.github.elijadei.lrp.model.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SuperClustererTest {

    private static final List<Point> POINTS = new ArrayList<>(Arrays.asList(
            new Point(1d,1d, 2),
            new Point(1d,2d, 3),
            new Point(2d,1d, 1),
            new Point(3d,3d, 4),
            new Point(1d,5d, 2),
            new Point(4d,2d, 1),
            new Point(2d,3d, 3)
    )) ;

    @Test
    public void minimizePoints() {
        SuperClusterer superClusterer = new SuperClusterer(1d, POINTS);
        assertEquals(4, superClusterer.minimizePoints().size());
    }
}