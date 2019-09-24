package com.github.elijadei.lrp.util;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class InputTxt {

    private static class Edge implements Comparable<Edge>
    {
        public double costs;
        public int node1;
        public int node2;

        public Edge(double v, int f, int t)
        {
            costs = v;
            node1 = f;
            node2 = t;
        }

        @Override
        public int compareTo(Edge o)
        {
            if(o.costs<this.costs)
                return -1;
            else if(o.costs == this.costs)
                return 0;
            else
                return 1;
        }
    }

    public static Routing_Model loadLR(String file) throws IOException
    {
        try (@SuppressWarnings("resource")
             Scanner input = new Scanner(new File(file)).useLocale(Locale.ENGLISH))
        {
            int customers = input.nextInt();
            int num_facilities = input.nextInt();
            Routing_Model model = new Routing_Model(customers, 10,1);
            model.distances_rounded = false;
            model.maxTourLength = Double.MAX_VALUE;
            model.nodes 	= new NodeRouting[model.customers];

            //read the location of the possible facilities
            model.possibleDepots= new NodeRouting[num_facilities];

            for (int i=0; i<num_facilities; i++)
            {
                NodeRouting n 	= new NodeRouting(-1);
                n.x		= input.nextDouble();
                n.y		= input.nextDouble();
                model.possibleDepots[i] = n;
            }

            //read the location of the customers
            for (int i=0; i<customers; i++)
            {
                NodeRouting n 	= new NodeRouting(i);
                n.x		= input.nextDouble();
                n.y		= input.nextDouble();
                model.nodes[i]	= n;
            }

            //capacities (everything is multiplied with 100 so that demand can be expresses as integers)
            model.capacityLimitVehicle = input.nextInt() *100;//large

            //facility capacities
            for (int i=0; i<num_facilities; i++)
                model.possibleDepots[i].demand = input.nextInt()*100;//large
            //customer demand
            for (int i=0; i<customers; i++)
                model.nodes[i].demand = (int) Math.round( input.nextDouble()*100 );//large

            //opening costs
            for (int i=0; i<num_facilities; i++)
                model.possibleDepots[i].opening_costs = (float) input.nextDouble();

            //costs per route (not applicable in the very large instances)
            //model.cost_per_Route = 	input.nextDouble();

            //---------------------------------------------------------------------------------
            //compute the distances between all pairs of customers
            //---------------------------------------------------------------------------------
            for(int i=0; i<model.customers; i++)
            {
                ArrayList<Edge> sortedDists = new ArrayList<Edge>();
                for(int j=0; j<model.customers ; j++)
                    if ( j != i )
                    {
                        //float dist = (int) (0.5 + Math.sqrt( Math.pow( model.nodes[i].x - model.nodes[j].x , 2) + Math.pow( model.nodes[i].y - model.nodes[j].y , 2) ) );
                        float dist = (float) Math.sqrt( Math.pow( model.nodes[i].x - model.nodes[j].x , 2) + Math.pow( model.nodes[i].y - model.nodes[j].y , 2) );
                        dist = (float) (dist);
                        sortedDists.add( new Edge(dist,j,-1) );
                    }

                //store the distances to the 100 nearest neighbours
                Collections.sort(sortedDists);
                Collections.reverse(sortedDists);
                for (int j=0; j<Math.min(sortedDists.size(), 100); j++)
                {
                    model.nodes[i].distances_for_CW.put(sortedDists.get(j).node1, (float) sortedDists.get(j).costs);
                    model.nodes[i].distances.put(sortedDists.get(j).node1, (float) sortedDists.get(j).costs);
                }
            }
            return model;
        }
    }


    public static Routing_Model addDepots(Routing_Model model, ArrayList<Integer> open_depots)
    {
        //create a new Routing_Model with the open depots
        model.routes.clear();
        Routing_Model model_depots = model;//.copy();
        model_depots.depots 	= open_depots.size();
        model_depots.vertices 	= model_depots.customers + model_depots.depots;

        NodeRouting[] nodes_new = new NodeRouting[model_depots.vertices];
        //add the customers to the new model
        for (int i=0; i<model_depots.customers; i++)
            nodes_new[i] = model_depots.nodes[i];//.copy();
        //add all open depots
        for (int i=0; i<model_depots.depots; i++)
        {
            nodes_new[i+model_depots.customers] 		= model.possibleDepots[open_depots.get(i)].copy();
            nodes_new[i+model_depots.customers].demand  = 0;
            nodes_new[i+model_depots.customers].index 	= i+model_depots.customers;
        }
        model_depots.nodes = nodes_new;

        //compute the distances (only from customers to the open depots - the other distances have already been computed)
        for (int i=0; i<model_depots.customers; i++)
        {
            for(int j=model_depots.customers; j<model_depots.vertices; j++)
            {
                //float dist = (int) (0.5 + Math.sqrt( Math.pow( model_depots.nodes[i].x - model_depots.nodes[j].x , 2) + Math.pow( model_depots.nodes[i].y - model_depots.nodes[j].y , 2) ) );
                float dist = (float) Math.sqrt( Math.pow( model_depots.nodes[i].x - model_depots.nodes[j].x , 2) + Math.pow( model_depots.nodes[i].y - model_depots.nodes[j].y , 2) );
                model_depots.nodes[i].distances.put(j, (float) dist);
            }
        }
        return model_depots;
    }

}
