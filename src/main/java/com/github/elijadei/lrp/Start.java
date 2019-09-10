package com.github.elijadei.lrp;

import com.github.elijadei.lrp.util.FileUtil;
import com.github.elijadei.lrp.util.RandomSampling;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) throws JAXBException, IOException {
        //new Start().writeTxtFile("input/network.xml");

        // RouteBuilder routeBuilder = new RouteBuilder("C:\\Users\\dei\\IdeaProjects\\LRP1\\path.txt");
        // routeBuilder.buildRoute();

        String file = "input/network.xml";

        ArrayList<Nodes> list = new ArrayList<Nodes>();
        Nodes nodeList = FileUtil.readXmlFile(file);
        list.add(nodeList); //почему нодс?

        FileUtil.readXmlFile(file).getNodes();

        FileUtil.writeNodesTxt(nodeList);

        RandomSampling random = new RandomSampling(nodeList);

        List<Node> nodeSample=  random.makeSamples();

        System.out.println(nodeSample.size());

    }
}

