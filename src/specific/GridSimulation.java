package specific;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import general.SimulationA;
import general.Event;
import general.PEC;
import general.INumberGenerator;

public class GridSimulation extends SimulationA{
	
	protected Population population;
	protected Point initialPoint;
	protected int maxInd, initPop;
	protected static final int DEATH=0;
	protected static final int MOVE=1;
	protected static final int REP=2;
	protected static final int THRESH=3;

	
	public GridSimulation(String filename,INumberGenerator deathTime,INumberGenerator moveTime,INumberGenerator repTime ,INumberGenerator thresh) {
	
	      try {
	    	  File file = new File(filename);
	    	  SAXParserFactory factory = SAXParserFactory.newInstance();
	    	  factory.setValidating(true);
	    	  SAXParser saxParser = factory.newSAXParser();
	    	  MyHandler handler = new MyHandler(this);
	    	  saxParser.parse(file, handler);     
		  } catch (IOException e) {
			  System.err.println("IO error: File doesn't exist"); 
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
		
	    // If initPop is zero means that no event will be simulated, cause there's no individual to have events
	    if (initPop == 0) {
	    	System.out.println("The initial population is zero, so there are no individuals to associate events to.");
	    	System.exit(0);
	    }
	      
		//call XML Parser
		pec = new PEC(3*maxInd); //3*maxInd is the initial capacity of the priority queue;
		
		INumberGenerator gens[] = new INumberGenerator[4];
		gens[DEATH]=deathTime;
		gens[MOVE]=moveTime;
		gens[REP]= repTime;
		gens[THRESH]= thresh;
		
		simComms= new GridCommands(gens);
	}
	
	public void simulate() {
		
		if(this.finalTime==0) {
			System.out.println("The final instant is 0, so there is no simulation");
			System.exit(0);
		}
		
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
		
		//simulate final observation
		if(population.individuals.size()!=0)
			eventList=currentEvent.simulateEvent(); 
		//printing final results of the simulation
		printResult(); 
	}
	
	private boolean checkEpidemic() {
		return population.individuals.size()> maxInd;
	}
	
	
	private void epidemic() {
						
		int epidemic_size=5;

		
		population.individuals.sort(new IndividualComfortComparator()); //escolher os melhores 5
		
		Individual ind=null;
		//para os restantes fazer um for em que percorro e calculo se morrem ou n�o
		//for(int i=5; i<population.individuals.size(); i++) {
		
		Iterator<Individual> i=population.individuals.iterator();
		
		for (int x=0; x < epidemic_size && i.hasNext(); x++) {
	        i.next(); // ignore the first x values
	    }
		
		for(;i.hasNext();) {

			//ind=population.individuals.get(i);
			ind=i.next();
			//double percentage= simGenerator.getThreshold(ind);
			double percentage=simComms.getCommand(THRESH);
	
			//we dont check if k=0 because its verified in the xml parser
			if(percentage>ind.comfort) {
						
				//clears dead individual events
				clearDeadEvents(pec, ind);
				
				//to avoid concurrent modification exception
				i.remove();
			}
		}
	}
	
	/**
	 * Checks if individual that will be killed by epidemy, has events on the pec
	 * and erases them
	 * 
	 * @param pec
	 * @param ind
	 */
	private void clearDeadEvents(PEC pec, Individual ind) {
		
		/*if the individual has an event associated to him, with time
		 * higher than the simulation time, that event is not in the pec
		 * it's just old information
		 * otherwise it's deleted from the pec 
		 */
		
		if(ind.nextMove != null)
			if(ind.nextMove.getTime() < finalTime) 
				pec.removeEvent(ind.nextMove);
		
		if(ind.nextRep != null)
			if(ind.nextRep.getTime() < finalTime)	
				pec.removeEvent(ind.nextRep);
	
		if(ind.myDeath != null) 
			if(ind.myDeath.getTime() < finalTime)
				pec.removeEvent(ind.myDeath);
		
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
			double eventTime = ((GridCommands) simComms).getCommand(DEATH,newInd);
			if(eventTime < finalTime) {
				Death death = new Death(eventTime,newInd, simComms);
				newInd.myDeath=death;
				pec.addEvent(death);
			}
			//So MANDAR EVENTOS PARA A PEC SE O SEU TEMPO FOR INFERIOR AO DAMORTE e de simTime
			eventTime=((GridCommands) simComms).getCommand(MOVE,newInd);
			if(IndividualEvent.checkDeathTime(eventTime, newInd) && eventTime <= finalTime) {
				Move move = new Move(eventTime,newInd, simComms);
				pec.addEvent(move);
				newInd.nextMove=move;
			}
			eventTime=((GridCommands) simComms).getCommand(REP,newInd);
			if(IndividualEvent.checkDeathTime(eventTime, newInd) && eventTime <= finalTime) {
				Reproduction rep = new Reproduction(eventTime,newInd, simComms);
				pec.addEvent(rep);
				newInd.nextRep=rep;
			}
	
			//adding individual to the population
			population.individuals.add(newInd);			
			
		}
		
		population.bestInd=population.getIndividuals().get(0).getPathIndividual();
		
		//in case of having initPop bigger than maxInd
		if(checkEpidemic()) {
			//checking epidemics
			epidemic();
		}
		
		//add first observation
		pec.addEvent(new ObservationEvent(finalTime/20,this));
			
	}
	
	public List<Point> getResult() {
		return population.bestInd.path;
	}
	
	public void printResult() {	
		try {
			System.out.println("Path of the best fit individual = "+population.bestInd.pathString());
		} catch (NullPointerException e) {
			System.out.println("There is no individual to simulate events.");
		}
		 
	}

	public boolean isFinalPointHit() {
		return population.finalPointHit;	
	}
	
	/*public static void main(String[] args) {
	
		GridSimulation simulation = new GridSimulation("projectexample.xml", null);
		
		System.out.println(simulation.initialPoint);
	}*/


}
