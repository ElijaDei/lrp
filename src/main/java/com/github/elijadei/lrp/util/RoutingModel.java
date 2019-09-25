package com.github.elijadei.lrp.util;

import java.util.ArrayList;

public class RoutingModel {

    //model parameters
    public int customers;

    //model variables
    public NodeRouting[] nodes;
    public ArrayList<Route> routes;

    //LRP
    public NodeRouting[] possibleDepots;

    public RoutingModel(int customers) {
        this.customers = customers;
        nodes = new NodeRouting[customers];
        routes = new ArrayList<>();
    }

}



