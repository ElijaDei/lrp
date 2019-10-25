package com.github.elijadei.lrp;

import com.github.elijadei.lrp.builder.Plotter;
import com.github.elijadei.lrp.intefaces.GroupingInterface;
import com.github.elijadei.lrp.intefaces.RoutingInterface;
import com.github.elijadei.lrp.intefaces.SuperClusterInterface;
import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.Point;

import java.util.List;

public class ProgramBuilder {
    private GroupingInterface grouperizer;
    private RoutingInterface router;
    private SuperClusterInterface superClusterer;

    public ProgramBuilder(GroupingInterface grouperizer,
                          RoutingInterface router, SuperClusterInterface superClusterer) {
        this.grouperizer = grouperizer;
        this.router = router;
        this.superClusterer = superClusterer;
    }

    public Double calcCost() {
        List<Point> points = superClusterer.minimizePoints();
        List<Group> groups = grouperizer.groupPoints(points);
        new Plotter(groups).plot();
        return router.calcRouteCost(groups);
    }

}
