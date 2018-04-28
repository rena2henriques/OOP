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
		
		//local variables
		LinkedList<Event> eventList; //list of events returned in the simulateEvent
		Individual currentInd=null; // individual of the current event
		Event currentEvent; // current event
		double obsvTime=finalTime/20;


		//initializing the simulation with the 1st event in pec
		currentEvent=pec.nextEvent();			
		//CHECKAR SE CURRENT EVENT É NULL ANTES DE ACEDER AO TEMPO?
		currentTime=currentEvent.getTime();
		
		
		while(currentEvent!=null && currentTime< finalTime) {
			
			while(currentTime>obsvTime) {				
				printObservation();
				obsvTime+=finalTime/20;
			}

			//fazer try and catch com excepções: a cena da epidemia, excepção para ver se já acabou?, outra excepção?
			
			//simulate current event
			eventList=(LinkedList<Event>) currentEvent.simulateEvent();
			numEvents++;			
			//add new list of events to pec
			addNewEvents(eventList);
			
			
			//CHECKAR SE O INDIVIDUO CHEGOU AO FIM
			currentInd = currentEvent.getIndividual(); //ACRESCENTAR UM NOVO PATAMAR NOS EVENTOS COM INDIVIDUAL?? DISCUTIR	
			
			//if none of the individuals has reached the final point before and the current individual
			//reaches it, we change the flag and set the current individual as the best individual
			if(!finalPointHit && map.isFinal(currentInd.getPath().get(currentInd.getPath().size()-1))) {
				finalPointHit=true; 
				updateBestFit(currentInd);			
			}
			
			//we only check the best individual if the final point hasn't been reached 
			//or if it has, we only check if our individual is also in the final point
			else if(!finalPointHit || (finalPointHit &&  map.isFinal(currentInd.getPath().get(currentInd.getPath().size()-1)))) {
				if(checkBestFit(currentInd))
					updateBestFit(currentInd);			
			}
				
			currentEvent=pec.nextEvent();			
			//TER CUIDADO PARA O CASO EM QUE O PEC RETORNA NULL!!! ATENCAO!
			//MUDAR ISTO ou por tipo uma excepção
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
		
		//if none of the individuals has reached the final point we check the comfort
		if(!finalPointHit && ind.getComfort()>bestInd.getComfort()) {
			return true;
		} 
		//if an individual has already reached the final point, we check the cost
		else if(finalPointHit && ind.getCost() < bestInd.getCost()) {
			return true;
		}
		return false;
	}
	
	
	void updateBestFit(Individual ind) throws CloneNotSupportedException {
		bestInd=(Individual) ind.clone(); //CLONE OU COPY CONSTRUCTOR?
	}
	
	
	ArrayList<Point> getResult() {
		return bestInd.getPath();
	}
	
	
	public void printObservation() {
		
		System.out.println("Observation "+obsvNumber+":");
		//POR AS COISAS NO FORMATO QUE ELA QUER - PERGUNTAR SE TEM QUE SER IGUALLZINHO
		
		obsvNumber++;				
	}

	
	public String printResult() {
		return "Path of the best fit individual = "+bestInd.toString(); 
	}
}
