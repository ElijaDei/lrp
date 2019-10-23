package com.github.elijadei.lrp.Point2D_Clustering;

import java.awt.*;
import java.awt.geom.Point2D;

public class Point2DExtended extends Point2D.Double {

    private Integer demand;


    public Integer getDemand() {
        return demand;
    }

    public void setDemand(Integer demand) {
        this.demand = demand;
    }

    public Point2DExtended(double x, double y, int demand){
        this.x=x;
        this.y=y;
        this.demand=demand;

    }

}
