package specific;

import general.SimulationA;

public class SimulationThread extends Thread{

	SimulationA gridSimulation;
	
	public SimulationThread(SimulationA sim){
		gridSimulation=sim;
	}
	
	public void run() {
		gridSimulation.simulate();
	}
}
