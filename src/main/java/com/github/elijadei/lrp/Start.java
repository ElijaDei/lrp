package com.github.elijadei.lrp;

import com.github.elijadei.lrp.clusterizing.Clusterizer;
import com.github.elijadei.lrp.util.FileUtil;
import com.github.elijadei.lrp.util.RandomSampling;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.util.List;

public class Start {
    public static void main(String[] args) throws JAXBException {
        String file = "input/network.xml";
        Nodes nodeList = FileUtil.readXmlFile(file);
        RandomSampling random = new RandomSampling(nodeList);
        List<Node> nodeSample = random.makeSamples();
        Clusterizer clusterizer = new Clusterizer(nodeSample, nodeList.getNodes().size());

        List<Nodes> clusteredNodes = clusterizer.getClusteredNodes();
        Plot2DPanel plot = new Plot2DPanel();
        clusteredNodes.forEach(nodes -> {
            Node node = nodes.getNode();
            // add a line plot to the PlotPanel
            plot.addScatterPlot("center point", Color.RED , new double[][]{node.getPoint()} );
            for (int i = 0; i <nodes.getNodes().size() ; i++) {
                Node node1 = nodes.getNodes().get(i);
                plot.addScatterPlot("center point", new Color(i, i + 5, i + 10) , new double[][]{node1.getPoint()});
            }
        });

        // put the PlotPanel in a JFrame, as a JPanel
        JFrame frame = new JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setVisible(true);

    }
}

