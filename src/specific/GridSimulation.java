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

public class GridSimulation extends SimulationA{
	
	private Population population;
	private Point initialPoint;
	private Individual bestInd;
	private int maxInd, initPop;
	private boolean finalPointHit;

	public GridSimulation(File file) {

	      try {
	    	  SAXParserFactory factory = SAXParserFactory.newInstance();
	    	  SAXParser saxParser = factory.newSAXParser();
	    	  MyHandler handler = new MyHandler(this);
	    	  saxParser.parse(file, handler);     
		  } catch (IOException e) {
			  System.err.println("IO error"); 
			  return;
		  } catch (SAXException e) {
			  System.err.println("Parser error");
			  return;
		  } catch (ParserConfigurationException e) {
			  System.err.println("Parser Configuration error");
			  return;
		  }
		
		//call XML Parser
		pec = new PEC(6*initPop); //6*initPop is the initialcapacity of the priorityqueue;
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
		if(currentEvent!=null)
			currentTime=currentEvent.getTime();
		
		//simulation loop
		while(currentEvent!=null && currentTime< finalTime) {
			
			//simulate current event and add new list of events to pec
			eventList=currentEvent.simulateEvent(); //cast to?(LinkedList<Event>) 
			numEvents++;			
			addNewEvents(eventList);
			
			//checking best fit
			checkCurrentIndividual(((IndividualEvent) currentEvent).getIndividual());
			
		}
		
	}
	
	public void reset() {	
		super.init();		
		finalPointHit=false;
		bestInd=null;
		population.clearIndividuals();
	}
	
	public void initialize() {
		
		Individual newInd=null;
		
		//initializing the population 
		for(int i=0; i<initPop;i++) {
			
			newInd=new Individual(population, initialPoint);
			
			//first 3 events for each individual - death, move, reproduction
			Death death= new Death(RANDTIME,newInd);
			newInd.setIndDeath(death);
			pec.addEvent(death);			
			//S� MANDAR EVENTOS PARA A PEC SE O SEU TEMPO FOR INFERIOR AO DAMORTE
			pec.addEvent(new Move(RANDTIME,newInd));
			pec.addEvent(new Reproduction(RANDTIME,newInd));
	
			//adding individual to the population
			population.individuals.add(newInd);			
			
		}
		
		//add first observation
		pec.addEvent(new Observation(finalTime/20,this));
			
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
		return finalPointHit;
	}


}
