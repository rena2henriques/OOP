package specific;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class is a handler for the SAX xml parser and retrieves the needed variables from the XML and
 * uses them to initialize some parameters of the simulation
 * 
 * It also prevents some errors in the input values of the XML
 * 
 *
 */
public class MyHandler extends DefaultHandler {

	boolean bZone = false;
	
	private GridSimulation simulation;
	
	// Variables needed to save to be able to initialize the population class inside the simulation
	private Map map;
	private List<Individual> individuals;
	private int death, reproduction, move, comfortsens;
	private int xiniSCZ, yiniSCZ, xfinSCZ, yfinSCZ;

	/**
	* Constructor
	* @param simulation
	*/
	public MyHandler(GridSimulation simulation) {
			super();
			// associates simulation reference
			this.simulation = simulation;
		}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException,NumberFormatException {

		// simulation variables
		if (qName.equalsIgnoreCase("simulation")) {
	    	  
			int finalinst = Integer.parseInt(attributes.getValue("finalinst"));
			int initpop = Integer.parseInt(attributes.getValue("initpop"));
			int maxpop = Integer.parseInt(attributes.getValue("maxpop"));
			comfortsens = Integer.parseInt(attributes.getValue("comfortsens"));
	         
			if(finalinst < 0 || initpop < 0 || maxpop < 0 || comfortsens <= 0) {
				System.err.println("simulation attributes received from XML are negative or zero when they shouldn't.");
				System.exit(-1);
			}
	         
	         // sets the initial population
	         simulation.initPop=initpop;
	         
			// constructs the list of individuals
			individuals = new LinkedList<Individual>();
	         
	         // sets the maximum population
	         simulation.maxInd=maxpop;
	         
	         // sets the final instant of the simulation
	         simulation.setFinalTime(finalinst);
	         
	      // map variables
	      } else if (qName.equalsIgnoreCase("grid")) {
	    	  
	    	  int colsnb = Integer.parseInt(attributes.getValue("colsnb"));
		      int rowsnb = Integer.parseInt(attributes.getValue("rowsnb"));
		      
		      if (colsnb <= 0 || rowsnb <= 0) {
		    	  System.err.println("Dimensions of grid in XML are negative or 0!");
		    	  System.exit(-1);
		      }
		      
		      // Constructs the Map Object
		      map = new Map(colsnb, rowsnb);
		      
		  // initial point coordinates
	      } else if (qName.equalsIgnoreCase("initialpoint")) {
	    	  
	    	  int xinitial = Integer.parseInt(attributes.getValue("xinitial"));
		      int yinitial = Integer.parseInt(attributes.getValue("yinitial"));
		      
		      if( xinitial < 0 || yinitial < 0) {
		    	  System.err.println("Coordinates received in XML are negative!");
		    	  System.exit(-1);
		      } else if (xinitial > map.width || yinitial > map.height) {
		    	  System.err.println("Coordinates received in XML are out of boundaries!");
		    	  System.exit(-1);
		      }
		      
		      simulation.initialPoint=new Point(xinitial, yinitial);
		   
		      map.addInitialPoint(xinitial, yinitial);
		      
		  // final point coordinates
	      } else if (qName.equalsIgnoreCase("finalpoint")) {
	    	  
	    	  int xfinal = Integer.parseInt(attributes.getValue("xfinal"));
		      int yfinal = Integer.parseInt(attributes.getValue("yfinal"));
		      
		      if(xfinal < 0 || yfinal < 0) {
		    	  System.err.println("Coordinates received in XML are negative!");
		    	  System.exit(-1);
		      } else if (xfinal > map.width || yfinal > map.height) {
		    	  System.err.println("Coordinates received in XML are out of boundaries!");
		    	  System.exit(-1);
		      }
		      
		      map.addFinalPoint(xfinal, yfinal);
		  
		  // special zone description
	      } else if (qName.equalsIgnoreCase("zone")) {
	         
	    	  xiniSCZ = Integer.parseInt(attributes.getValue("xinitial"));
		      yiniSCZ = Integer.parseInt(attributes.getValue("yinitial"));
		      xfinSCZ = Integer.parseInt(attributes.getValue("xfinal"));
		      yfinSCZ = Integer.parseInt(attributes.getValue("yfinal"));
		      
		      if( xfinSCZ < 0 || yfinSCZ < 0  || xiniSCZ < 0 || yiniSCZ < 0) {
		    	  System.err.println("Coordinates received in XML are negative!");
		    	  System.exit(-1);
		      } else if(xfinSCZ > map.width || yfinSCZ > map.height || xiniSCZ > map.width|| yiniSCZ > map.height) {
		    	  System.err.println("Coordinates received in XML are out of boundaries!");
		    	  System.exit(-1);
		      }
		      
		      // for triggering character reading
		      bZone = true;
	    	  
		  // num of obstacles
	      } else if (qName.equalsIgnoreCase("obstacles")) {
	    	  int num = Integer.parseInt(attributes.getValue("num"));
		      
	    	  if (num < 0) {
	    		  System.err.println("obstacles attributes received from XML are negative when they shouldn't.");
	    		  System.exit(-1);
	    	  }
	    	  
		      // set the number of obstacles in the map
		      map.n_obst=num;
		      
		  // obstacles coordinates  
	      } else if (qName.equalsIgnoreCase("obstacle")) {
	    	  int xpos = Integer.parseInt(attributes.getValue("xpos"));
		      int ypos = Integer.parseInt(attributes.getValue("ypos"));
		      
		      if( xpos < 0 || ypos < 0) {
		    	  System.err.println("Coordinates received in XML are negative!");
		    	  System.exit(-1);
		      } else if (xpos > map.width || ypos > map.height ) {
		    	  System.err.println("Coordinates received in XML are out of boundaries!");
		    	  System.exit(-1);
		      }
		      
		      if(simulation.initialPoint.getX() == xpos && simulation.initialPoint.getY() == ypos) {
		    	  System.err.println("Obstacle wasn't inserted because the coordinates requested were equal to the initial point!");
		      } else {
		    	// add an obstacle to the graph with the received connections
			      map.addObstacle(xpos, ypos);
		      }
		      
		  // death param
	      } else if (qName.equalsIgnoreCase("death")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      
	    	  if (param < 0) {
	    		  System.err.println("Parameters received in XML are negative!");
		    	  System.exit(-1); 
	    	  }
	    	  
		      this.death = param;
		      
		  // reproduction param
	      } else if (qName.equalsIgnoreCase("reproduction")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      
	    	  if (param < 0) {
	    		  System.err.println("Parameters received in XML are negative!");
		    	  System.exit(-1); 
	    	  }
	    	  
		      this.reproduction = param;
		      
		  // move param
	      } else if (qName.equalsIgnoreCase("move")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      
	    	  if (param < 0) {
	    		  System.err.println("Parameters received in XML are negative!");
		    	  System.exit(-1); 
	    	  }
	    	  
		      this.move = param;
	      }
	   }

	@Override
	public void characters(char ch[], int start, int length) throws SAXException, NumberFormatException {
	      
	      if (bZone) {
	    	  
	    	 int cost = Integer.parseInt(new String(ch, start, length));
	         
	         bZone = false;
	         
	         map.addSpecialZone(xiniSCZ, yiniSCZ, xfinSCZ, yfinSCZ, cost);
	      } 
	   }
	   
	@Override
	public void endDocument() throws SAXException {
		
		   // Initializes the population with all the parameters, the map and the list of individuals
		   Population population = new Population(comfortsens,death, move,reproduction,map,individuals);
		   
		   simulation.population=population;
	   }
	   
	@Override
	public void error(SAXParseException e) throws SAXException {
		   System.err.println("Error Message: " + e.getMessage());
		   System.err.println("Error in XML at Line: " + e.getLineNumber());
		   System.exit(-1);
	}
	   	   
}