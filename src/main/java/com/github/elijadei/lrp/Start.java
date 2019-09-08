package com.github.elijadei.lrp;

import com.github.elijadei.lrp.builder.RouteBuilder;
import com.github.elijadei.lrp.util.FileUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Start {
    public static void main(String[] args) throws JAXBException, IOException {
        //new Start().writeTxtFile("input/network.xml");

         RouteBuilder routeBuilder = new RouteBuilder("C:\\Users\\dei\\IdeaProjects\\LRP1\\path.txt");
          routeBuilder.buildRoute();

        //ArrayList<Nodes> list = new ArrayList<Nodes>();
        //list.add((Nodes) FileUtil.readXmlFile("input/network.xml")); //почему нодс?
        //FileUtil.readXmlFile("input/network.xml").getNodes();
          //  System.out.println( );



            FileUtil.writeNodesTxt(FileUtil.readXmlFile("input/network.xml"));


    }
}

