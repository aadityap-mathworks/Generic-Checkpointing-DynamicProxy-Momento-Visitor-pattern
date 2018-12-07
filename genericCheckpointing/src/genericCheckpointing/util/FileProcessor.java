package genericCheckpointing.util;
/**
 * @author Aaditya Sakharam Patil
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {


	private BufferedReader inputReader = null;
	private String filename;
	
	/**
	 *constructor
	 *@param filename
	 */
	public FileProcessor(String filenameIn)
	{
		this.filename=filenameIn;
	}
	
	/**
	 *To process a file
	 *
	 */
	public void open()
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((inputReader == null) ? 0 : inputReader.hashCode());
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
		FileProcessor other = (FileProcessor) obj;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (inputReader == null) {
			if (other.inputReader != null)
				return false;
		} else if (!inputReader.equals(other.inputReader))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileProcessor [inputReader=" + inputReader + ", filename=" + filename + "]";
	}

	 

}
