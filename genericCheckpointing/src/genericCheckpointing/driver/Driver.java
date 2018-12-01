package genericCheckpointing.driver;

import java.io.File;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

/**
 * @author Aaditya Sakharam Patil
 *
 */
public class Driver {
	/**
	 * Main method
	 * @param Commandline arguments
	 * @return none
	 */
	public static void main(String[] args) {
		try {
			
			/**
			 * As the build.xml specifies the arguments as argX, in case the
			 * argument value is not given java takes the default value specified in
			 * build.xml. To avoid that, below condition is used
			 */
			if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")) 
			{
				System.err.println("Error: Incorrect number of arguments. Program accepts 3 argumnets.");
				System.exit(1);
			}
			
			String mode = args[0];
			if (!mode.equals("serdeser") && !mode.equals("deser")) 
			{
				System.err.println("Error: Incorrect mode. Program accepts serdeser or deser mode");
				System.exit(1);
			}
			
			int NUM_OF_OBJECTS = Integer.parseInt(args[1]);
			
			String fileName = args[2];
			File checkPointFile = new File(fileName);
			
				if (mode.equals("deser")) 
				{	
					if (!checkPointFile.exists() || checkPointFile.length() == 0 ) 
					{
						System.err.println("Invalid file for deser, file does not exist or is empty");
						System.exit(1);
					}
				} 
				else if(mode.equals("serdeser")) 
				{
					if (!checkPointFile.exists()) 
					{
						checkPointFile.createNewFile();
					}
					else if(checkPointFile.length() != 0 )
					{
						System.err.println("Invalid file for serdeser, file is not empty");
						System.exit(1);
					}
					
				}
				
			
				

			// FIXME: read the value of checkpointFile from the command line
			
			ProxyCreator pc = new ProxyCreator();
			
			// create an instance of StoreRestoreHandler (which implements
			// the InvocationHandler
			
			// create a proxy
			StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
										 new Class[] {
										     StoreI.class, RestoreI.class
										 }, 
										 new StoreRestoreHandler()
										 );
				
			// FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file

			MyAllTypesFirst myFirst;
			MyAllTypesSecond  mySecond;

		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		finally {
			
		}
	
	}

}

