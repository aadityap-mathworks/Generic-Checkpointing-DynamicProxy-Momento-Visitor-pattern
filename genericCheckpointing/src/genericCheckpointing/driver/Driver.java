package genericCheckpointing.driver;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Vector;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
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
				
				FileProcessor fp = new FileProcessor(fileName);
				
				ProxyCreator pc = new ProxyCreator();
				StoreRestoreHandler storeRestorehandler = new StoreRestoreHandler(fp);
				
				// create a proxy
				StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
											 new Class[] {
											     StoreI.class, RestoreI.class
											 }, 
											 storeRestorehandler
											 );
				
	
				MyAllTypesFirst myFirst;
				MyAllTypesSecond  mySecond;
				Vector<SerializableObject> vector_old = new Vector<SerializableObject>();
				Vector<SerializableObject> vector_new = new Vector<SerializableObject>();
				
				switch(mode)
				{
					case "serdeser":
						
						Random random = new Random();
						for (int i=0; i<NUM_OF_OBJECTS; i++) {

							int myInt=random.nextInt(100)+i;
							//System.out.println("myInt: "+myInt);
							
							long myLong=random.nextLong();
							//System.out.println("myLong: "+myLong);
							
							StringBuilder sb = new StringBuilder();
							String alphabets = "abcdefghijklmnopqrstuvwxyz";
							for (int c = 0; c < random.nextInt(10)+1 ; c++) {
							       int randIndex=random.nextInt(alphabets.length()); 
							       sb.append(alphabets.charAt(randIndex));            
							 }
							String myString=sb.toString();
							//System.out.println("myString: "+myString);
							
							boolean myBool= random.nextBoolean();
							//System.out.println("myBool: "+myBool);
							
							int myOtherInt=random.nextInt(1000)+i+10;
							//System.out.println("myOtherInt: "+myOtherInt);
							
							myFirst = new MyAllTypesFirst(myInt,myLong,myString,myBool,myOtherInt);
							
							
							
							double myDouble= Math.random()*(i+1)*10;
							//System.out.println("myDouble: "+myDouble);
							float myFloat=random.nextFloat()*(i+1)*10;
							//System.out.println("myFloat: "+myFloat);
							short myShort=(short) random.nextInt(1 << 16);
							//System.out.println("myShort: "+myShort);
							double myOtherDouble=myDouble*Math.random()*10;
							//System.out.println("myOtherDouble: "+myOtherDouble);
							char myChar=(char) (random.nextInt(26) + 'a');;
							//System.out.println("myChar: "+myChar);
						    mySecond = new MyAllTypesSecond(myDouble,myFloat,myShort,myOtherDouble,myChar);

						    vector_old.add(myFirst);
						    vector_old.add(mySecond);
						    
						    ((StoreI) cpointRef).writeObj(myFirst, 13,  "XML");
						    //((StoreI) cpointRef).writeObj(mySecond, 17, "XML");

						}

						SerializableObject myRecordRet;

						// create a data structure to store the returned ojects
						for (int j=0; j<2*NUM_OF_OBJECTS; j++) {

						    //myRecordRet = ((RestoreI) cpointRef).readObj("XML");
						    // FIXME: store myRecordRet in the vector (or arrayList)
						}

						// FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)

						// FIXME: compare and confirm that the serialized and deserialzed objects are equal. 
						// The comparison should use the equals and hashCode methods. Note that hashCode 
						// is used for key-value based data structures
						
						
						
						
						break;
						
						
					case "deser":
						
						break;
				}
				


			        // FIXME
			        // Create an instance of the PrimeVisitorImpl and use it to determine the number of unique integers in all the instances of MyAllTypesFirst and MyAllTypesSecond

			        
			        // FIXME
			        // Create an instance of the PalindromeVisitorImpl and use it to determine the number of unique integers in all the instances of MyAllTypesFirst and MyAllTypesSecond

		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		finally {
			
		}
	
	}

}

