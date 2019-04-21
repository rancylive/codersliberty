package codersliberty.rough;

import java.util.HashMap;
import java.util.Map;

/**
 * given array of N numbers, find sum of K numbers such that the sum is equal to
 * S. K numbers can be randomly scattered.
 * 
 * @author rchoudhury
 *
 */
public class SumOfN {
	public static void main(String[] args) {
		int[] arr = { 2, 5, 3, 8, 7, 9, 3, 5 };
		int k = 2;
		int s = 10;
		int[] out = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				int sum = arr[i] + arr[j];
				for (int l = j; l < k; l++) {
					sum = sum + arr[l];
					if (sum == s) {
						System.out.println(sum);
					}
				}
			}
		}
	}

	public static void sumOfTwo(String[] args) {
		int[] arr = { 2, 5, 3, 8, 7, 9, 3, 5 };
		int k = 2;
		int s = 10;
		int[] out = new int[arr.length];
		//set can also be used
		Map<Integer, Integer> map=new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], arr[i]);
		}
		for (int i = 0; i < arr.length; i++) {
			int r=s-arr[i];
			if(map.containsKey(r)) {
				System.out.println(arr[i]+ r);
			}
		}
	}
	
}
