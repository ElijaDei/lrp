package com.github.elijadei.lrp.data;

import com.github.elijadei.lrp.util.InputTxt;
import com.github.elijadei.lrp.util.NodeRouting;
import com.github.elijadei.lrp.util.RoutingModel;

import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Points2Dconverted implements DataProvider2D  {

    private String file;
    private Point2D coordinates;
    public Points2Dconverted(String file) {
        this.file=file;
    }


    @Override
    public List<Point2D> getPoints2D() {
        List<Point2D> points = new ArrayList<>();
        try {
            RoutingModel routingModel = InputTxt.loadLR(this.file);
            points = Arrays.stream(routingModel.nodes)
                    .map(this::coordinates)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            System.out.println("Cant load file " + file + " " + e.getMessage());
        }
        return points;
    }

    public Point2D coordinates(NodeRouting node) {
        Point2D coordinates=new Point2D.Double(node.x,node.y);

        return coordinates;
    }


}
