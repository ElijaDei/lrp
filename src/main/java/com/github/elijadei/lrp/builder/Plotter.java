package com.github.elijadei.lrp.builder;

import com.github.elijadei.lrp.model.GroupWithDepot;
import com.github.elijadei.lrp.model.Point;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Plotter {

    private List<GroupWithDepot> groupWithDepots;

    public Plotter(List<GroupWithDepot> groupWithDepots) {
        this.groupWithDepots = groupWithDepots;
    }

    public void plot() {
        Plot2DPanel plot = new Plot2DPanel();
        Random rand = new Random();

        groupWithDepots.forEach(nodes -> {

            // add a line plot to the PlotPanel
            plot.addScatterPlot("center point", Color.RED, new double[][]{nodes.getDepot().getPoint()});

            int randoms = rand.nextInt(100);
            int randoms2 = rand.nextInt(255);
            int randoms3 = rand.nextInt(255);

            for (int j = 0; j < nodes.getGroup().size(); j++) {
                Point point = nodes.getGroup().get(j);
                plot.addScatterPlot("point", new Color(randoms, randoms3, randoms2), new double[][]{point.getPoint()});
            }
        });

        // put the PlotPanel in a JFrame, as a JPanel
        JFrame frame = new JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setVisible(true);
    }


}
