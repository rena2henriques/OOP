package general;

import java.util.Random;

public final class Utils {

	/**
	 * @param n max bound
	 * @return uniform random between 0 and n
	 */
	public static double getUniformRandom(int nmin, int nmax) {
		Random rand= new Random();
		return rand.nextDouble()*(nmax-nmin)+nmin;
	}
	
	
	/**
	 * @param mean mean value of the exponential distribution
	 * @return  observation of an exponential random variable
	 * 
	 */
	public static double getExpRandom(double mean) {
		Random rand= new Random();
		return -mean*Math.log(1.0-rand.nextDouble());
		}
	
}
