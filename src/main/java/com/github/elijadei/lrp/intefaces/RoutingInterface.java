package com.github.elijadei.lrp.intefaces;

import com.github.elijadei.lrp.model.Depots;
import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.Point;

import java.util.List;

public interface RoutingInterface {
    Double buildRoutes(List<Group> pointGroups, List<Depots> Depots);

}
