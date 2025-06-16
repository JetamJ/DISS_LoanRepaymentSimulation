package Simulation;

import GUI.GuiObserver;
public class SimulationService {

    private MonteCarlo mc;
    private String strategy;

    private final GuiObserver o;

    public SimulationService(GuiObserver o) {
        this.o = o;
    }

    public void runSimulation(double HU, int numberOfReplications, int seed) {
        this.mc = new MonteCarlo(this.o, HU, numberOfReplications, seed, this.strategy);
        Thread simulationThread = new Thread(mc);
        simulationThread.start();
    }

    public void stopSimulation() {
        this.mc.setStop(true);
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

}
