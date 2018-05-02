package specific;

import general.Event;

public abstract class IndividualEvent extends Event{
	
	private Individual individual;
	private SimulationNumberCommands sNC;
	
	IndividualEvent(double time,Individual individual, SimulationNumberCommands simNumCom){
		super(time);
		this.individual = individual;
		this.sNC = simNumCom;
	}
	
	public Individual getIndividual() {
		return individual;
	}
	
	public void setIndividual(Individual individual) {
		this.individual=individual;
	}
	
	public SimulationNumberCommands getsNC() {
		return sNC;
	}
	
	public boolean peekEvent(Object o) {
		
		//PODE DAR MERDA POR CAUSA DO CAST, VERIFICAR!
		//we want this event to be peeked if o equals its individual
		try {
			if(individual== (Individual) o)
				return true;
		} catch(ClassCastException exception) {
			return false;
		}
		
		return false;
		//EXCEP�AO caso nao recebamos um individuo???
		
	}

	

	/**
	 * Receives the next event generated time and checks if its after the indiviual death
	 * 
	 * @param time
	 * @return boolean
	 */
	static boolean checkDeathTime(double time, Individual ind) {
		if (time > ind.getIndDeath().getTime()) {
			System.out.println("event not added"); //NAO ESQUECER DE TIRAR
			return false;
		}
		return true;
	}
	
}
