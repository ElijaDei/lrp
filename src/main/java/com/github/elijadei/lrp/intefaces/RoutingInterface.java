package com.github.elijadei.lrp.intefaces;

import com.github.elijadei.lrp.model.Group;

import java.util.List;

public interface RoutingInterface {
    Double calcRouteCost(List<Group> pointGroups);
}
