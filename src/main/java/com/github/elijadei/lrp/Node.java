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

    public boolean doesItFeet(double[] point) {
        if (point.length == 2) {
            return x.equals(point[0]) && y.equals(point[1]);
        } else {
            throw new RuntimeException("Point is wrong");
        }
    }
}
