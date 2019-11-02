package com.github.elijadei.lrp.implementations;

import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.InitialGroupPoints;
import com.github.elijadei.lrp.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class mergeCentersAndGroups {

    private List<Group> fromClusterizer;
    private InitialGroupPoints initialGroupPoints;

    public void fuseClusters(List<Group> fromClusterizer, InitialGroupPoints initialGroupPoints ){

        fromClusterizer.forEach(a->{
            List<Point> p=a.getGroup();
            int i=p.size();

            System.out.println(i);
            List<List<Point>> p1= initialGroupPoints.getInitialPoints().subList(0,i);
            List<Point> points=new ArrayList<>();
            for (List<Point> pp:p1) {
                points.addAll(pp);
            }

            p1.iterator().next().remove(0);

            a.setGroup(points);
        });

}

}
