package com.github.elijadei.lrp.implementations;

import com.github.elijadei.lrp.intefaces.RoutingInterface;
import com.github.elijadei.lrp.model.Group;
import com.github.elijadei.lrp.model.InitialGroupPoints;
import com.github.elijadei.lrp.model.Point;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.algorithm.box.SchrimpfFactory;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.util.Solutions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoutingInterfaceImpl implements RoutingInterface {

    int demand;
    int capacity=248500;

    @Override
    public Double calcRouteCost(List<Group> pointGroups) {

        List<Double> costs = new ArrayList<>();
        pointGroups.forEach(group -> {
            int count=0;

            final int WEIGHT_INDEX = 0;
            VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicleType").addCapacityDimension(WEIGHT_INDEX, capacity);
            VehicleType vehicleType = vehicleTypeBuilder.build();

            VehicleImpl.Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
            vehicleBuilder.setStartLocation(Location.newInstance(group.getCenter().getX(), group.getCenter().getY()));
            vehicleBuilder.setType(vehicleType);
            VehicleImpl vehicle = vehicleBuilder.build();

            int counter = 1;
            List<Service> services = new ArrayList<Service>();

            for (Point groups : group.getGroup()) {
                for(Point nod: groups.getInitialPoints()) {
                    Location location = Location.newInstance(nod.getX(), nod.getY()); //here Lan. and Lat.
                    Service.Builder builder = Service.Builder.newInstance(String.valueOf(counter))
                            .addSizeDimension(0, nod.getDemand()).setLocation(location);
                    Service service = builder.build();
                    services.add(service);
                    counter++;
                }
            }

            VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
            vrpBuilder.addVehicle(vehicle).addAllJobs(services);
            vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.INFINITE);
            VehicleRoutingProblem problem = vrpBuilder.build();

            VehicleRoutingAlgorithm vra = new SchrimpfFactory().createAlgorithm(problem);
            Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
            VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);
            Double cost = bestSolution.getCost();
            count++;
            costs.add(cost);
        });

        double costTotal = 0;
        for (int i = 0; i < costs.size(); i++) {
            costTotal = costTotal + costs.get(i);
            ;
        }

        return costTotal;

    }
}
