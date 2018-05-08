package main;
/**
 * 
 */

import general.SimulationA;
import specific.*;
	

/**
 * @author renato
 *
 */
public class MainSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//SimulationNumberCommands generator= new SimulationNumberCommands(death,move,reproduction,threshold);

		SimulationA simulation = new GridSimulation("projectexample.xml");

		simulation.simulate();		
	}

}
