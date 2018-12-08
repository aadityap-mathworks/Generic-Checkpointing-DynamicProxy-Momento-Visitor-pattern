package genericCheckpointing.xmlStoreRestore;
/**
 * @author Aaditya Sakharam Patil
 *
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler{

	Results res;
	FileProcessor fp;
	
	/**
	 * constructor
	 *
	 */
	public StoreRestoreHandler(String fileName) 
	{
		this.res = new Results(fileName);
		this.fp= new FileProcessor(fileName);
	}

	/**
	 *invoke method to read and write objects
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		//to write object 
		if(method.getName().equals("writeObj"))
		{
			if(((String) args[2]).equalsIgnoreCase("XML"))
			{
				serializeData((SerializableObject) args[0], new XMLSerializationStrategy(),res);
			}
				
		}
		
		//to write object 
		else if(method.getName().equals("readObj"))
		{
			if(((String) args[0]).equalsIgnoreCase("XML"))
			{
					return deSerialize(new XMLDeSerializationStrategy(),fp);
				
			}			
		}
		
		return null;
	}

	//to serialize
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy, Results res) {
        sStrategy.processInput(sObject, res);
	}
	

	//to deserialize
	private SerializableObject deSerialize(XMLDeSerializationStrategy dStrategy, FileProcessor fpIn) 
	{
		return dStrategy.deSerialize(fpIn);
		
	}
	

	/**
	 * to open file
	 */
	public void openFile()
	{
		fp.open();
	}
	
	/**
	 * to close file
	 */
	public void closeFile()
	{
		fp.close();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fp == null) ? 0 : fp.hashCode());
		result = prime * result + ((res == null) ? 0 : res.hashCode());
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
		StoreRestoreHandler other = (StoreRestoreHandler) obj;
		if (fp == null) {
			if (other.fp != null)
				return false;
		} else if (!fp.equals(other.fp))
			return false;
		if (res == null) {
			if (other.res != null)
				return false;
		} else if (!res.equals(other.res))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoreRestoreHandler [res=" + res + ", fp=" + fp + "]";
	}
	
	
	
}
