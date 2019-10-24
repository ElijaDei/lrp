package com.github.elijadei.lrp;

import com.github.elijadei.lrp.data.DataProvider;
import com.github.elijadei.lrp.intefaces.GroupingInterface;
import com.github.elijadei.lrp.intefaces.RoutingInterface;
import com.github.elijadei.lrp.intefaces.SuperClusterInterface;
import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.Point;

import java.util.List;

public class ProgramBuilder {
    private DataProvider dataProvider;
    private GroupingInterface grouperizer;
    private RoutingInterface router;
    private SuperClusterInterface superClusterer;

    public ProgramBuilder(DataProvider dataProvider, GroupingInterface grouperizer,
                          RoutingInterface router, SuperClusterInterface superClusterer) {
        this.dataProvider = dataProvider;
        this.grouperizer = grouperizer;
        this.router = router;
        this.superClusterer = superClusterer;
    }

    /*
    public Integer letsDoIt() {
        List<Point> points = dataProvider.getPoints();
        points = superClusterer.minimizePoints(points);
        List<Group> groups = grouperizer.groupPoints(points);
        return router.buildRoutes(groups);
    }
    */

}
