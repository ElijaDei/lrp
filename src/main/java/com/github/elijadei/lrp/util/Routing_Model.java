package com.github.elijadei.lrp.util;
import java.util.ArrayList;
public class Routing_Model {

        //model parameters
        public int customers;
        public int depots;
        public int vertices;
        public int vehicles;
        public int capacityLimitVehicle;
        public double maxTourLength;
        public boolean distances_rounded;

        //model variables
        public NodeRouting[] nodes;
        public ArrayList<Route> routes;

        //LRP
        public NodeRouting[] possibleDepots;


        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        //FUNCTION : Constructor
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        public Routing_Model(int customers, int depots, int products)
        {
            this.customers 		= customers;
            this.depots    		= depots;
            this.vertices  		= customers + depots;
            nodes 				= new NodeRouting[vertices];
            routes 				= new ArrayList<Route>();
        }

        public float getDistance(int n1, int n2)
        {
            if (nodes[n1].distances.containsKey(n2))
                return nodes[n1].distances.get(n2);
            else if (nodes[n2].distances.containsKey(n1))
                return nodes[n2].distances.get(n1);
            else
            {
                //System.out.println("A requested distance value is not stored");
                float dist = 0;
                if (distances_rounded)
                    dist = (int) (0.5 + Math.sqrt( Math.pow( nodes[n1].x - nodes[n2].x , 2) + Math.pow( nodes[n1].y - nodes[n2].y , 2) ) );
                else
                    dist = (float) Math.sqrt( Math.pow( nodes[n1].x - nodes[n2].x , 2) + Math.pow( nodes[n1].y - nodes[n2].y , 2) );
                return dist;
            }
        }

        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        //FUNCTION : Compute the costs of the model
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        public double computeSolutionCosts()
        {
            //distances
            double totalCost = 0;
            for (int r=0; r<routes.size(); r++)
                if (routes.get(r).length > 0)
                    for (int i=1; i<routes.get(r).length+2; i++)
                        totalCost += getDistance(routes.get(r).nodes.get(i-1).index, routes.get(r).nodes.get(i).index);

            //opening
            for (int i=customers; i<vertices; i++)
                totalCost += nodes[i].opening_costs;
            return totalCost;
        }

        public boolean checkSolution()
        {
            boolean validSolution = true;
            boolean visited[] = new boolean[customers+1];
            for (int r=0; r<routes.size(); r++)
            {
                int load = 0;
                for (int i=1; i<routes.get(r).nodes.size()-1; i++)
                {
                    load 		+= routes.get(r).nodes.get(i).demand;
                    if (visited[routes.get(r).nodes.get(i).index])
                        System.out.println("ERROR : Customer assigned twice - " + routes.get(r).nodes.get(i).index);
                    visited[routes.get(r).nodes.get(i).index]	=	true;
                    if ( routes.get(r).nodes.get(i).routePosition != i  )
                        System.out.println("ERROR : route position wrong " + routes.get(r).nodes.get(i).index);
                    if ( routes.get(r).nodes.get(i).routeIndex != r  )
                        System.out.println("ERROR : route index wrong " + routes.get(r).nodes.get(i).index);
                }
                //check capacity constraints
                if (load > capacityLimitVehicle && routes.get(r).length>1)//large
                {
                    validSolution = false;
                    System.out.println("ERROR : capacity violation on route " + r);
                }
                if (load != routes.get(r).load)
                    System.out.println("ERROR : load computation wrong");
                if (routes.get(r).nodes.get( routes.get(r).nodes.size()-1 ).index < customers)
                    System.out.println("ERROR : last node is not a depot - " + r);
                if (routes.get(r).nodes.get( 0 ).index != routes.get(r).nodes.get( routes.get(r).nodes.size()-1 ).index)
                    System.out.println("ERROR : route starts and ends at different depots");
                if (routes.get(r).nodes.get( 0 ).index-customers != routes.get(r).depot)
                    System.out.println("ERROR : route has wrong depot index");
            }
            //check whether all customers have been visited
            for (int i=0; i<customers; i++)
                if (!visited[i] && nodes[i]!=null)
                    System.out.println("ERROR : customer " + i + " not visited");


            return validSolution;
        }


    }//CLASS



