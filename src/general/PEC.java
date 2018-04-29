package general;

import java.util.PriorityQueue;

public class PEC implements PECI<Event>{
	
	//ver como implementar comparador
	PriorityQueue<Event> events;
	
	public PEC() {
		events= new PriorityQueue<Event>();
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
