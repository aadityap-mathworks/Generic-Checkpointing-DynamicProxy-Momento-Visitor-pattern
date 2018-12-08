
package genericCheckpointing.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Aaditya Sakharam Patil
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	
	public static ArrayList<String> finalList = new ArrayList<String>();
	String outputFile;
	public ArrayList<Integer> prime = new ArrayList<Integer>();
	public ArrayList<String> pallindrome = new ArrayList<String>();
	/**
	 * to get output file name
	 *@param filename
	 */
	public String getOutputFile() {
		return outputFile;
	}

	
	/**
	 * sets output file name
	 *@param filename
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	
	/**
	 * Constructor
	 * sets output file name
	 *@param filename
	 */
	public Results(String fileName)
	{
//		MyLogger.writeMessage("Constructor of Results called ", MyLogger.DebugLevel.CONSTRUCTOR);
		setOutputFile(fileName);
	}

	public Results() {
		
	}


	/**
	 * method to store results
	 * @param String 
	 */
	public void addToFinalResult(String res)
	{
		finalList.add(res);
//		MyLogger.writeMessage("Entry is made in Result with "+res+"", MyLogger.DebugLevel.ENTRY_IN_RESULT);
	}
	
	/**
	 * method to store results
	 * @param String 
	 */
	public void addToPrimeResult(int primeIn)
	{
		if(!prime.contains(primeIn))
		{			
			prime.add(primeIn);
		}
//		MyLogger.writeMessage("Entry is made in Result with "+res+"", MyLogger.DebugLevel.ENTRY_IN_RESULT);
	}
	
	public void addToPallindromeResult(String pallindromeIn)
	{
		if(!pallindrome.contains(pallindromeIn))
		{			
			pallindrome.add(pallindromeIn);
		}

	}
	/**
	 * method to write results to a file
	 *
	 */
	public void writeToFile() 
	{
		File out=null;
		FileWriter fw = null;
		
		try {
			 
			out= new File(outputFile);
			fw = new FileWriter(out);
			for(int i =0 ; i<finalList.size();i++)
			{	
				if(i!=(finalList.size()-1))
				{
					fw.write(finalList.get(i)+"\n");
				}
				else
				{
					fw.write(finalList.get(i));
				}
			}
		}
		catch (Exception e){
//			MyLogger.writeMessage("Exception occured in writeToFile of Results class \n"+e.toString(), MyLogger.DebugLevel.NONE);
			System.exit(1);
		}
		finally {
			try {
				
				fw.close();
			}
			catch(IOException ex)
			{
//				MyLogger.writeMessage("Exception occured while closing file writer "
//						+ "in writeToFile of Results class \n"+ex.toString(), MyLogger.DebugLevel.NONE);
				System.exit(1);
			}
		}
		
	}
	
	/**
	 * method to display results on StdOut
	 *
	 */
	public void displayStdOut(String s) 
	{
		try {
			
			//MyLogger.writeMessage(s+":", MyLogger.DebugLevel.NONE);
			for(int i =0 ; i<finalList.size();i++)
			{	
				//MyLogger.writeMessage(finalList.get(i), MyLogger.DebugLevel.NONE);
			}
		}
		catch(Exception e)
		{
			//MyLogger.writeMessage("Exception occured in displayStdOut of Results class \n"+e.toString(), MyLogger.DebugLevel.NONE);
			System.exit(1);
		}
		finally {}
	}
	
	/**
	 * method to clear results
	 *
	 */
	public void clearResult()
	{
		finalList.clear();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((outputFile == null) ? 0 : outputFile.hashCode());
		result = prime * result + ((pallindrome == null) ? 0 : pallindrome.hashCode());
		result = prime * result + ((this.prime == null) ? 0 : this.prime.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Results other = (Results) obj;
		if (outputFile == null) {
			if (other.outputFile != null)
				return false;
		} else if (!outputFile.equals(other.outputFile))
			return false;
		if (pallindrome == null) {
			if (other.pallindrome != null)
				return false;
		} else if (!pallindrome.equals(other.pallindrome))
			return false;
		if (prime == null) {
			if (other.prime != null)
				return false;
		} else if (!prime.equals(other.prime))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Results [outputFile=" + outputFile + ", prime=" + prime + ", pallindrome=" + pallindrome + "]";
	}


	
	
}
