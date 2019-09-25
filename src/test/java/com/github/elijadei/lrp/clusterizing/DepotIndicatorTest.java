package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.model.GroupWithDepot;
import com.github.elijadei.lrp.model.Point;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DepotIndicatorTest {

    private static List<List<Point>> GROUPS = Arrays.asList (
            Arrays.asList(new Point(1d,1d), new Point(1d,2d)),
            Arrays.asList(new Point(2d,1d), new Point(3d,3d)),
            Arrays.asList(new Point(4d,2d), new Point(2d,3d), new Point(5d,1d))
    );

    @Test
    public void findDepots() {
        DepotIndicator depotIndicator = new DepotIndicator(GROUPS);
        List<GroupWithDepot> groupWithDepot = depotIndicator.groupWithDepot();
        groupWithDepot.forEach(group -> Assert.assertNotNull(group.getDepot()));
    }
}