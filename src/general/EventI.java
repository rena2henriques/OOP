package general;

import java.util.List;

public interface EventI {
	
	List<? extends EventI> simulateEvent();

}
