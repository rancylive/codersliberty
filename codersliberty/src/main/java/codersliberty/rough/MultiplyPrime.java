package codersliberty.rough;

import java.util.ArrayList;
import java.util.List;

public class MultiplyPrime {

	public static void main(String[] args) {
		List<Integer> primes = new ArrayList<Integer>(1);
		int n = 10;
		int counter = 1;
		while (primes.size() < n) {
			boolean flag = false;
			for (int i = 2; i < counter; i++) {
				if (counter % i == 0) {
					flag = true;
				}
			}
			if (flag == false) {
				primes.add(counter);
			}
			counter++;
		}
		System.out.println("Primes are: "+primes);
		for (Integer num : primes) {
			for (int i = 1; i <= 10; i++) {
				System.out.println(num + " x " + i + " = " + num * i);
			}
			System.out.println("==============");
		}
	}
}
