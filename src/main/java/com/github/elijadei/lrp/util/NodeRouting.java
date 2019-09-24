package com.github.elijadei.lrp.util;
import java.util.HashMap;

public class NodeRouting {

        public int index;
        public int routeIndex;
        public int routePosition;
        public double x;
        public double y;
        public int demand;
        public int depot;
        public double opening_costs;

        public HashMap<Integer, Float> distances;
        public HashMap<Integer, Float> distances_for_CW;

        @SuppressWarnings("unchecked")
        public NodeRouting copy()
        {
            NodeRouting n	= new NodeRouting(index);
            n.routeIndex	= routeIndex;
            n.routePosition	= routePosition;
            n.x				= x;
            n.y				= y;
            n.demand		= demand;
            n.opening_costs = opening_costs;
            if(distances!=null) n.distances		= (HashMap<Integer, Float>) distances.clone();
            if(distances_for_CW!=null) n.distances_for_CW	= (HashMap<Integer, Float>) distances_for_CW.clone();
            n.depot			= depot;

            return n;
        }


        public NodeRouting(int i)
        {
            index = i;
            distances			= new HashMap<Integer, Float>();
            distances_for_CW	= new HashMap<Integer, Float>();
        }

    }//class


