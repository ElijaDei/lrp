package com.github.elijadei.lrp;

import org.apache.commons.math3.ml.clustering.Clusterable;

import javax.xml.bind.annotation.XmlAttribute;

public class Node implements Clusterable {

    @XmlAttribute(name = "id")
    private Integer id;

    @XmlAttribute(name = "x")
    private Double x;

    @XmlAttribute(name = "y")
    private Double y;

    @XmlAttribute(name = "type")
    private Integer type;

    public Node() {
    }

    public Node(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public double[] getPoint() {
        return new double[]{x, y};
    }

    public static Node fromPoint(double[] point) {
       return new Node(point[0], point[1]);
    }

    @Override
    public String toString() {
        return x + " " + y;

    }
}
