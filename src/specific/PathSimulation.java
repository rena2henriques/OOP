package specific;

import general.Event;
import general.SimulationA;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import general.Utils;


public class PathSimulation extends SimulationA {
	
	//MUDAR NOMES DOS PARAMETROS DE ACORDO COM O ENUNCIADO
	//checkar visibilidade dos metodos
	
	private int maxInd, initPop;
	private Point initialPoint;
	private boolean finalPointHit;
	private double sensitivity;
	private Map map;
	private Individual bestInd;
	private List<Individual> individuals;
	//private double death, reproduction, move;
	
	public PathSimulation(File file, ) {
		
		//apply XML PARSER
		//params=XMLParser(file); 
		//IMPORT FILE?
		//CRIAR PARAMETROS!
		//TEMOS QUE CHECKAR SE OS PARAMETROS FAZEM SENTIDO?
		
		
		super.setFinalTime(finalinst);	//<--MUDAR ISTO	CONSOANTE O XML // não é preciso fazer assim, posso aceder logo
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
		
		//reseting the dynamic variables and initializing the population of individuals
		reset();
		initialize();	
		//local variables
		//INICIALIZAR TODAS AS LISTAS!!!
		List<Event> eventList = new LinkedList<Event>(); //list of events returned in the simulateEvent
		Individual currentInd=null; // individual of the current event
		Event currentEvent= null; // current event //EVENT OU EVENTi?
		double obsvTime=finalTime/20;
		int obsvNumber=1;

		//initializing the simulation with the 1st event in pec
		currentEvent=pec.nextEvent();			
		if(currentEvent!=null)
			currentTime=currentEvent.getTime();
				
		while(currentEvent!=null && currentTime< finalTime) {
			
			while(currentTime>obsvTime) {				
				printObservation(obsvNumber,obsvTime);
				obsvTime+=finalTime/20;
				obsvNumber++;				
			}

			//fazer try and catch com excepções: a cena da epidemia,
			//FAZER TRY AND CATCH COM EPIDEMIA,  	
			//excepção para ver se já acabou?, outra excepção?
			
			//simulate current event and add new list of events to pec
			eventList=currentEvent.simulateEvent(); //cast to?(LinkedList<Event>) 
			numEvents++;			
			addNewEvents(eventList);
					
			//checkar o best indiviual			
			currentInd = (Individual) currentEvent.getEventObject(); //ACRESCENTAR UM NOVO PATAMAR NOS EVENTOS COM INDIVIDUAL?? DISCUTIR	
			
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
				
			//checking epidemics
			if(checkEpidemic())
				epidemic();
						
			currentEvent=pec.nextEvent();			
			if(currentEvent!=null)
				currentTime=currentEvent.getTime();					
		}	
			
		//if there are more observations after the final event
		if(obsvNumber<21) {
			printObservation(obsvNumber,obsvTime);
			obsvTime+=finalTime/20;
			obsvNumber++;
		}	
		
		printResult(); 
	}
	
	
	
	//CHECKAR VISIBILIDADES DAS FUNÇOES?
	void reset() {
		
		super.init();		
		finalPointHit=false;
		bestInd=null;
		individuals.clear();
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
		
		if(bestInd==null)
			return true;
		//if none of the individuals has reached the final point we check the comfort
		else if(!finalPointHit && ind.getComfort()>bestInd.getComfort()) {
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
	
	
	/*void checkEpidemic() throws ExceedsMaxPopException{
		if(individuals.size()> maxInd) throw new ExceedsMaxPopException();			
	}*/
	
	boolean checkEpidemic() {
		return individuals.size()> maxInd;
	}
	
	
	void epidemic() {
		
		individuals.sort(new IndividualComfortComparator()); //escolher os melhores 5

		//para os restantes fazer um for em que percorro e calculo se morrem ou não
		for(int i=5; i<individuals.size(); i++) {
			if(Utils.getRandom(1)>individuals.get(i).getComfort()) {
				//MORRE 		//como fazer a morte?
			}
		}
	}
	
	
	List<Point> getResult() {
		return bestInd.getPath();
	}
	
	
	public void printObservation(int obsvNumber, double obsvTime) {
		
		System.out.println("Observation "+obsvNumber+":");
		//POR AS COISAS NO FORMATO QUE ELA QUER - PERGUNTAR SE TEM QUE SER IGUALLZINHO
			}

	
	public String printResult() {
		return "Path of the best fit individual = "+bestInd.toString(); 
	}
}
