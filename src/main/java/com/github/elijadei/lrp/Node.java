package com.github.elijadei.lrp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


public class Node {
    @XmlAttribute(name = "id")
    private Integer id;
    @XmlAttribute(name = "x")
    private String x;
    @XmlAttribute(name = "y")
    private String y;
    @XmlAttribute(name = "type")
    private Integer type;

    public Node () {

    }


    public Integer getId() {
        return id;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public Integer getType() {
        return type;
    }
}
