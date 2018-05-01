package general;

import java.util.PriorityQueue;
import java.util.Queue;


//IMPLEMENTACAO DA PECI, só funciona com eventos do tipo event, que têm um time

public class PEC implements PECI<Event>{  
	
	private Queue<Event> events; //PROTECTED? , QUEUE OU LIST?
	
	public PEC() {
		events= new PriorityQueue<Event>();
	}
	
	public PEC(int initialCapacity) {
		events= new PriorityQueue<Event>(initialCapacity);
	}
	
	public void addEvent(Event e) {
		events.add(e);
	}
	
	public Event nextEvent() {
		Event event= events.poll(); //POLL OU REMOVE??? -- ADICIONAR EXCEPTION NO SIMULATOR
		return event;
	}
	
	public Event getEvent() {
		return events.peek(); 
	}
	
	public void clear() {
		events.clear();
	}
	
}
