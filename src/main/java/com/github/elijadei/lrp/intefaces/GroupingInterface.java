package com.github.elijadei.lrp.intefaces;

import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.Point;

import java.util.List;

public interface GroupingInterface {

    List<Group> groupPoints(List<Point> point);
}
