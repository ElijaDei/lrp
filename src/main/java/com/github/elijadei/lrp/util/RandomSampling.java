package com.github.elijadei.lrp.util;

import com.github.elijadei.lrp.model.XML_Nodes.Node;
import com.github.elijadei.lrp.model.XML_Nodes.Nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSampling {

    Nodes nodes ;
    List<Node> nodesCopy=new ArrayList<>();

    public RandomSampling(Nodes readXmlFile) {
        this.nodes=readXmlFile;
    }

    public List<Node> getNodesCopy() {
        return nodesCopy;
    }

    public List<Node> makeSamples() {

    List<Node> nodesList = nodes.getNodes();
    int listSize= (int) (20*Math.sqrt(nodesList.size()));
    System.out.println(listSize);
    Random rand = new Random();


    for (int i = 0; i <listSize; i++) {
        int randomIndex = rand.nextInt(nodesList.size());
        Node randomElement = nodesList.get(randomIndex);
        nodesCopy.add(randomElement);
        nodesList.remove(randomIndex);
    }
    return nodesCopy;
}



    }

