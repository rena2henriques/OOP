package specific;

import general.Event;
import general.SimulationA;
import java.util.ArrayList;
import java.util.LinkedList;


public class PathSimulation extends SimulationA {
	
	//MUDAR NOMES DOS PARAMETROS DE ACORDO COM O ENUNCIADO
	
	private int maxInd, initPop;
	private Point initialPoint;
	private boolean finalPointHit;
	private double sensitivity;
	private Map map;
	private Individual bestInd;
	private ArrayList<Individual> individuals;
	private Event currentEvent;
	private int obsvNumber=1;
	//private double death, reproduction, move;
	
	public PathSimulation(File file) {
		
		//apply XML PARSER
		//params=XMLParser(file); 
		//IMPORT FILE?
		//CRIAR PARAMETROS!
		
		super.setFinalTime(finalinst);		
		map = new Map(grid,initialpoint,finalpoint,obstacles,events); // VERIFICAR NOMES DAS VARIAVEIS
		individuals = new ArrayList<Individual>(initPop);	
	}
	
	public Point getInitialPoint() {
		return initialPoint;
	}
	
	public void setInitialPoint(Point p) {
		initialPoint=p;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map m) {
		map=m;
	}
	
	public int getMaxInd() {
		return maxInd;
	}

	public void setMaxInd(int max_ind) {
		this.maxInd = max_ind;	
	}

	public int getInitPop() {
		return initPop;
	}

	public void setInitPop(int init_pop) {
		this.initPop = init_pop;
	}

	public boolean isFinalPointHit() {
		return finalPointHit;
	}

	public double getSensivity() {
		return sensitivity;
	}

	public void setSensitivity(double sensivity) {
		this.sensitivity = sensivity;
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

	public void simulate() {
		
		//reseting the dynamic variables
		reset();
		//initializing the population of individuals
		initialize();
		//list of events returned in the simulateEvent
		LinkedList<Event> eventList;

		//initializing the simulation with the 1st event in pec
		currentEvent=pec.nextEvent();			
		while(currentEvent.getTime()>currentTime+finalTime/20) {
			currentTime+=finalTime/20;
			printObservation();
			obsvNumber++;				
		}
		currentTime=currentEvent.getTime();
		
		//run actual simulation
		while(currentEvent!=null && currentTime< finalTime) {

			//fazer try and catch com excepções: a cena da epidemia, excepção para ver se já acabou?, outra excepção?
			
			//simulate current event
			eventList=(LinkedList<Event>) currentEvent.simulateEvent();
			numEvents++;			
			//add new list of events to pec
			addNewEvents(eventList);
			
			//CHECKAR SE O INDIVIDUO CHEGOU AO FIM
			Individual currentInd= currentEvent.getIndividual(); //ACRESCENTAR AO CURRENTEVENT
			if(!final_point_hit && map.isFinal(currentInd.getPath().get(currentInd.getPath().size()-1)))
				final_point_hit=true; 
			//E NO CASO EM QUE É O PRIMEIRO INDIVIDUO A CHEGAR AO FIM?
				
			
			//alterar o BESTINDIVIDUAL
			if(checkBestFit(currentEvent.getIndividual()))
				updateBestFit(currentEvent.getIndividual());
				
			currentEvent=pec.nextEvent();	
			
			//TER CUIDADO PARA O CASO EM QUE O PEC RETORNA NULL!!! ATENCAO!
			//MUDAR ISTO ou por tipo uma excepção
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
	void reset() {
		
		super.init();		
		finalPointHit=false;
		bestInd=null;
		individuals.clear();
		currentEvent=null;
		obsvNumber=1;
		Individual.setSensitivity(sensitivity);
		//DAR SET AOS PARAMETROS DO MOVE, REPRODUCTION E DEATH
	}
	
	void initialize() {
		
		//initializing the population 
		for(int i=0; i<initPop;i++) {
			individuals.add(new Individual(map, initialPoint));
			//first 3 events for each individual - death, move, reproduction
			pec.addEvent(new Death(RANDTIME,individuals.get(individuals.size()-1),individuals));
			pec.addEvent(new Move(RANDTIME,individuals.get(individuals.size()-1)));
			pec.addEvent(new Reproduction(RANDTIME,individuals.get(individuals.size()-1),individuals));
		}
		
	}
	
	boolean checkBestFit(Individual ind) {
		
		if(!final_point_hit) {
			//checkar comfort
		} else{
			//E NO CASO DO PRIMEIRO QUE CHEGA AO FINAL? COMO FAZER? - SO COMPARAR SE O ULTIMO PONTO DO BEST IND E O ULTIMO PONTO DO IND QUE MANDAM FOREM O FINAL?
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

	public String printResult() {
		return "Path of the best fit individual="+bestInd.toString(); //ADICIONAR TOSTRING AO INDIVIDUAL PARA IMPRIMIR O PATH COMO NO ENUNCIADO	
	}
}
