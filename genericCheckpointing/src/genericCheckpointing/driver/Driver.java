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
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.visitor.PalindromVisitorImpl;
import genericCheckpointing.visitor.PrimeVisitorImpl;
import genericCheckpointing.visitor.VisitorI;
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
			
			//validate mode
			String mode = args[0];
			if (!mode.equals("serdeser") && !mode.equals("deser")) 
			{
				System.err.println("Error: Incorrect mode. Program accepts serdeser or deser mode");
				System.exit(1);
			}
			
			//validate number of objects
			int NUM_OF_OBJECTS = Integer.parseInt(args[1]);
			if(NUM_OF_OBJECTS==0)
			{
				System.err.println("Invalid number of objets");
				System.exit(1);
			}
			
			//validate checkpoint file
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
				
				//creating instance of proxy and handler
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
				
				//vectors to store objects
				Vector<SerializableObject> vector_old = new Vector<SerializableObject>();
				Vector<SerializableObject> vector_new = new Vector<SerializableObject>();
				Vector<SerializableObject> vector_deser = new Vector<SerializableObject>();
				
				//instance of result class
				Results res = new Results(); 
				
				//creating instance of visitors
				VisitorI prime = new PrimeVisitorImpl(res);
				VisitorI palindrome = new PalindromVisitorImpl(res);
				
				
				
				switch(mode)
				{
					case "serdeser":
						
						Random random = new Random();
						for (int i=0; i<NUM_OF_OBJECTS; i++) {

							//creating object of class MyAllTypesFirst
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
							
							//creating object of class MyAllTypesSecond
							double myDouble= random.nextDouble()*(i+1)*10;
							float myFloat=random.nextFloat()*(i+1)*10;
							short myShort=(short) random.nextInt(1 << 16);
							double myOtherDouble=myDouble*Math.random()*10;
							char myChar=(char) (random.nextInt(26) + 'a');
						    mySecond = new MyAllTypesSecond(myDouble,myFloat,myShort,myOtherDouble,myChar);

						    //storing objects
						    vector_old.add(myFirst);
						    vector_old.add(mySecond);
						    
						    //calling writeObj method
						    ((StoreI) cpointRef).writeObj(myFirst, 13,  "XML");
						    ((StoreI) cpointRef).writeObj(mySecond, 17, "XML");

						}
						
						SerializableObject myRecordRet;

						storeRestorehandler.openFile();
						
						//deserializing objects
						for (int j=0; j<2*NUM_OF_OBJECTS; j++) {

						    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
						    vector_new.add(myRecordRet);
						}

						storeRestorehandler.closeFile();
						
						//finding mismatches
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
					
						//visitors to find prime numbers and palindromes
						MyAllTypesFirst sfirst= new MyAllTypesFirst();
						MyAllTypesSecond ssecond = new MyAllTypesSecond();
						for(int a =0; a<vector_old.size();a++)
						{
							if(vector_old.get(a) instanceof MyAllTypesFirst)
							{
								sfirst = (MyAllTypesFirst) vector_old.get(a);
								sfirst.accept(prime);
								sfirst.accept(palindrome);
							}
							else if(vector_old.get(a) instanceof MyAllTypesSecond)
							{
								ssecond.accept(prime);
								ssecond.accept(palindrome);
							}
							
						}
						
						//printing number of prime numbers and all the palindromes
						System.out.println("number of unique primes: "+res.prime.size());
						for(int i=0; i<res.pallindrome.size(); i++)
						{
							System.out.println("palindrome "+(i+1)+": "+res.pallindrome.get(i));
						}
						if(res.pallindrome.size()==0)
						{
							System.out.println("No palindrome found");
						}
						
						break;
						
						
					case "deser":
						
						SerializableObject myRecordRet2 = null;
						storeRestorehandler.openFile();
						
						//deserializing objects
						for (int j=0; j<NUM_OF_OBJECTS; j++) {

						    myRecordRet2 = ((RestoreI) cpointRef).readObj("XML");
						    vector_deser.add(myRecordRet2);
						    System.out.println(myRecordRet2.toString());
						}
						
						storeRestorehandler.closeFile();
						
						//visitors to find prime numbers and palindromes
						MyAllTypesFirst dfirst= new MyAllTypesFirst();
						MyAllTypesSecond dsecond = new MyAllTypesSecond();
						for(int a =0; a<vector_deser.size();a++)
						{
							if(vector_deser.get(a) instanceof MyAllTypesFirst)
							{
								dfirst = (MyAllTypesFirst) vector_deser.get(a);
								dfirst.accept(prime);
								dfirst.accept(palindrome);
							}
							else if(vector_deser.get(a) instanceof MyAllTypesSecond)
							{
								dsecond.accept(prime);
								dsecond.accept(palindrome);
							}
							
						}
						
						//printing number of prime numbers and all the palindromes
						System.out.println("number of unique primes: "+res.prime.size());
						for(int i=0; i<res.pallindrome.size(); i++)
						{
							System.out.println("palindrome "+(i+1)+": "+res.pallindrome.get(i));
						}
						
						if(res.pallindrome.size()==0)
						{
							System.out.println("No palindrome found");
						}
						break;
				}
				

			        
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		finally {
			
		}
	
	}

}

