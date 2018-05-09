package specific;

import general.INumberGenerator;
import general.SimulationCommands;

/**
 * GridCommands extends SimulationCommands and provides an additional method to generate numbers
 * that are necessarily associated to Individuals, such as the time between events.
 * 
 * @see SimulationCommands
 *
 */
public class GridCommands extends SimulationCommands{

	/**
	 * Constructor
	 * <p> Creates an object GridCommands with the specified array of INumberGenerators.
	 * 
	 * @param commands - array of INumberGenerators that will command how the simulation evolves.
	 */
	public GridCommands(INumberGenerator[] commands){
		super(commands);
	}
	
	/**
	 * Associates an Individual to the generator in the position i of the commands array and returns the number it generates.
	 * 
	 * @param i - index of the generator in the commands array
	 * @param ind - individual to associate to the generator
	 * @return the number generated with the specified generator and individual
	 */
	public double getCommand(int i, Individual ind){
		//mudar o individuo para depois obter o valor
		((IndividualTimeGenerator) commands[i]).setIndividual(ind);
		double nr= super.getCommand(i);
		
		//so queremos numeros positivos na nossa gridsimulation
		if(nr<0)
			nr=0;
		
		return nr;
	}
}

