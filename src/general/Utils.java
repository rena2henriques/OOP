package general;

import java.util.Random;

public final class Utils {

	public static double getRandom(int n) {
		Random rand= new Random();
		return rand.nextDouble(n);//VER ISTO

	}
}
