package com.github.elijadei.lrp.implementations;

import com.github.elijadei.lrp.intefaces.RoutingInterface;
import com.github.elijadei.lrp.model.Depots;
import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.Point;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.algorithm.box.SchrimpfFactory;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.util.Coordinate;
import com.graphhopper.jsprit.core.util.Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RoutingInterfaceImpl implements RoutingInterface {



    @Override
    public Double buildRoutes(List<Group> pointGroups, List<Depots> Depots) {

        int nuOfVehicles = 10;
        int capacity = 80;
        double cost;

        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        //for each group of points
        pointGroups.forEach(group-> {

                    int depotCounter = 1;

                    //for each depot from Depot list or kmeans
                    for (Depots depotCoord : Depots) {
                        int counter = 1;

                        //create output list of services
                        List<Service> services = new ArrayList<Service>();

                        //for each customer in the group
                        for (Point customer : group.getGroup()) {
                            VehicleTypeImpl vehicleType = VehicleTypeImpl.Builder.newInstance(depotCounter + "_type").addCapacityDimension(0, capacity).setCostPerDistance(1.0).build();
                            VehicleImpl vehicle = VehicleImpl.Builder.newInstance(depotCounter + "_" + (counter + 1) + "_vehicle").setStartLocation(Location.newInstance(depotCoord.getX(), depotCoord.getY())).setType(vehicleType).build();
                            vrpBuilder.addVehicle(vehicle);

                            Location location = Location.newInstance(customer.getX(), customer.getY()); //here Lan. and Lat.
                            Service.Builder builder = Service.Builder.newInstance(String.valueOf(counter))
                                    .addSizeDimension(0, 1).setLocation(location);
                            Service service = builder.build();
                            services.add(service);
                            counter++;

                        }
                        depotCounter++;
                    }
                });




        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.INFINITE);
        VehicleRoutingProblem problem = vrpBuilder.build();
        VehicleRoutingAlgorithm vra = new SchrimpfFactory().createAlgorithm(problem);
        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
        VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);

        cost =  bestSolution.getCost();

return cost;
    }
}
