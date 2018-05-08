package main;
/**
 * 
 */

import general.INumberGenerator;
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
		
		INumberGenerator deathTime=new DeathExpRandomTime();
		INumberGenerator moveTime=new MoveExpRandomTime();
		INumberGenerator repTime= new ReproductionExpRandomTime();
		INumberGenerator thresh= new RandomPercentage();
		SimulationA simulation = new GridSimulation("test_14_soumindividual.xml",deathTime,moveTime,repTime,thresh);

		simulation.simulate();
	}

}
