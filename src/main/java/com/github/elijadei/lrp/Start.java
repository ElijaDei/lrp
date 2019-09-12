package com.github.elijadei.lrp;

import com.github.elijadei.lrp.clusterizing.Clusterizer;
import com.github.elijadei.lrp.util.FileUtil;
import com.github.elijadei.lrp.util.RandomSampling;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) throws JAXBException {
        String file = "input/network.xml";
        Nodes nodeList = FileUtil.readXmlFile(file);
        RandomSampling random = new RandomSampling(nodeList);
        List<Node> nodeSample = random.makeSamples();
        Clusterizer clusterizer = new Clusterizer(nodeSample);
        List<Node> clusteredNodes = clusterizer.getClusteredNodes();
        System.out.println(clusteredNodes.size());
    }
}

