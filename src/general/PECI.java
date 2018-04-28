package general;

/**
 * 
 *
 * @author Grupo 6
 *
 * @param <E> 
 */
public interface PECI<E extends EventI>{
	
	//INTERFACE CASO QUEIRAMOS TER OUTRO TIPO DE CONTAINERS COM OS EVENTOS OU CASO QUEIRAMOS TER OUTRO TIPO DE EVENTS
	//ACEITA QUALQUER TIPO DE EVENTOS QUE IMPLEMENTE A CLASSE EVENTI!!
	public void addEvent(E e);
	public E nextEvent(); 
	
}