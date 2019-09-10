package com.github.elijadei.lrp.util;


/*
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Pmedian {
    // Number of locations
    private int N;

    // Number of edges between locations.
    private int E;

    // Size of the subset S of locations.
    private int p;

    // Weight matrix of the shortest path between locations.
    private int[][] w;

    // Maximum distance between two locations.
    private int wmax;

    // LocalSolver.
    private LocalSolver localsolver;

    // Decisions variables
    private LSExpression[] x;

    // Objective
    private LSExpression totalCost;

    // List of selected locations
    private List<Integer> solution;

    private Pmedian(LocalSolver localsolver) {
        this.localsolver = localsolver;
    }

    // Reads instance data.
    private void readInstance(String fileName) throws IOException {
        try(Scanner input = new Scanner(new File(fileName))) {
            N = input.nextInt();
            E = input.nextInt();
            p = input.nextInt();

            w = new int[N][N];
            wmax = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    w[i][j] = input.nextInt();
                    if (w[i][j] > wmax)
                        wmax = w[i][j];
                }
            }
        }
    }

    // Declares the optimization model.
    private void solve(int limit) {
        LSModel model = localsolver.getModel();

        // Boolean decisions
        x = new LSExpression[N];
        for (int i = 0; i < N; i++) {
            x[i] = model.boolVar();
        }

        // No more than p locations are selected
        LSExpression openedLocations = model.sum();
        for (int i = 0; i < N; i++) {
            openedLocations.addOperand(x[i]);
        }

        model.constraint(model.leq(openedLocations, p));

        // Costs between location i and j is w[i][j] if j is selected in S or 2*wmax if not
        LSExpression[][] costs = new LSExpression[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                costs[i][j] = model.iif(x[j], w[i][j], 2 * wmax);
            }
        }

        // Cost between location i and the closest selected location
        LSExpression[] cost = new LSExpression[N];
        for (int i = 0; i < N; i++) {
            cost[i] = model.min();
            for (int j = 0; j < N; j++) {
                cost[i].addOperand(costs[i][j]);
            }
        }

        // Minimize the total cost
        totalCost = model.sum();
        for (int i = 0; i < N; i++) {
            totalCost.addOperand(cost[i]);
        }
        model.minimize(totalCost);

        model.close();

        // Parameterizes the solver.
        localsolver.getParam().setTimeLimit(limit);
        localsolver.solve();

        solution = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            if (x[i].getValue() == 1)
                solution.add(i);
        }
    }

    // Writes the solution in a file following the following format:
    // - value of the objective
    // - indiced of the selected locations (between 0 and N-1) */
/*
    private void writeSolution(String fileName) throws IOException {
        try(PrintWriter output = new PrintWriter(fileName)) {
            output.println(totalCost.getValue());
            for (int i = 0; i < solution.size(); i++) {
                output.print(solution.get(i) + " ");
            }
            output.println();
        }
    }


*/

