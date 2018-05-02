package specific;

import general.Event;

public abstract class IndividualEvent extends Event{
	
	private Individual individual;
	
	IndividualEvent(double time,Individual individual){
		super(time);
		this.individual = individual;
	}
	
	public Individual getIndividual() {
		return individual;
	}
	
	public void setIndividual(Individual individual) {
		this.individual=individual;
	}
	
	public boolean peekEvent(Object o) {
		
		if(individual==(Individual) o)
			return true;
		
		return false;
		//EXCEPÇAO caso nao recebamos um individuo???			
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
