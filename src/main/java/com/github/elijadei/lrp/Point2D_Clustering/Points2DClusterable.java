package com.github.elijadei.lrp.Point2D_Clustering;
import com.github.elijadei.lrp.model.Point;
import org.apache.commons.math3.ml.clustering.Clusterable;

import java.awt.geom.Point2D;

public class Points2DClusterable implements Clusterable{

    private Point2D points;
    private Double x;
    private Double y;

    public Points2DClusterable(Point2D points) {
        this.points = points;
    }

    public Points2DClusterable(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX(Point2D.Double X) {
        this.x=X.getX();
        return this.x;
    }

    public Double getY(Point2D.Double Y) {
        this.y=Y.getY();
        return this.y;
    }


    @Override
    public double[] getPoint() {
        return new double[]{this.x , this.y};
    }
}
