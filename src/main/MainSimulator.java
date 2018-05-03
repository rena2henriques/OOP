package main;
/**
 * 
 */

import specific.*;
import general.INumberGenerator;


/**
 * @author renato
 *
 */
public class MainSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//MUDAR EVENTUALMENTE
		INumberGenerator<Individual> death = new DeathExpRandomTime();
		INumberGenerator<Individual> move = new MoveExpRandomTime();
		INumberGenerator<Individual> reproduction = new ReproductionExpRandomTime();
		INumberGenerator<Individual> threshold = new RandomPercentage();
		
		SimulationNumberCommands generator= new SimulationNumberCommands(death,move,reproduction,threshold);

		GridSimulation simulation = new GridSimulation("projectexample.xml",generator);
		simulation.simulate();		
	}

}
