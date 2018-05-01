package specific;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

	   boolean bZone = false;

	   @Override
	   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		  // simulation variables
	      if (qName.equalsIgnoreCase("simulation")) {
	    	  
	         int finalinst = Integer.parseInt(attributes.getValue("finalinst"));
	         System.out.println("Finalinst : " + finalinst);
	         int initpop = Integer.parseInt(attributes.getValue("initpop"));
	         System.out.println("Initpop : " + initpop);
	         int maxpop = Integer.parseInt(attributes.getValue("maxpop"));
	         System.out.println("Maxpop : " + maxpop);
	         int comfortsens = Integer.parseInt(attributes.getValue("comfortsens"));
	         System.out.println("Comfortsens : " + comfortsens);
	         
	      // map variables
	      } else if (qName.equalsIgnoreCase("grid")) {
	    	  
	    	  int colsnb = Integer.parseInt(attributes.getValue("colsnb"));
		      System.out.println("Colsnb : " + colsnb);
		      int rowsnb = Integer.parseInt(attributes.getValue("rowsnb"));
		      System.out.println("Rowsnb : " + rowsnb);
		      
		  // initial point coordinates
	      } else if (qName.equalsIgnoreCase("initialpoint")) {
	    	  
	    	  int xinitial = Integer.parseInt(attributes.getValue("xinitial"));
		      System.out.println("Xinitial : " + xinitial);
		      int yinitial = Integer.parseInt(attributes.getValue("yinitial"));
		      System.out.println("Yinitial : " + yinitial);
		   
		  // final point coordinates
	      } else if (qName.equalsIgnoreCase("finalpoint")) {
	    	  
	    	  int xfinal = Integer.parseInt(attributes.getValue("xfinal"));
		      System.out.println("Xfinal : " + xfinal);
		      int yfinal = Integer.parseInt(attributes.getValue("yfinal"));
		      System.out.println("Yfinal : " + yfinal);
		  
		  // special zone description
	      } else if (qName.equalsIgnoreCase("zone")) {
	         
	    	  int xinitial = Integer.parseInt(attributes.getValue("xinitial"));
		      System.out.println("Xinitial : " + xinitial);
		      int yinitial = Integer.parseInt(attributes.getValue("yinitial"));
		      System.out.println("Yinitial : " + yinitial);
		      int xfinal = Integer.parseInt(attributes.getValue("xfinal"));
		      System.out.println("Xfinal : " + xfinal);
		      int yfinal = Integer.parseInt(attributes.getValue("yfinal"));
		      System.out.println("Yfinal : " + yfinal);
		      
		      // for triggering character reading
		      bZone = true;
	    	  
		  // num of obstacles
	      } else if (qName.equalsIgnoreCase("obstacles")) {
	    	  int num = Integer.parseInt(attributes.getValue("num"));
		      System.out.println("Num of obstacles : " + num);
		  // obstacles coordinates  
	      } else if (qName.equalsIgnoreCase("obstacle")) {
	    	  int xpos = Integer.parseInt(attributes.getValue("xpos"));
		      System.out.println("Xpos : " + xpos);
		      int ypos = Integer.parseInt(attributes.getValue("ypos"));
		      System.out.println("Ypos : " + ypos);
		  // death param
	      } else if (qName.equalsIgnoreCase("death")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      System.out.println("Death param : " + param);
		      
		  // reproduction param
	      } else if (qName.equalsIgnoreCase("reproduction")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      System.out.println("Reproduction param : " + param);
		      
		  // mutation param
	      } else if (qName.equalsIgnoreCase("mutacao")) {
	    	  int param = Integer.parseInt(attributes.getValue("param"));
		      System.out.println("Mutation param : " + param);
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
	         System.out.println("Zone cost: " + new String(ch, start, length));
	         bZone = false;
	      } 
	   }
	   
	   public static void main(String[] args) {

		      try {
		         File inputFile = new File("projectexample.xml");
		         SAXParserFactory factory = SAXParserFactory.newInstance();
		         SAXParser saxParser = factory.newSAXParser();
		         MyHandler handler = new MyHandler();
		         saxParser.parse(inputFile, handler);     
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
		   }   
	   
	}