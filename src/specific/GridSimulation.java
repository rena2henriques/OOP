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
import general.SimulationCommands;

/**
 * GridSimulation extends SimulationA and implements the simulate method. 
 * <p>
 * GridSimulation provides method to simulate with discrete time a population of individuals generated at instant zero an placed at an initial
 * point, that evolve until a final instant according to the events Death, Move and Reproduction. 
 * The population evolves in function of the individual evolution of its elements and also by the
 * occurrence of epidemics (method epidemic).
 * <p>
 * The simulation can only be created from parameters given in a XML file provided in the constructor.
 * It is also dependent on the number generators provided in the constructor, that decide whether it will 
 * follow random or deterministic mechanisms.
 * <p>
 * Since there are no static methods and an initialization is made in the beginning of the simulate method,
 * the object of the created Simulation can be run multiple times.
 * 
 */
public class GridSimulation extends SimulationA{
	
	/**
	 * Population associated to this simulation.
	 */
	protected Population population;
	
	/**
	 * Initial point where the individuals start the simulation.
	 */
	protected Point initialPoint;
	
	/**
	 * Maximum number of individuals used in the epidemic.
	 */
	protected int maxInd;
	
	/**
	 * Initial number of individuals.
	 */
	protected int initPop;
	
	/**
	 * Index of the generator between Death events in the SimulationCommand.
	 * <p>
	 * Defined to increase the readability in the code.
	 * @see SimulationCommands
	 */
	protected static final int DEATH=0;
	
	/**
	 * Index of the generator between Move events in the SimulationCommand.
	 * <p>
	 * Defined to increase the readability in the code.
	 * @see SimulationCommands
	 */
	protected static final int MOVE=1;
	
	/**
	 * Index of the generator between Reproduction events in the SimulationCommand.
	 * <p>
	 * Defined to increase the readability in the code.
	 * @see SimulationCommands
	 */
	protected static final int REP=2;
	
	/**
	 * Index of the double (between 0 and 1) generator in the SimulationCommand.
	 * <p>
	 * Defined to increase the readability in the code.
	 * @see SimulationCommands
	 */
	protected static final int THRESH=3;
	
	/**
	 * Constructor.
	 * Creates a GridSimulation from the specified file and number generators.
	 * <p>
	 * The SAX Parser is called in this constructor and a File with the filename received is opened. The validation of the parser
	 * is set to true in order to get its validity using the dtd. In case of error, many exceptions can be caught. IOException in 
	 * case of the file not being available, SAXException in case of the xml not being in accord with the dtd and also a 
	 * NumberFormatException in case of other variables are sent instead of just integers
	 * <p>
	 * Initializes the PEC with an initial capacity of 3*maxInd and creates a GridCommands with the provided INumberGenerators.
	 * 
	 * 
	 * @param filename - XML file that contains the parameters of this simulation
	 * @param deathTime - generator of the time between Death events
	 * @param moveTime - generator of the time between Move events
	 * @param repTime - generator of the time between Reproduction events
	 * @param thresh - generator of doubles between 0 and 1
	 */
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
		
		//creates a GridCommands object from the provided number generators.
		INumberGenerator gens[] = new INumberGenerator[4];
		gens[DEATH]=deathTime;
		gens[MOVE]=moveTime;
		gens[REP]= repTime;
		gens[THRESH]= thresh;
		
		simComms= new GridCommands(gens);
	}
	
	/* (non-Javadoc)
	 * @see general.SimulationI#simulate()
	 */
	/**
	 * The reset and startSimulation method are called, so that the PEC is filled with events to be simulated. 
	 * <p>
	 * After that, a simulation loop starts where in each cycle the current Event is simulated, the list 
	 * of new Events is added to the PEC and an epidemic occurs if the population exceeds the maximum number.
	 * The next event is removed from the PEC and the time is set to be the the time of that event.
	 * During the simulation loop the observations (made from finalTime/20 to finalTime/20) are printed to the terminal 
	 * as a result of simulating an Observation event.
	 * <p>
	 * The simulation finishes when the evolution final instant is reached, or if there are no more events to simulate,
	 * which means that there is only one observation in the PEC.
	 * If the simulation finishes before the final instant, only the observations until that instant are printed to the terminal.
	 * At the end of the simulation the path of the best fit individual is printed to the terminal.
	 * 
	 */
	
	public void simulate() {
		
		if(this.finalTime==0) {
			System.out.println("The final instant is 0, so there is no simulation");
			System.exit(0);
		}
		
		//reseting the dynamic variables and initializing the population of individuals
		reset();
		startSimulation();	
		
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
	
	/**
	 * Checks if an epidemic should occur. Returns true if the size of the current population is bigger than the maximum population
	 * 
	 * @return true if the size of the current population is bigger than the maximum population
	 */
	private boolean checkEpidemic() {
		return population.individuals.size()> maxInd;
	}
	
	
	/**
	 * 	In this method the five individuals with greater comfort are untouched.
	 * 	The 5 best individuals are determined by sorting the list of Individuals using the IndividualComfortComparator, 
	 *  so they are sorted in descendant order according to their comfort.
	 *  <p>
	 *  For each of the remaining individuals in the sorted list, a number between 0 and 1 is generated and if it's higher than the comfort of the individual,
	 *  he is removed from the population and his events are removed from the PEC with the clearDeadEvents method.
	 *  <p>
	 * 	This method is only used when checkEpidemic returns true.
	 * 
	 */
	private void epidemic() {
						
		int epidemic_size=5;
		
		population.individuals.sort(new IndividualComfortComparator()); //escolher os melhores 5
		
		Individual ind=null;
		
		Iterator<Individual> i=population.individuals.iterator();
		
		// ignore the first 5 values
		for (int x=0; x < epidemic_size && i.hasNext(); x++) {
	        i.next(); 
	    }
		
		for(;i.hasNext();) {

			ind=i.next();
			double percentage=simComms.getCommand(THRESH);
	
			//we dont check if k=0 because its verified in the xml parser
			//if the percentage is higher than the comfort, the individuals dies
			if(percentage>ind.comfort) {
						
				//clears dead individual events
				clearDeadEvents(ind);
				
				//to avoid concurrent modification exception
				i.remove();
			}
		}
	}
	
	/**
	 * Checks if the individual that will be killed by epidemic, has events on the PEC
	 * and erases them if it has
	 * 
	 * 	@param ind - individual that passes away and whose events are going to be removed from the PEC
	 */
	private void clearDeadEvents(Individual ind) {
		
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
	
	/**
	 * The variables that change when the simulation is performed are reseted.
	 * <p>
	 * The init method of the superclass is called, the list of individuals is cleared,
	 * the best fit is set to null and the flag finalPointHit to false.
	 * <p>
	 * Should be called before starting an actual simulation.
	 * 
	 */
	public void reset() {	
		super.init();		
		population.finalPointHit=false;
		population.bestInd=null;
		population.clearIndividuals();
	}
	
	/**
	 * Initialization of the simulation. 
	 * <p>
	 * The initial list of individuals is created in the initial point, and the first 3 events of each individual are added to the PEC (Death, first Move and first Reproduction).
	 * The first Observation is added to the PEC and the best fit Individual is initialized.
	 * <p>
	 * Should be called before starting an actual simulation
	 * 
	 */
	public void startSimulation() {
		
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
	
	/**
	 * Returns the path of the best fit individual.
	 * 
	 * @return the path of the best fit individual
	 */
	public List<Point> getResult() {
		return population.bestInd.path;
	}
	
	/**
	 * The path of the best fit individual in the end of the simulation is printed to the terminal.
	 */
	protected void printResult() {	
		try {
			System.out.println("Path of the best fit individual = "+population.bestInd.pathString());

		} catch (NullPointerException e) {
			System.out.println("There is no individual to simulate events.");
		}
		 
	}

	/**
	 * Returns true if the final point has been hit.
	 * 
	 * @return true if the final point has been hit and false if not
	 * 
	 */
	public boolean isFinalPointHit() {
		return population.finalPointHit;	
	}
	
}
