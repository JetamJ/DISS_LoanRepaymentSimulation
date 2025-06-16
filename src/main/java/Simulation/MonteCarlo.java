package Simulation;

import Entity.Interest;
import GUI.GuiObserver;
import Generators.*;

import java.util.ArrayList;

public class MonteCarlo extends Core {

    private double HU;
    private final double initialHU;
    private int n;
    private double ipm;
    private int m;
    private double result;
    private double simulationResult = 0;
    private int iterationNumber = 0;
    private final SeedGenerator seedGenerator;
    private final int[] fixation = new int[4];
    private final int numberOfReplications;
    private int stepSize;
    private final GuiObserver o;
    double simulationWarming;


    public MonteCarlo(GuiObserver o, double HU, int numberOfReplications, int seed, String strategy) {
        setFixation(strategy);
        this.initialHU = HU;
        this.numberOfReplications = numberOfReplications;
        this.seedGenerator = new SeedGenerator(seed);
        this.o = o;
        this.simulationWarming = this.numberOfReplications * 0.2;
    }
    @Override
    public void beforeSimulation() {
        int maxGraphData = 1000;
        if (this.numberOfReplications <= maxGraphData) {
            this.stepSize = 1;
        } else {
            this.stepSize = this.numberOfReplications / maxGraphData;
        }
    }

    @Override
    public void simulate() {
        int year = 2024;
        iterationNumber++;

        m = this.fixation[0];
        result += m * 12 * getMonthlyPayment(getGenerator(year));
        HU = getBalance();
        year += m;
        n -= m;

        m = this.fixation[1];
        result += m * 12 * getMonthlyPayment(getGenerator(year));
        HU = getBalance();
        year += m;
        n -= m;

        m = this.fixation[2];
        result += m * 12 * getMonthlyPayment(getGenerator(year));
        HU = getBalance();
        year += m;
        n -= m;

        m = this.fixation[3];
        result += m * 12 * getMonthlyPayment(getGenerator(year));
    }

    @Override
    public void afterSimulation() {
        System.out.println(this.getSimulationResult());
    }

    @Override
    public void beforeReplication() {
        this.result = 0;
        this.HU = this.initialHU;
        this.n = 10;
    }

    @Override
    public void afterReplication() {
        this.simulationResult += this.result;
        this.sendData();
    }

    public double getSimulationResult() {
        return this.simulationResult / iterationNumber;
    }

    public double getMonthlyPayment(Generator gen) {
        double ipa = gen.nextVal() / 100;
        this.ipm = ipa / 12;
        double up = HU * ipm * Math.pow(1 + ipm, 12*n);
        double down =  Math.pow(1 + ipm, 12*n) -1;
        return up / down;
    }

    public double getBalance() {
        double up = Math.pow(1 + ipm, 12 * n) - Math.pow(1 + ipm, 12 * m);
        double down = Math.pow(1 + ipm, 12 * n) - 1;
        return HU * (up / down);
    }

    public Generator getGenerator(int year) {
        if (year == 2024 || year == 2025) {
            return new DUniformGenerator(seedGenerator,1,4);
        }
        if (year == 2026 || year == 2027) {
            return new CUniformGenerator(seedGenerator,0.3,5);
        }
        if (year == 2028 || year == 2029) {
            ArrayList<Interest> interests = new ArrayList<>();
            interests.add(new Interest(0.1,0.3,0.1));
            interests.add(new Interest(0.3,0.8,0.35));
            interests.add(new Interest(0.8,1.2,0.2));
            interests.add(new Interest(1.2,2.5,0.15));
            interests.add(new Interest(2.5,3.8,0.15));
            interests.add(new Interest(3.8,4.8,0.05));
            return new EmpGenerator(seedGenerator, interests);
        }
        if (year == 2030 || year == 2031) {
            return new DeterGenerator(1.3);
        }
        if (year == 2032 || year == 2033) {
            return new CUniformGenerator(seedGenerator,0.9,2.2);
        }
        return null;
    }

    public void setFixation(String strategy) {
        switch (strategy) {
            case "A":
                this.fixation[0] = 5;
                this.fixation[1] = 3;
                this.fixation[2] = 1;
                this.fixation[3] = 1;
                break;
            case "B":
                this.fixation[0] = 3;
                this.fixation[1] = 3;
                this.fixation[2] = 3;
                this.fixation[3] = 1;
                break;
            case "C":
                this.fixation[0] = 3;
                this.fixation[1] = 1;
                this.fixation[2] = 5;
                this.fixation[3] = 1;
                break;
        }
    }

    private void sendData() {
        if (this.stepSize != 1) {
            if (this.iterationNumber % this.stepSize == 0 && this.iterationNumber >= this.simulationWarming) {
                o.refresh(this.iterationNumber, this.getSimulationResult());
            }
        } else {
            o.refresh(this.iterationNumber, this.getSimulationResult());
        }
    }

    @Override
    public void run() {
        run(this.numberOfReplications);
    }
}
