package specific;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import general.SimulationA;
import general.Event;
import general.PEC;
import general.Point;

public class GridSimulation extends SimulationA{
	
	private Population population;
	private Point initialPoint;
	private int maxInd, initPop;
	private SimulationNumberCommands simGenerator; 

	public GridSimulation(String filename, SimulationNumberCommands generator) {

	      try {
	    	  File file = new File(filename);
	    	  SAXParserFactory factory = SAXParserFactory.newInstance();
	    	  factory.setValidating(true);
	    	  SAXParser saxParser = factory.newSAXParser();
	    	  MyHandler handler = new MyHandler(this);
	    	  saxParser.parse(file, handler);     
		  } catch (IOException e) {
			  System.err.println("IO error"); 
			  System.exit(-1);
		  } catch (SAXException e) {
			  System.err.println("Parser error");
			  System.exit(-1);
		  } catch (ParserConfigurationException e) {
			  System.err.println("Parser Configuration error");
			  System.exit(-1);
		  } catch(NumberFormatException e) {
			  System.err.println("XML has an invalid Integer to parse");
			  System.exit(-1);
		  }
		
	     System.out.println(initialPoint);
		//call XML Parser
		pec = new PEC(6*initPop); //6*initPop is the initialcapacity of the priority queue;
		simGenerator= generator;
	}
	
	public void simulate() {
		
		//reseting the dynamic variables and initializing the population of individuals
		reset();
		initialize();	
		
		//local variables
		List<Event> eventList = new LinkedList<Event>(); //list of events returned in the simulateEvent
		Event currentEvent= null; // current event 
		
		//initializing the simulation with the 1st event in pec
		currentEvent=pec.nextEvent();			
		currentTime=currentEvent.getTime();
		
		//simulation loop
		while(!pec.isEmpty() && currentTime< finalTime) {
			
			//simulate current event and add new list of events to pec
			eventList=currentEvent.simulateEvent(); 
			numEvents++;			
			addNewEvents(eventList);
			
			//checking epidemics
			if(checkEpidemic())
				epidemic();
					 
			//next event
			currentEvent=pec.nextEvent();			
			currentTime=currentEvent.getTime();	
			
		}
		
		//printing final results of the simulation
		printResult(); 
	}
	
	private boolean checkEpidemic() {
		return population.individuals.size()> maxInd;
	}
	
	
	private void epidemic() {
		
		population.individuals.sort(new IndividualComfortComparator()); //escolher os melhores 5
		
		Individual ind=null;
		//para os restantes fazer um for em que percorro e calculo se morrem ou n�o
		for(int i=5; i<population.individuals.size(); i++) {
			ind=population.individuals.get(i); 
			if(simGenerator.getThreshold(ind)>ind.getComfort()) {
				//percorrer a pec e retirar todos os eventos do individual morto
				for(Event event: pec.getEvents()) {
					if(event.peekEvent(ind))
						pec.removeEvent(event);
				}
				
				//retirar individual da lista de individuals
				population.individuals.remove(ind);
			}
		}
	}
	
	public void reset() {	
		super.init();		
		population.finalPointHit=false;
		population.bestInd=null;
		population.clearIndividuals();
	}
	
	public void initialize() {
		
		Individual newInd=null;
		
		//initializing the population 
		for(int i=0; i<initPop;i++) {
			
			newInd=new Individual(population, initialPoint);
			
			//first 3 events for each individual - death, move, reproduction
			Death death= new Death(simGenerator.getDeathTime(newInd),newInd, simGenerator);
			newInd.setIndDeath(death);
			pec.addEvent(death);			
			//So MANDAR EVENTOS PARA A PEC SE O SEU TEMPO FOR INFERIOR AO DAMORTE e de simTime
			double eventTime=simGenerator.getMoveTime(newInd);
			if(IndividualEvent.checkDeathTime(eventTime, newInd) && eventTime <= finalTime);
				pec.addEvent(new Move(eventTime,newInd, simGenerator));
			eventTime=simGenerator.getReproductionTime(newInd);
			if(IndividualEvent.checkDeathTime(eventTime, newInd) && eventTime <= finalTime);
				pec.addEvent(new Reproduction(eventTime,newInd, simGenerator));
	
			//adding individual to the population
			population.individuals.add(newInd);			
			
		}
		
		//add first observation
		pec.addEvent(new ObservationEvent(finalTime/20,this));
			
	}
	/*
	private void checkBestFitIndividual(Individual currentInd) {
		
		//if none of the individuals has reached the final point before and the current individual
		//reaches it, we change the flag and set the current individual as the best individual
		if(!finalPointHit && population.map.isFinal(currentInd.getPath().get(currentInd.getPath().size()-1))) {
			finalPointHit=true; 
			updateBestFit(currentInd);			
		}
		
		//we only check if it is the best individual if the final point hasn't been reached 
		//or if it has, we only check if our individual is also in the final point
		else if(!finalPointHit || (finalPointHit &&  population.map.isFinal(currentInd.getPath().get(currentInd.getPath().size()-1)))) {
			if(checkIfIsBestFit(currentInd))
				updateBestFit(currentInd);			
		}
	}
	
	private boolean checkIfIsBestFit(Individual currentInd) {
		
		if(bestInd==null)
			return true;
		
		//if none of the individuals has reached the final point we check the comfort
		else if(!finalPointHit && currentInd.getComfort()>bestInd.getComfort()) {
			return true;
		} 
		//if an individual has already reached the final point, we check the cost
		else if(finalPointHit && currentInd.getCost() < bestInd.getCost()) {
			return true;
		}
		
		return false;
	}
	
	private void updateBestFit(Individual currentInd) {
		try {
		bestInd=(Individual) currentInd.clone(); 	
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	*/
	
	public List<Point> getResult() {
		return population.bestInd.getPath();
	}
	
	public void printResult() {
		System.out.println("Path of the best fit individual = "+population.bestInd.toString()); 
	}

	public Point getInitialPoint() {
		return initialPoint;
	}
	
	public void setInitialPoint(Point p) {
		initialPoint=p;
	}
	
	public Population getPopulation() {
		return population;
	}
	
	public void setPopulation(Population population) {
		this.population=population;
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
		return population.finalPointHit;	
	}
	
	public static void main(String[] args) {
	
		GridSimulation simulation = new GridSimulation("projectexample.xml", null);
		
		System.out.println(simulation.initialPoint);
	}


}
