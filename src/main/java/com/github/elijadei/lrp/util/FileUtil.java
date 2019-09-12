package com.github.elijadei.lrp.util;

import com.github.elijadei.lrp.Node;
import com.github.elijadei.lrp.Nodes;
import org.apache.logging.log4j.core.util.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FileUtil {

    public static Nodes readXmlFile(String filename) throws JAXBException {
        File xmlFile = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(Nodes.class); //почему .класс там
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Nodes) jaxbUnmarshaller.unmarshal(xmlFile);
    }

    public static void writeNodesTxt(Nodes node) throws IOException {
        //FileWriter file = new FileWriter("output.txt");
        PrintWriter pw = new PrintWriter("path.txt");
        int count = 1;
        int a = new Random().nextInt(100);
        //todo
        List<Node> ll= node.getNodes();  //почему лист а не аррайлист и почему моделъ нод.гетнодс мы записываем ш нод лист?
        for(Node s : ll) {
            int r = new Random().nextInt(100);
            pw.println(count++ + " " +  s.getX() + " " + s.getY() + " " + 0 + " " + r + " " + 1 +" " + 2 +" " + 1 +" " + 2);
        }

        //Path out = Paths.get("path.txt");





        //for( n : node.getNodes(){

       // }
    }
    }

