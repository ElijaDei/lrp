package com.github.elijadei.lrp.builder;

import com.github.elijadei.lrp.clusterizing.Clusterizer;
import com.graphhopper.jsprit.analysis.toolbox.GraphStreamViewer;
import com.graphhopper.jsprit.analysis.toolbox.StopWatch;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.listener.VehicleRoutingAlgorithmListeners;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Coordinate;
import com.graphhopper.jsprit.core.util.Solutions;
import com.graphhopper.jsprit.instance.reader.CordeauReader;
import com.graphhopper.jsprit.io.algorithm.VehicleRoutingAlgorithms;
import com.graphhopper.jsprit.util.Examples;

import java.util.Collection;

public class RouteBuilder {
   Clusterizer clusterizer;

   /*
    public RouteBuilder(Clusterizer clusterizer) {
        this.clusterizer = clusterizer;
    }
    */


    int nrVehicles = 24;
    int capacity = 2500;
    int depotcounter = 500;


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



    public void buildRoute() {



        Examples.createOutputFolder();
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        new CordeauReader(vrpBuilder).read(file);




        Coordinate first = Coordinate.newInstance(10, 20);
        Coordinate second = Coordinate.newInstance(5, 10);
        Coordinate third = Coordinate.newInstance(-10, -20);
        Coordinate fourt = Coordinate.newInstance(10, -20);
        Coordinate first1 = Coordinate.newInstance(11, 30);
        Coordinate second2 = Coordinate.newInstance(9, 1);
        Coordinate third3 = Coordinate.newInstance(-13, -16);
        Coordinate fourt4 = Coordinate.newInstance(10, -30);
        Coordinate first5 = Coordinate.newInstance(30, 25);
        Coordinate second6 = Coordinate.newInstance(23, 70);
        Coordinate third7 = Coordinate.newInstance(-21, -1);
        Coordinate fourt8 = Coordinate.newInstance(10, -70);
        double[] x = new double[]{first.getX(), second.getX(), third.getX(), fourt.getX(), first1.getX(), second2.getX(), third3.getX(), fourt4.getX(),first5.getX(), second6.getX(), third7.getX(), fourt8.getX() };
        double[] y = new double[]{first.getY(), second.getY(), third.getY(), fourt.getY(), first1.getY(), second2.getY(), third3.getY(), fourt4.getY(),first5.getY(), second6.getY(), third7.getY(), fourt8.getY()};

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < nrVehicles; j++) {
                VehicleType vehicletype = VehicleTypeImpl.Builder.newInstance(depotcounter + "_type").addCapacityDimension(1, capacity).setCostPerDistance(4).build();

                String vehicleID = depotcounter + "_" + (j + 1) + "_vehicle";
                VehicleImpl.Builder vhBuilder = VehicleImpl.Builder.newInstance(vehicleID);
                vhBuilder.setStartLocation(Location.newInstance(x[i], y[i]));
                vhBuilder.setType(vehicletype);
                VehicleImpl vehicle = vhBuilder.build();
                vrpBuilder.addVehicle(vehicle);
                System.out.println(x[i]);
            }
            depotcounter++;
        }
        //for(Coordinate depot  : Arrays.asList())
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.FINITE);

        VehicleRoutingProblem vrp = vrpBuilder.build();


         //solve the problem



       // VehicleRoutingAlgorithm vra = Jsprit.Builder.newInstance(vrp).setProperty(Jsprit.Parameter.FAST_REGRET, "true").setProperty(Jsprit.Parameter.THREADS, "5").buildAlgorithm();
       // vra.setMaxIterations(500);
       // vra.getAlgorithmListeners().addListener(new StopWatch(), VehicleRoutingAlgorithmListeners.Priority.HIGH);
        //vra.getAlgorithmListeners().addListener(new AlgorithmSearchProgressChartListener("output/progress.png"));
        VehicleRoutingAlgorithm vra = VehicleRoutingAlgorithms.readAndCreateAlgorithm(vrp,"input/algorithmConfig.xml");
        vra.setMaxIterations(500);
        vra.getAlgorithmListeners().addListener(new StopWatch(), VehicleRoutingAlgorithmListeners.Priority.HIGH);
        Collection solutions = vra.searchSolutions();
       // Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();

        SolutionPrinter.print(vrp, Solutions.bestOf(solutions), SolutionPrinter.Print.VERBOSE);

        new GraphStreamViewer(vrp, Solutions.bestOf(solutions)).setRenderDelay(50).display();

    }


}
