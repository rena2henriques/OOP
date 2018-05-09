package general;

import java.util.Random;

/**
 *  Class with static methods in order to facilitate the generation of random numbers.
 * 
 * @author Group 6
 *
 */
public class Utils {

	/**
	 *Returns an uniform random in the chosen interval 
	 *
	 * @param nmin minimum bound
	 * @param nmax maximum bound
	 * @return uniform random between nmin and nmax
	 */
	public static double getUniformRandom(int nmin, int nmax) {
		Random rand= new Random();
		return rand.nextDouble()*(nmax-nmin)+nmin;
	}
	
	
	/**
	 * Returns an observation of an exponential random variable with mean value mean.
	 * 
	 * @param mean mean value of the exponential distribution
	 * @return  observation of an exponential random variable
	 * 
	 */
	public static double getExpRandom(double mean) {
		Random rand= new Random();
		return -mean*Math.log(1.0-rand.nextDouble());
		}
	
}
