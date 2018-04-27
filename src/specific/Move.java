package specific;

import general.Event;

public class Move extends Event {
	private double move; //podia ser estatico para nao se enviar 
					//sempre que se cria um evento? 
					//Mas depois se um gajo tiver varias 
					//simulaçoes a correr ao mesmo tempo com valores diferentes não da :(
					//ya é melhor nao ser estatico
	private Individual individual;
	
	
	//constructor
	public Move(double time, Individual ind, Individual[] players/*, PEC pec*/) {
		this.setTime(time);
		this.individual = ind;
		//individuals = players vai ser do tipo array?
		//this.pec = pec -- Vem do super
	}
	
	//inherited implemetions
	public void simulateEvent() {
		//TODO
		//choose direction
		//adicionar nova poisção à pessoa
		//fazer o check cyle dentro da pessoa? dentro do add. porque o move nao tem nada a ver com os ciclos
		//atualizar bue parametros do inviduo
		//criar e adcioinar novo move à pec
	}
	
	//public methods
	//getters e setters
	
	//private methods
	private void chooseDirection () {
		
	}
	
	private boolean checkCycle () {
		
	}
	
	private void breakCycle () {
		
	}
}
