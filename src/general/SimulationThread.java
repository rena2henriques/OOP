package general;

/**
 * This class provides a run method to launch threads and run a simulation.
 *
 */
public class SimulationThread extends Thread{

	/**
	 * Simulation to run
	 */
	SimulationA simulation;
	
	/**
	 * Constructor that creates a SimulationThread with the given simulation
	 * 
	 * @param sim - simulation to associate to this SimulationThread
	 */
	public SimulationThread(SimulationA sim){
		simulation=sim;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	/**
	 * Method to run the simulation. It calls the simulate method of the simulation.
	 */
	public void run() {
		simulation.simulate();
	}
}
