package specific;

import general.Event;
import general.SimulationA;
import java.util.ArrayList;
import java.util.LinkedList;

public class PathSimulation extends SimulationA {
	
	//MUDAR NOMES DOS PARAMETROS DE ACORDO COM O ENUNCIADO
	
	private int maxInd, initPop;
	private boolean final_point_hit;
	private double sensivity;
	private Map map;
	private Individual bestInd;
	private ArrayList<Individual> individuals;
	private Event currentEvent;
	private int obsvNumber;
	//private double death, reproduction, move;
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map m) {
		map=m;
	}
	
	public int getMax_ind() {
		return maxInd;
	}

	public void setMax_ind(int max_ind) {
		this.maxInd = max_ind;	
	}

	public int getInit_pop() {
		return initPop;
	}

	public void setInit_pop(int init_pop) {
		this.initPop = init_pop;
	}

	public boolean isFinal_point_hit() {
		return final_point_hit;
	}

	public double getSensivity() {
		return sensivity;
	}

	public void setSensivity(double sensivity) {
		this.sensivity = sensivity;
	}
/*
	public double getDeath() {
		return death;
	}

	public void setDeath(double death) {
		this.death = death;
	}

	public double getReproduction() {
		return reproduction;
	}

	public void setReproduction(double reproduction) {
		this.reproduction = reproduction;
	}

	public double getMove() {
		return move;
	}

	public void setMove(double move) {
		this.move = move;
	}
*/
	
	public PathSimulation(File file) {
		
		//apply XML PARSER
		//params=XMLParser(file); 
		//IMPORT FILE?
		
		map = new Map(height,width,nObst,maxCost); // VERIFICAR NOMES DAS VARIAVEIS
		individuals = new ArrayList<Individual>(initPop);
		
	}

	@Override
	public void simulate() {
		
		init();
		
		//DAR RESET AS VARIAVEIS NECESSARIAS
		final_point_hit=false;
		bestInd=null;
		individuals.clear();
		currentEvent=null;
		obsvNumber=0;

		//INICIALIZAR A POPULAÇÃO		
		for(int i=0; i<initPop;i++) {
			individuals.add(new Individual());//MANDAR PARAMETROS LÁ PARA DENTRO);
			//3 EVENTOS PARA CADA INDIVIDUO - DEATH, MOVE, REPRODUCTION
			pec.addEvent(new Death(RANDTIME,individuals.get(individuals.size()-1),individuals));
			pec.addEvent(new Move(RANDTIME,individuals.get(individuals.size()-1)));
			pec.addEvent(new Reproduction(RANDTIME,individuals.get(individuals.size()-1),individuals));
		}
		
		currentEvent=pec.nextEvent();		
		while(currentEvent.getTime()>currentTime+finalTime/20) {
			currentTime+=finalTime/20;
			printObservation();
			obsvNumber++;				
		}
		currentTime=currentEvent.getTime();

		
		LinkedList<Event> eventList;
		
		//run actual simulation
		while(pec.getEvent()!=null && currentTime<finalTime) {
			
			//fazer try and catch com excepções: a cena da epidemia, excepção para ver se já acabou?, outra excepção?
			
			eventList=currentEvent.simulateEvent();
			numEvents++;
			
			//adicionar nova lista de eventos - mudar
			while(!eventList.isEmpty())
				pec.add(eventList.remove());
			
			//alterar o BESTINDIVIDUAL
			if(checkBestFit(currentEvent.getIndividual()))
				updateBestFit(currentEvent.getIndividual());
				
			currentEvent=pec.nextEvent();			
			while(currentEvent.getTime()>currentTime+finalTime/20) {
				currentTime+=finalTime/20;
				printObservation();
				obsvNumber++;				
			}
			currentTime=currentEvent.getTime();					
		}	
		
		printResult();
	}
	
	//CHECKAR VISIBILIDADES DAS FUNÇOES?
	boolean checkBestFit(Individual ind) {
		
		if(!final_point_hit) {
			//checkar comfort
		} else{
			//E NO CASO DO PRIMEIRO QUE CHEGA AO FINAL? COMO FAZER?
			//checkar custo
		}
	}
	
	boolean updateBestFit(Individual ind) {
		bestInd=ind.clone(); //ver como se faz clone
	}
	
	Point[] getResult() {
		return bestInd.getPath();
	}
	
	public void printObservation() {
		//METER AS CENAS NO FORMATO QUE ELA QUER
	}

	public void printResult() {
		return "Path of the best fit individual="+bestInd.toString(); //ADICIONAR TOSTRING AO INDIVIDUAL PARA IMPRIMIR O PATH COMO NO ENUNCIADO	
	}
}
