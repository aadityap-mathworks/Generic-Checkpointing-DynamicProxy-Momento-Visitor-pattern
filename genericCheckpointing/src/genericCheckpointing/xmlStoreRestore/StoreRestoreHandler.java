package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;

public class StoreRestoreHandler implements InvocationHandler{

	FileProcessor fileProcessor;
	public StoreRestoreHandler(FileProcessor fileProcessorIn) 
	{
		 this.fileProcessor= fileProcessorIn;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
