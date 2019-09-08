package com.github.elijadei.lrp.builder;

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

public class RouteBuilder {
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
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance(); //ак понятъ эту строчку. почему мз берем один класс, билдер, и запихиваем в него другой обьект. ФииклРутингБилде.зачем?
        new CordeauReader(vrpBuilder).read(file);


        int nrVehicles = 12;
        int capacity = 200;
        int depotcounter = 1;
        Coordinate first = Coordinate.newInstance(10, 20); //что тут происходит. Почему Координат фирст. ане дабл например
        Coordinate second = Coordinate.newInstance(5, 10);
        Coordinate third = Coordinate.newInstance(-10, -20);
        Coordinate fourt = Coordinate.newInstance(10, -20);
        double[] x = new double[]{first.getX(), second.getX(), third.getX(), fourt.getX()}; //почему тут я не могу просто создатъ Аррей например? типа double[] s = new double[]{first};
        double[] y = new double[]{first.getY(), second.getY(), third.getY(), fourt.getY()};
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < nrVehicles; j++) {
                VehicleType vehicletype = VehicleTypeImpl.Builder.newInstance(depotcounter + "_type").addCapacityDimension(0, capacity).setCostPerDistance(2).build();
                //почему выбранна такая структура? как бы можно было иначе реализоватъ такое решение  VehicleType это Interface ..и мы туда запихиваем какой то непонятный обьект. почему?
                String vehicleID = depotcounter + "_" + (j + 1) + "_vehicle";
                VehicleImpl.Builder vhBuilder = VehicleImpl.Builder.newInstance(vehicleID); //что тут происходит? мы создали новый обейкт и передали ему свойства обьекта builder.newInstance()? почему стринг
                vhBuilder.setStartLocation(Location.newInstance(x[i], y[i]));
                vhBuilder.setType(vehicletype);
                VehicleImpl vehicle = vhBuilder.build();
                vrpBuilder.addVehicle(vehicle); //что мы сделали тут? создали переменную или что? обычно переменные по другому же инициализируются
                System.out.println(x[i]);
            }
            depotcounter++;
        }
        //for(Coordinate depot  : Arrays.asList())
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.FINITE);

        VehicleRoutingProblem vrp = vrpBuilder.build();

        /*
         * solve the problem
         */
        VehicleRoutingAlgorithm vra = Jsprit.Builder.newInstance(vrp).setProperty(Jsprit.Parameter.FAST_REGRET, "true").setProperty(Jsprit.Parameter.THREADS, "5").buildAlgorithm();
        vra.setMaxIterations(1000);
        vra.getAlgorithmListeners().addListener(new StopWatch(), VehicleRoutingAlgorithmListeners.Priority.HIGH);
        vra.getAlgorithmListeners().addListener(new AlgorithmSearchProgressChartListener("output/progress.png"));
        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
        new GraphStreamViewer(vrp, Solutions.bestOf(solutions)).setRenderDelay(50).display();

    }


}
