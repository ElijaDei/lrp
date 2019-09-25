package com.github.elijadei.lrp.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class InputTxt {

    private static class Edge implements Comparable<Edge> {
        public double costs;
        public int node1;
        public int node2;

        public Edge(double v, int f, int t) {
            costs = v;
            node1 = f;
            node2 = t;
        }

        @Override
        public int compareTo(Edge o) {
            if (o.costs < this.costs)
                return -1;
            else if (o.costs == this.costs)
                return 0;
            else
                return 1;
        }
    }

    public static RoutingModel loadLR(String file) throws FileNotFoundException {
        try (Scanner input = new Scanner(new File(file)).useLocale(Locale.ENGLISH)) {
            int customers = input.nextInt();
            int num_facilities = input.nextInt();
            RoutingModel model = new RoutingModel(customers);
            model.nodes = new NodeRouting[model.customers];

            //read the location of the possible facilities
            model.possibleDepots = new NodeRouting[num_facilities];

            for (int i = 0; i < num_facilities; i++) {
                NodeRouting n = new NodeRouting(-1);
                n.x = input.nextDouble();
                n.y = input.nextDouble();
                model.possibleDepots[i] = n;
            }

            //read the location of the customers
            for (int i = 0; i < customers; i++) {
                NodeRouting n = new NodeRouting(i);
                n.x = input.nextDouble();
                n.y = input.nextDouble();
                model.nodes[i] = n;
            }

            //facility capacities
            for (int i = 0; i < num_facilities; i++)
                model.possibleDepots[i].demand = input.nextInt() * 100;//large
            //customer demand
            for (int i = 0; i < customers; i++)
                model.nodes[i].demand = (int) Math.round(input.nextDouble() * 100);//large

            //opening costs
            for (int i = 0; i < num_facilities; i++)
                model.possibleDepots[i].opening_costs = (float) input.nextDouble();

            //costs per route (not applicable in the very large instances)
            //model.cost_per_Route = 	input.nextDouble();

            //---------------------------------------------------------------------------------
            //compute the distances between all pairs of customers
            //---------------------------------------------------------------------------------
            for (int i = 0; i < model.customers; i++) {
                ArrayList<Edge> sortedDists = new ArrayList<>();
                for (int j = 0; j < model.customers; j++)
                    if (j != i) {
                        //float dist = (int) (0.5 + Math.sqrt( Math.pow( model.nodes[i].x - model.nodes[j].x , 2) + Math.pow( model.nodes[i].y - model.nodes[j].y , 2) ) );
                        float dist = (float) Math.sqrt(Math.pow(model.nodes[i].x - model.nodes[j].x, 2) + Math.pow(model.nodes[i].y - model.nodes[j].y, 2));
                        sortedDists.add(new Edge(dist, j, -1));
                    }

                //store the distances to the 100 nearest neighbours
                Collections.sort(sortedDists);
                Collections.reverse(sortedDists);
                for (int j = 0; j < Math.min(sortedDists.size(), 100); j++) {
                    model.nodes[i].distances_for_CW.put(sortedDists.get(j).node1, (float) sortedDists.get(j).costs);
                    model.nodes[i].distances.put(sortedDists.get(j).node1, (float) sortedDists.get(j).costs);
                }
            }
            return model;
        }
    }

}
