package com.github.elijadei.lrp.builder;

import com.github.elijadei.lrp.Node;
import com.github.elijadei.lrp.Nodes;
import com.github.elijadei.lrp.clusterizing.Clusterizer;
import com.graphhopper.jsprit.analysis.toolbox.AlgorithmSearchProgressChartListener;
import com.graphhopper.jsprit.analysis.toolbox.GraphStreamViewer;
import com.graphhopper.jsprit.analysis.toolbox.StopWatch;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.algorithm.listener.VehicleRoutingAlgorithmListeners;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.util.Coordinate;
import com.graphhopper.jsprit.core.util.Solutions;
import com.graphhopper.jsprit.instance.reader.CordeauReader;
import com.graphhopper.jsprit.util.Examples;

import java.util.Collection;
import java.util.List;

public class RouteBuilder {
   Clusterizer clusterizer;

    public RouteBuilder(Clusterizer clusterizer) {
        this.clusterizer = clusterizer;
    }

    int nrVehicles = 12;
    int capacity = 200;
    int depotcounter = 1;

/*
    private String file;


    public RouteBuilder(String file) {
        this.file = file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    */

    public void buildRoute() {



        Examples.createOutputFolder();
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
       // new CordeauReader(vrpBuilder).read(file);



        /*
        Coordinate first = Coordinate.newInstance(10, 20);
        Coordinate second = Coordinate.newInstance(5, 10);
        Coordinate third = Coordinate.newInstance(-10, -20);
        Coordinate fourt = Coordinate.newInstance(10, -20);
        double[] x = new double[]{first.getX(), second.getX(), third.getX(), fourt.getX()};
        double[] y = new double[]{first.getY(), second.getY(), third.getY(), fourt.getY()};




        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < nrVehicles; j++) {
                VehicleType vehicletype = VehicleTypeImpl.Builder.newInstance(depotcounter + "_type").addCapacityDimension(0, capacity).setCostPerDistance(2).build();

                String vehicleID = depotcounter + "_" + (j + 1) + "_vehicle";
                VehicleImpl.Builder vhBuilder = VehicleImpl.Builder.newInstance(vehicleID);
                vhBuilder.setStartLocation(Location.newInstance(x, y));
                vhBuilder.setType(vehicletype);
                VehicleImpl vehicle = vhBuilder.build();
                vrpBuilder.addVehicle(vehicle);
                System.out.println(x[i]);
            }
            depotcounter++;
        }
        //for(Coordinate depot  : Arrays.asList())
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.INFINITE);

        VehicleRoutingProblem vrp = vrpBuilder.build();

        /*
         * solve the problem



        VehicleRoutingAlgorithm vra = Jsprit.Builder.newInstance(vrp).setProperty(Jsprit.Parameter.FAST_REGRET, "true").setProperty(Jsprit.Parameter.THREADS, "5").buildAlgorithm();
        vra.setMaxIterations(1000);
        vra.getAlgorithmListeners().addListener(new StopWatch(), VehicleRoutingAlgorithmListeners.Priority.HIGH);
        vra.getAlgorithmListeners().addListener(new AlgorithmSearchProgressChartListener("output/progress.png"));
        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
        new GraphStreamViewer(vrp, Solutions.bestOf(solutions)).setRenderDelay(50).display();
*/
    }


}
