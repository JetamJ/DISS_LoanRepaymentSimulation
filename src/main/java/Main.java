import Simulation.MonteCarlo;
import GUI.GUI;
import Simulation.SimulationService;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        SimulationService ss = new SimulationService(gui);
    }

}