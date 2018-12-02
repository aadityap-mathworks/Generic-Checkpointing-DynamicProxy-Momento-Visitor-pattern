package genericCheckpointing.driver;

import java.io.File;
import java.util.Random;
import java.util.Vector;

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
//				else if(mode.equals("serdeser")) 
//				{
//					if (!checkPointFile.exists()) 
//					{
//						checkPointFile.createNewFile();
//					}
//					else if(checkPointFile.length() != 0 )
//					{
//						System.err.println("Invalid file for serdeser, file is not empty");
//						System.exit(1);
//					}
//					
//				}
				
				
				ProxyCreator pc = new ProxyCreator();
				StoreRestoreHandler storeRestorehandler = new StoreRestoreHandler(fileName);
				
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

							int myInt=random.nextInt(100)+i+1;
							long myLong=random.nextLong();
							StringBuilder sb = new StringBuilder();
							String alphabets = "abcdefghijklmnopqrstuvwxyz";
							for (int c = 0; c < 7 ; c++) {
							       int randIndex=random.nextInt(alphabets.length()); 
							       sb.append(alphabets.charAt(randIndex));            
							 }
							String myString=sb.toString();
							boolean myBool= random.nextBoolean();
							int myOtherInt=random.nextInt(1000)+i+10;
							myFirst = new MyAllTypesFirst(myInt,myLong,myString,myBool,myOtherInt);
							
							
							
							double myDouble= random.nextDouble()*(i+1)*10;
							float myFloat=random.nextFloat()*(i+1)*10;
							short myShort=(short) random.nextInt(1 << 16);
							double myOtherDouble=myDouble*Math.random()*10;
							char myChar=(char) (random.nextInt(26) + 'a');
						    mySecond = new MyAllTypesSecond(myDouble,myFloat,myShort,myOtherDouble,myChar);

						    vector_old.add(myFirst);
						    vector_old.add(mySecond);
						    
						    ((StoreI) cpointRef).writeObj(myFirst, 13,  "XML");
						    ((StoreI) cpointRef).writeObj(mySecond, 17, "XML");

						}
						
						SerializableObject myRecordRet;

						storeRestorehandler.openFile();
						// create a data structure to store the returned ojects
						for (int j=0; j<2*NUM_OF_OBJECTS; j++) {

						    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
						    vector_new.add(myRecordRet);
						}

						storeRestorehandler.closeFile();
						
						int noMatch=0;
						for(int a =0; a<vector_new.size();a++)
						{
							if(!vector_new.get(a).equals(vector_old.get(a)) 
									|| vector_new.get(a).hashCode()!=vector_old.get(a).hashCode())
							{
								noMatch++;
							}
						}
						
						System.out.println(noMatch+" mismatched objects");
						
						break;
						
						
					case "deser":
						
						SerializableObject myRecordRet2 = null;
						storeRestorehandler.openFile();
						for (int j=0; j<2*NUM_OF_OBJECTS; j++) {

						    myRecordRet2 = ((RestoreI) cpointRef).readObj("XML");
						    System.out.println(myRecordRet2.toString());
						}
						
						storeRestorehandler.closeFile();
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

