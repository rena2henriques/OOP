package main;
/**
 * 
 */

import general.INumberGenerator;
import general.SimulationA;
import specific.*;
	

/**
 * Class just to specify the generators of time between events and run the simulation
 * 
 * @author Group 6
 *
 */
public class MainSimulator {

	/**
	 * The main function to run the project
	 * 
	 * @param args where should be specified the xml file to use
	 */
	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.err.println("No XML File received in the argumens.");
			System.exit(-1);
		} else if(args.length != 1) {
			System.err.println("Number of arguments received isn't correct.");
			System.exit(-1);
		}
	
		INumberGenerator deathTime=new DeathExpRandomTime();
		INumberGenerator moveTime=new MoveExpRandomTime();
		INumberGenerator repTime= new ReproductionExpRandomTime();
		INumberGenerator thresh= new RandomPercentage();
		
		SimulationA simulation = new GridSimulation(args[0],deathTime,moveTime,repTime,thresh);
		simulation.simulate();

		/*SimulationA simulation2 = new GridSimulation(args[0],deathTime,moveTime,repTime,thresh);

		SimulationThread test= new SimulationThread(simulation);
		SimulationThread test2= new SimulationThread(simulation2);
		test.start();
		test2.start();*/
		
	}

}
