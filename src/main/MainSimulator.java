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
	}

}
