package com.github.elijadei.lrp.data;

import com.github.elijadei.lrp.model.Point;
import com.github.elijadei.lrp.util.InputTxt;
import com.github.elijadei.lrp.util.NodeRouting;
import com.github.elijadei.lrp.util.RoutingModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileDataProvider implements DataProvider {

    private String file;

    public FileDataProvider(String file) {
        this.file = file;
    }

    @Override
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        try {
            RoutingModel routingModel = InputTxt.loadLR(this.file);
            points = Arrays.stream(routingModel.nodes)
                    .map(this::fromNodeRouting)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            System.out.println("Cant load file " + file + " " + e.getMessage());
        }
        return points;
    }

    private Point fromNodeRouting(NodeRouting node) {
        return new Point(node.x, node.y);
    }
}
