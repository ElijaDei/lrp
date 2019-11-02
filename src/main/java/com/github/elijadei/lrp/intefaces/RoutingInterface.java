package com.github.elijadei.lrp.intefaces;

import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.InitialGroupPoints;

import java.util.List;

public interface RoutingInterface {
    Double calcRouteCost(List<Group> pointGroups);
}
