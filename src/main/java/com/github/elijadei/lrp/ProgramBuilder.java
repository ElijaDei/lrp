package com.github.elijadei.lrp;

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

    public Integer letsDoIt() {
        List<Point> points = superClusterer.minimizePoints();
        List<Group> groups = grouperizer.groupPoints(points);
        return router.buildRoutes(groups);
    }
}
