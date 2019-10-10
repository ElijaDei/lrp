package com.github.elijadei.lrp.clusterizing;

import org.junit.Test;
import smile.neighbor.KDTree;
import smile.neighbor.Neighbor;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class SortPointTest {

    private static List<Point2D> POINTS = new ArrayList<>(Arrays.asList(
            new Point2D.Double(1d,1d),
            new Point2D.Double(1d,2d),
            new Point2D.Double(2d,1d),
            new Point2D.Double(3d,3d),
            new Point2D.Double(1d,5d),
            new Point2D.Double(4d,2d),
            new Point2D.Double(2d,3d)
    )) ;

    @Test
    public void sort() {
        SortPoint sortPoint = new SortPoint(POINTS);
        KDTree<Point2D> sort = sortPoint.sort();
        Neighbor<double[], Point2D> nearest = sort.nearest(new double[]{2d, 5d});
        assertArrayEquals(new double[]{1d, 5d}, nearest.key, 0.0);
    }
}