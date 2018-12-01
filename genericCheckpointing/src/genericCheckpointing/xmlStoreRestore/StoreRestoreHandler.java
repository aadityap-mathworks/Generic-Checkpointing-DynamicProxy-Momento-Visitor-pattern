package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler{

	FileProcessor fileProcessor;
	public StoreRestoreHandler(FileProcessor fileProcessorIn) 
	{
		 this.fileProcessor= fileProcessorIn;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if(method.getName().equals("writeObj"))
		{
			if(((String) args[2]).equalsIgnoreCase("XML"))
			{
				System.out.println("inside invoke");
				serializeData((SerializableObject) args[0], new XMLSerializationStrategy());
			}
				
		}
		
		
		return null;
	}

	
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
        sStrategy.processInput(sObject);
}
	
}
