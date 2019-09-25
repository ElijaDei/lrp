package com.github.elijadei.lrp.util;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class InputTxtTest {

    @Test
    public void loadLR() throws IOException {
        RoutingModel routingModel = InputTxt.loadLR("input/r5000_2_test.dat");
        NodeRouting[] nodes = routingModel.nodes;
        assertEquals(nodes.length, 5000);
    }
}