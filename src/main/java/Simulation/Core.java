package Simulation;

public abstract class Core implements Runnable {
    private volatile boolean stop = false;
    public abstract void beforeSimulation();
    public abstract void simulate();
    public abstract void afterSimulation();
    public abstract void beforeReplication();
    public abstract void afterReplication();

    public void run(int numberOfIterations) {
        this.beforeSimulation();
        for (int i = 0; i < numberOfIterations; i++) {
            if (this.stop) {
                break;
            }
            this.beforeReplication();
            this.simulate();
            this.afterReplication();
        }
        this.afterSimulation();
    }

    public void setStop(boolean value) {
        this.stop = value;
    }
}
