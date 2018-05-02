package specific;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

	boolean bZone = false;
	   
	GridSimulation simulation;
	
	Map map;
	
	List<Individual> individuals;
	
	int death, reproduction, move, comfortsens;
	
	int xiniSCZ, yiniSCZ, xfinSCZ, yfinSCZ;

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
	         
	         // sets the initial population
	         simulation.setInitPop(initpop);
	         
	         // constructs the list of individuals
	         individuals = new LinkedList<Individual>();
	         
	         // sets the maximum population
	         simulation.setMaxInd(maxpop);
	         
	         // sets the final instant of the simulation
	         simulation.setFinalTime(finalinst);
	         
	      // map variables
	      } else if (qName.equalsIgnoreCase("grid")) {
	    	  
	    	  int colsnb = Integer.parseInt(attributes.getValue("colsnb"));
		      int rowsnb = Integer.parseInt(attributes.getValue("rowsnb"));
		      
		      // Constructs the Map Object
		      map = new Map(colsnb, rowsnb);
		      
		  // initial point coordinates
	      } else if (qName.equalsIgnoreCase("initialpoint")) {
	    	  
	    	  int xinitial = Integer.parseInt(attributes.getValue("xinitial"));
		      int yinitial = Integer.parseInt(attributes.getValue("yinitial"));
		   
		      map.addInitialPoint(xinitial, yinitial);
		      
		  // final point coordinates
	      } else if (qName.equalsIgnoreCase("finalpoint")) {
	    	  
	    	  int xfinal = Integer.parseInt(attributes.getValue("xfinal"));
		      int yfinal = Integer.parseInt(attributes.getValue("yfinal"));
		      
		      map.addFinalPoint(xfinal, yfinal);
		  
		  // special zone description
	      } else if (qName.equalsIgnoreCase("zone")) {
	         
	    	  xiniSCZ = Integer.parseInt(attributes.getValue("xinitial"));
		      yiniSCZ = Integer.parseInt(attributes.getValue("yinitial"));
		      xfinSCZ = Integer.parseInt(attributes.getValue("xfinal"));
		      yfinSCZ = Integer.parseInt(attributes.getValue("yfinal"));
		      
		      // for triggering character reading
		      bZone = true;
	    	  
		  // num of obstacles
	      } else if (qName.equalsIgnoreCase("obstacles")) {
	    	  int num = Integer.parseInt(attributes.getValue("num"));
		      
		      // set the number of obstacles in the map
		      map.setN_obst(num);
		      
		  // obstacles coordinates  
	      } else if (qName.equalsIgnoreCase("obstacle")) {
	    	  int xpos = Integer.parseInt(attributes.getValue("xpos"));
		      int ypos = Integer.parseInt(attributes.getValue("ypos"));
		      
		      // add an obstacle to the graph with the received connections
		      map.addObstacle(xpos, ypos);
		      
		  // death param
	      } else if (qName.equalsIgnoreCase("death")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      
		      this.death = param;
		      
		  // reproduction param
	      } else if (qName.equalsIgnoreCase("reproduction")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      
		      this.reproduction = param;
		      
		  // move param
	      } else if (qName.equalsIgnoreCase("mutacao")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      
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
		   
		   simulation.setPopulation(population);
	   }
	   
	   @Override
	   public void error(SAXParseException e) throws SAXException {
		   System.err.println("Error Message: " + e.getMessage());
		   System.exit(-1);
	   }
	   
	   
	}