package specific;

import java.util.LinkedList;
import java.util.List;

import general.Event;

public class Death extends IndividualEvent{

	public Death(double time, Individual ind) {
		super(time, ind);
	}
	
	public List<Event> simulateEvent() {
		//eliminar o gajo da lista?
		
		return null;		
	}
}
