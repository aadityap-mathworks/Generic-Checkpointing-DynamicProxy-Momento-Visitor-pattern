package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {


	private BufferedReader inputReader = null;
	
	/**
	 *To process a file
	 *create a inputReader
	 *@param filename
	 */
	public FileProcessor(String filename)
	{
		try {
			//MyLogger.writeMessage("Constructor of FileProcessor called ", MyLogger.DebugLevel.CONSTRUCTOR);
			File input = new File(filename);
			inputReader = new BufferedReader(new FileReader(input));
		}
		catch(IOException e){
			//MyLogger.writeMessage("Exception occured in Constructor of FileProcessor class \n"+e.toString(), MyLogger.DebugLevel.NONE);
			System.exit(1);
		}
		finally{ 
		}
	}
	
	/**
	 *Reads line from input file
	 */
	 public String readInputLine() 
	 {
			try{
				String currentline;
				while ((currentline = inputReader.readLine()) != null) 
				{	
					if(currentline.equals(""))
					{
						return null;
					}
					else
						return currentline;
				}
			} catch (IOException ex)
	        {
				//MyLogger.writeMessage("Exception occured while reading line"
				//		+ "in FileProcessor class \n"+ex.toString(), MyLogger.DebugLevel.NONE);
				System.exit(1);
	
	        }	
			finally {
				    
			}
			return null;
		}
	
	 /**
	 * to close buffered reader
	 */
	 public void close(){
	    	try{
		    inputReader.close();
	    	}catch(IOException e){
	    		//MyLogger.writeMessage("Exception occured while closing input reader in FileProcessor class \n"+e.toString(), MyLogger.DebugLevel.NONE);
				System.exit(1);
	    	}
	    	finally {}
	}


}
