/**
 * Provides recursive and iterative implementations of summation function.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 */
public class Summation {

	/** Returns the sum of 1..n for n > 0. */
	public static int sumI(int n) {

		if (n == 1){
			return 1;

		}

		return  n * sumI(n-1);
	}

	/** Returns the sum of 1..n  */
	public static int sumR(int n) {
		return 0;
	}

	/** Drives execution. */
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			int s1 = sumI(i);
			int s2 = sumR(i);
			System.out.println(i + ": " + s1 + ", " + s2);
		}

		int sum = sumI(5);
		sum = sumR(5);
		System.out.println(sum);
	}
}

