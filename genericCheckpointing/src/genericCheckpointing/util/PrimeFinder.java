package genericCheckpointing.util;
/**
 * @author Aaditya Sakharam Patil
 * Referred prime number algorithm from https://www.geeksforgeeks.org/
 */
public class PrimeFinder {

	public PrimeFinder() {
		
	}
	
	/**
	 * to check whether the number is prime
	 * @param number
	 */
	public boolean isPrime(int n) 
    { 
		// Corner case 
        if (n <= 1) 
            return false; 
       
        // Check from 2 to n-1 
        for (int i = 2; i < n; i++) 
            if (n % i == 0) 
                return false; 
       
        return true; 
    } 

}
