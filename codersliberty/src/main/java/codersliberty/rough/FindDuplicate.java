package codersliberty.rough;

//Java code to find 
//duplicates in O(n) time 

class FindDuplicate 
{ 
//Function to print duplicates 
	void printRepeating(int arr[], int size) 
	{ 
		int i; 
		System.out.println("The repeating elements are : "); 
	
		for (i = 0; i < size; i++) 
		{ 
			System.out.println("Processing "+arr[Math.abs(arr[i])]+" Arr: "+arr[i]+" i:"+i);
			if (arr[Math.abs(arr[i])] >= 0) 
				arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])]; 
			else
				System.out.print(Math.abs(arr[i]) + " "); 
		}		 
	} 

	// Driver program 
	public static void main(String[] args) 
	{ 
		FindDuplicate duplicate = new FindDuplicate(); 
		//int arr[] = {1, 2, 3, 1, 3, 6, 6};
		int arr[] = {34,56,11,2,34,2,56};
		int arr_size = arr.length; 

		duplicate.printRepeating(arr, arr_size); 
		System.out.println(arr[Math.abs(3)]);
	} 
} 

//This code has been contributed by Mayank Jaiswal 

