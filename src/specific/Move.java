package specific;

import general.Event;

public class Move extends Event {
	private double move; //podia ser estatico para nao se enviar 
					//sempre que se cria um evento? 
					//Mas depois se um gajo tiver varias 
					//simulaçoes a correr ao mesmo tempo com valores diferentes não da :(
					//ya é melhor nao ser estatico
	
	private Individual individual; //aqui ou numa super?? 
	
	
	//constructor
	public Move(double time, Individual ind) {
		this.setTime(time); //super
		this.individual = ind; //suepr tambem??
	}
	
	//inherited implemetions
	public void simulateEvent() {
		//TODO
		//choose direction -> retornar direção
		//adicionar novo point à pessoa
		//fazer o check cyle dentro da pessoa? dentro do add. porque o move nao tem nada a ver com os ciclos
		//calcular media -->> Depois de atualizar o conforto
		//criar novo move e adicionar ao array?
		//criar um novo move mandar time
		//retornar array :)
	}
	
	//public methods
	//getters e setters
	
	//private methods
	private void chooseDirection() {
			//sacar no individuo o ultimo point do array
			//ver o size o connections desse point --> dá o N
			//pa ver o size, probably, mapPoint.getValidDirections, que vai ter um ciclo
			//gerar numero random entre 0 e 1
			//ver a que posição equivale o numero gerado
	}

	//fazer isto no ind??
	private boolean checkCycle() {
		
	}
	
	private void breakCycle() {
		
	}
}

//floor(p*n) da me a poisção que preciso de tirar do array.
