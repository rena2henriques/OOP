package specific;

import general.INumberGenerator;
import general.SimulationCommands;

public class GridCommands extends SimulationCommands{

	public GridCommands(INumberGenerator[] c){
		super(c);
	}
	
	public double getCommand(int i, Individual ind){
		((IndividualTimeGenerator) commands[i]).setIndividual(ind);
		double nr= super.getCommand(i);
		
		//so queremos numeros positivos na nossa gridsimulation
		if(nr<0)
			nr=0;
		
		return nr;
	}
}

