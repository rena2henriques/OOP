package general;

import java.util.PriorityQueue;
import java.util.Queue;


//IMPLEMENTACAO DA PECI, só funciona com eventos do tipo event, que têm um time
//compara eventos obrigatoriamente a partir do tempo

public class PEC implements PECI<Event>{  
	
	private Queue<Event> events; //PROTECTED? , QUEUE OU LIST?
	
	public PEC() {
		events= new PriorityQueue<Event>(new TimeEventComparator());
	}
	
	public PEC(int initialCapacity) {
		events= new PriorityQueue<Event>(initialCapacity,new TimeEventComparator());
	}
	
	public Queue<Event> getEvents() {
		return events;
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
	
	public void removeEvent(Event event) {

		if(!events.remove(event)) {
			System.err.println("Error: try to remove an event that doesn't exist");	
		}
	}
	
	public boolean isEmpty() {
		return events.isEmpty();
	}
	
	
	
}
