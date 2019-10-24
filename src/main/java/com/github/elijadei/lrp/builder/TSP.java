package com.github.elijadei.lrp.builder;

import com.github.elijadei.lrp.model.Node;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl.Builder;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Solutions;
import com.graphhopper.jsprit.io.problem.VrpXMLWriter;
import com.graphhopper.jsprit.util.Examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TSP {
    //List<Nodes> nodes;
    double xc;
    double yc;
    List<Node> node;

    public TSP(Node nod, List<Node> nodes) {
        this.xc = nod.getX();
        this.yc = nod.getY();
        this.node = nodes;
    }


    public void tspBuild() throws FileNotFoundException {
        Examples.createOutputFolder();
        /*
         * get a vehicle type-builder and build a type with the typeId "vehicleType" and a capacity of 2
         */
        final int WEIGHT_INDEX = 0;
        VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicleType").addCapacityDimension(WEIGHT_INDEX, 1);
        VehicleType vehicleType = vehicleTypeBuilder.build();

        Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
        vehicleBuilder.setStartLocation(Location.newInstance(xc, yc));
        vehicleBuilder.setType(vehicleType);
        VehicleImpl vehicle = vehicleBuilder.build();

        int counter = 1;
        List<Service> services = new ArrayList<Service>();

        for (Node nods : node) {
            Location location = Location.newInstance(nods.getX(), nods.getY()); //here Lan. and Lat.
            Service.Builder builder = Service.Builder.newInstance(String.valueOf(counter))
                    .addSizeDimension(0, 1).setLocation(location);
            Service service = builder.build();
            services.add(service);
            counter++;
        }

        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        vrpBuilder.addVehicle(vehicle).addAllJobs(services);
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.INFINITE);

        VehicleRoutingProblem problem = vrpBuilder.build();
        VehicleRoutingAlgorithm algorithm = Jsprit.Builder.newInstance(problem)
                .setProperty(Jsprit.Parameter.ITERATIONS.toString(), "500")
                .buildAlgorithm();
        Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();

        /*
         * get the best
         */
        VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);


        ArrayList<VehicleRoutingProblemSolution> bestSolutions = new ArrayList<>();
        bestSolutions.add(bestSolution);

        PrintWriter printWriter = new PrintWriter(new File("output/problem-with-best-solution.txt"));
        new VrpXMLWriter(problem, solutions).write("output/problem-with-solution.xml");
        SolutionPrinter.print(printWriter, problem, bestSolution, SolutionPrinter.Print.CONCISE);

        /*
         * plot
         */
       // new Plotter(problem, bestSolution).plot("output/plot.png","simple example");

        /*
        render problem and solution with GraphStream
         */
     //    new GraphStreamViewer(problem, bestSolution).labelWith(GraphStreamViewer.Label.ID).setRenderDelay(200).display();

    }

}