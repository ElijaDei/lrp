package com.github.elijadei.lrp;

import com.github.elijadei.lrp.builder.TSP;
import com.github.elijadei.lrp.clusterizing.Clusterizer;
import com.github.elijadei.lrp.clusterizing.Grouparizer;
import com.github.elijadei.lrp.model.Node;
import com.github.elijadei.lrp.model.Nodes;
import com.github.elijadei.lrp.util.FileUtil;
import com.github.elijadei.lrp.util.RandomSampling;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

public class Start {
    public static void main(String[] args) throws JAXBException, IOException {
        String file = "input/network.xml";
        Nodes nodeList = FileUtil.readXmlFile(file);
        RandomSampling random = new RandomSampling(nodeList);
        List<Node> nodeSample = random.makeSamples();
        Clusterizer clusterizer = new Clusterizer(nodeSample, nodeList.getNodes().size());
       // DBSCAN clusterizer = new DBSCAN(nodeSample, nodeList.getNodes().size());
/*
        RouteBuilder rb =new RouteBuilder("input/p11a.txt");
        long lon =System.currentTimeMillis();
        rb.buildRoute();
        long after=System.currentTimeMillis();
        System.out.println(after-lon);
*/
        PrintWriter cntr = new PrintWriter("cntr.txt");

        List<Nodes> clusteredNodes = clusterizer.getClusteredNodes();

        for (Nodes nds : clusteredNodes) {
            cntr.println(nds.getCenter());
        }
//toString().replace("[", "").replace("]", "") + " , " + nds.getNodes().toString().replace("[", "").replace("]", "")

        Plot2DPanel plot = new Plot2DPanel();
        Random rand = new Random();

        for (int i = 0; i < nodeList.getNodes().size(); i++) {
            Node node1 = nodeList.getNodes().get(i);
            plot.addScatterPlot("point", Color.LIGHT_GRAY, new double[][]{node1.getPoint()});
        }

        clusteredNodes.forEach(nodes -> {
            Node node = nodes.getCenter();
            TSP tsp;

            tsp = new TSP(node, nodes.getNodes());
            try {
                tsp.tspBuild();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



            // add a line plot to the PlotPanel
            plot.addScatterPlot("center point", Color.RED, new double[][]{node.getPoint()});
            int randoms = rand.nextInt(255);
            int randoms2 = rand.nextInt(255);
            int randoms3 = rand.nextInt(255);
            System.out.println(clusteredNodes.size());

            for (int j = 0; j < nodes.getNodes().size(); j++) {
                Node node1 = nodes.getNodes().get(j);

                plot.addScatterPlot("point", new Color(randoms, randoms3, randoms2), new double[][]{node1.getPoint()});
            }


        });


        // put the PlotPanel in a JFrame, as a JPanel
        JFrame frame = new JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setVisible(true);


    }
}

