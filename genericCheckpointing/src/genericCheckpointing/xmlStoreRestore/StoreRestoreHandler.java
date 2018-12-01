package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler{

	FileProcessor fileProcessor;
	Results res;
	public StoreRestoreHandler(FileProcessor fileProcessorIn, Results resIn) 
	{
		 this.fileProcessor= fileProcessorIn;
		 this.res=resIn;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if(method.getName().equals("writeObj"))
		{
			fileProcessor.open();
			
			if(((String) args[2]).equalsIgnoreCase("XML"))
			{
				//System.out.println("inside invoke");
				serializeData((SerializableObject) args[0], new XMLSerializationStrategy(),res);
			}
				
			fileProcessor.close();
		}
		
		
		return null;
	}

	
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy, Results res) {
        sStrategy.processInput(sObject, res);
}
	
}
