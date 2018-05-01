package specific;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
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
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		  // simulation variables
	      if (qName.equalsIgnoreCase("simulation")) {
	    	  
	         int finalinst = Integer.parseInt(attributes.getValue("finalinst"));
	         //System.out.println("Finalinst : " + finalinst);
	         int initpop = Integer.parseInt(attributes.getValue("initpop"));
	         //System.out.println("Initpop : " + initpop);
	         int maxpop = Integer.parseInt(attributes.getValue("maxpop"));
	         //System.out.println("Maxpop : " + maxpop);
	         comfortsens = Integer.parseInt(attributes.getValue("comfortsens"));
	         //System.out.println("Comfortsens : " + comfortsens);
	         
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
		      //System.out.println("Colsnb : " + colsnb);
		      int rowsnb = Integer.parseInt(attributes.getValue("rowsnb"));
		      //System.out.println("Rowsnb : " + rowsnb);
		      
		      // Constructs the Map Object
		      map = new Map(colsnb, rowsnb);
		      
		  // initial point coordinates
	      } else if (qName.equalsIgnoreCase("initialpoint")) {
	    	  
	    	  int xinitial = Integer.parseInt(attributes.getValue("xinitial"));
		      //System.out.println("Xinitial : " + xinitial);
		      int yinitial = Integer.parseInt(attributes.getValue("yinitial"));
		      //System.out.println("Yinitial : " + yinitial);
		   
		      map.addInitialPoint(xinitial, yinitial);
		      
		  // final point coordinates
	      } else if (qName.equalsIgnoreCase("finalpoint")) {
	    	  
	    	  int xfinal = Integer.parseInt(attributes.getValue("xfinal"));
		      //System.out.println("Xfinal : " + xfinal);
		      int yfinal = Integer.parseInt(attributes.getValue("yfinal"));
		      //System.out.println("Yfinal : " + yfinal);
		      
		      map.addFinalPoint(xfinal, yfinal);
		  
		  // special zone description
	      } else if (qName.equalsIgnoreCase("zone")) {
	         
	    	  xiniSCZ = Integer.parseInt(attributes.getValue("xinitial"));
		      //System.out.println("Xinitial : " + xiniSCZ);
		      yiniSCZ = Integer.parseInt(attributes.getValue("yinitial"));
		      //System.out.println("Yinitial : " + yiniSCZ);
		      xfinSCZ = Integer.parseInt(attributes.getValue("xfinal"));
		      //System.out.println("Xfinal : " + xfinSCZ);
		      yfinSCZ = Integer.parseInt(attributes.getValue("yfinal"));
		      //System.out.println("Yfinal : " + yfinSCZ);
		      
		      // for triggering character reading
		      bZone = true;
	    	  
		  // num of obstacles
	      } else if (qName.equalsIgnoreCase("obstacles")) {
	    	  int num = Integer.parseInt(attributes.getValue("num"));
		      //System.out.println("Num of obstacles : " + num);
		      
		      // set the number of obstacles in the map
		      map.setN_obst(num);
		      
		  // obstacles coordinates  
	      } else if (qName.equalsIgnoreCase("obstacle")) {
	    	  int xpos = Integer.parseInt(attributes.getValue("xpos"));
		      //System.out.println("Xpos : " + xpos);
		      int ypos = Integer.parseInt(attributes.getValue("ypos"));
		      //System.out.println("Ypos : " + ypos);
		      
		      // add an obstacle to the graph with the received connections
		      map.addObstacle(xpos, ypos);
		      
		  // death param
	      } else if (qName.equalsIgnoreCase("death")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      System.out.println("Death param : " + param);
		      
		      this.death = param;
		      
		  // reproduction param
	      } else if (qName.equalsIgnoreCase("reproduction")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      System.out.println("Reproduction param : " + param);
		      
		      this.reproduction = param;
		      
		  // move param
	      } else if (qName.equalsIgnoreCase("mutacao")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      System.out.println("Mutation param : " + param);
		      
		      this.move = param;
	      }
	   }

	   @Override
	   public void endElement(String uri, String localName, String qName) throws SAXException {
	      if (qName.equalsIgnoreCase("student")) {
	         System.out.println("End Element :" + qName);
	      }
	   }

	   @Override
	   public void characters(char ch[], int start, int length) throws SAXException {
	      
	      if (bZone) {
	    	  
	    	 int cost = Integer.parseInt(new String(ch, start, length));
	         System.out.println("Zone cost: " + cost);
	         
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
	   
	}