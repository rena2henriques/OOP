package main;
/**
 * 
 */

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

		GridSimulation simulation = new GridSimulation("test_9_maxpopgrande.xml");//DECLARAR COMO SIMULATIONA??
		simulation.simulate();		
	}

}
