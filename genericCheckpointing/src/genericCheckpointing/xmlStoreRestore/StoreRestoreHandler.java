package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler{

	Results res;
	FileProcessor fp;
	
	public StoreRestoreHandler(String fileName) 
	{
		this.res = new Results(fileName);
		this.fp= new FileProcessor(fileName);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if(method.getName().equals("writeObj"))
		{
			if(((String) args[2]).equalsIgnoreCase("XML"))
			{
				serializeData((SerializableObject) args[0], new XMLSerializationStrategy(),res);
			}
				
		}
		
		else if(method.getName().equals("readObj"))
		{
			if(((String) args[0]).equalsIgnoreCase("XML"))
			{
					return deSerialize(new XMLDeSerializationStrategy(),fp);
				
			}			
		}
		
		return null;
	}

	
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy, Results res) {
        sStrategy.processInput(sObject, res);
	}
	
	private SerializableObject deSerialize(XMLDeSerializationStrategy dStrategy, FileProcessor fpIn) 
	{
		return dStrategy.deSerialize(fpIn);
		
	}
	
	public void openFile()
	{
		fp.open();
	}
	
	public void closeFile()
	{
		fp.close();
	}
	
}
