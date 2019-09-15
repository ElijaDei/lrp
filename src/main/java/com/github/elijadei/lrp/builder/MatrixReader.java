package com.github.elijadei.lrp.builder;

import com.github.elijadei.lrp.Node;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

public class MatrixReader {
    Node node;
    List<Node> nodes;
    int Id = 1;

    public MatrixReader(Node node, List<Node> nodes, int i) throws FileNotFoundException {
        this.node = node;
        this.nodes = nodes;
        this.Id = i;
    }


    public void matrixReader(double xc, double yc) throws FileNotFoundException {

        xc = node.getX();
        yc = node.getY();




    }
}
