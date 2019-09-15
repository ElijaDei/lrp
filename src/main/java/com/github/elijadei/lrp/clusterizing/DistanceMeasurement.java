package com.github.elijadei.lrp.clusterizing;

import com.github.elijadei.lrp.Node;
import com.github.elijadei.lrp.Nodes;

import java.util.List;

public class DistanceMeasurement {

    public static void calculateDistance(List<Nodes> nodess) {
        for (Nodes nodes : nodess) {
            Node center = nodes.getCenter();
            for (Node node : nodes.getNodes()) {

//                node.setDistance();
            }

        }
    }
}
