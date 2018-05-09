package specific;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <p>This class is a handler for the SAX xml parser and retrieves the needed variables from the XML and
 * uses them to initialize some parameters of the simulation. It also prevents some errors in the input values of 
 * the XML.
 *
 */
public class MyHandler extends DefaultHandler {

	
	/**
	 * This variable is used to check if the parser is in the element of a specialcostzone in order to retrieve the cost value
	 */
	boolean bZone = false;
	
	/**
	 * A reference to the simulation in order to pass the initialized list of individuals and map to it 
	 */
	private GridSimulation simulation;
	
	/**
	 * Auxiliar map needed to initialize the population class inside the simulation
	 */
	private Map map;
	
	
	/**
	 * Auxiliar list of individuals needed to initialize the population class inside the simulation
	 */
	private List<Individual> individuals;
	
	
	/**
	 * Auxiliar parameter needed to save during the parsing because the initializion is done only in the end of the document
	 */
	private int death, reproduction, move, comfortsens;
	
	
	/**
	 * Auxiliar coordinate needed to save during the parsing because the initiliaztion is done only when the character is read
	 */
	private int xiniSCZ, yiniSCZ, xfinSCZ, yfinSCZ;

	/**
	* <p>Constructor. As the Class needs the a simulation to initiliaze the constructor receives a reference to one and it keeps it 
	* in the field simulation of the class
	* 
	* @param simulation - reference to an object of type GridSimulation
	*/
	public MyHandler(GridSimulation simulation) {
			super();
			// associates simulation reference
			this.simulation = simulation;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	/**
	 * <p> Used to read the value and the data of an element
	 * <p> Used to read the value and the data of an element. It prevents the elements to have certain values like coordinates
	 * being negative or 0 and the values not being integers. In these cases an error message is printed and the program is aborted.
	 * More cases of error handling can be checked in the project report.
	 * 
	 * @throws NumberFormatException - in order to avoid to use try catch statements in every parseInt method calls
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException,NumberFormatException {

		// simulation variables
		if (qName.equalsIgnoreCase("simulation")) {
	    	  
			int finalinst = Integer.parseInt(attributes.getValue("finalinst"));
			int initpop = Integer.parseInt(attributes.getValue("initpop"));
			int maxpop = Integer.parseInt(attributes.getValue("maxpop"));
			comfortsens = Integer.parseInt(attributes.getValue("comfortsens"));
	         
			if(finalinst < 0 || initpop < 0 || maxpop < 0 || comfortsens <= 0) {
				System.err.println("Simulation attributes received from XML are negative or zero when they shouldn't.");
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

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	/**
	 * <p>Reads the characters inside an element. In this case the only character read is the cost 
	 * value of the special cost zones
	 * 
	 * @throws NumberFormatException - in order to avoid to use try catch statements in every parseInt method calls
	 */
	@Override
	public void characters(char ch[], int start, int length) throws SAXException, NumberFormatException {
	      
	      if (bZone) {
	    	  
	    	 int cost = Integer.parseInt(new String(ch, start, length));
	         
	         bZone = false;
	         
	         map.addSpecialZone(xiniSCZ, yiniSCZ, xfinSCZ, yfinSCZ, cost);
	      } 
	   }
	   
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	/**
	 * Called when the parser reaches the end of the document. 
	 * <p>In this case it's used to initialize the population with the parsed
	 * parameters and then sent to the simulation
	 */
	@Override
	public void endDocument() throws SAXException {
		
		   // Initializes the population with all the parameters, the map and the list of individuals
		   Population population = new Population(comfortsens,death, move,reproduction,map,individuals);
		   
		   simulation.population=population;
	   }
	   
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
	 */
	/**
	 * Used for printing error messages when the xml doens't correspond to the dtd structure 
	 */
	@Override
	public void error(SAXParseException e) throws SAXException {
		   System.err.println("Error Message: " + e.getMessage());
		   System.err.println("Error in XML at Line: " + e.getLineNumber());
		   System.exit(-1);
	}
	   	   
}