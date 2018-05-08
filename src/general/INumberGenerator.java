package general;

/**
 * Provides a method to get a number (random or deterministic) related to any type T
 * 
 * @author Grupo 6
 *
 * @param <T> it can extend class Number or any other class
 */
public interface INumberGenerator {

	/**
	 * @param t 
	 * @return a double related to object t
	 */
	double getNumber();
}
