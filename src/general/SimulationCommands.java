package general;

public class SimulationCommands {
	
	protected INumberGenerator[] commands;

	public SimulationCommands(INumberGenerator[] commands){
		this.commands=commands;
	}
	
	public double getCommand(int i){
		return commands[i].getNumber();
	}

}
