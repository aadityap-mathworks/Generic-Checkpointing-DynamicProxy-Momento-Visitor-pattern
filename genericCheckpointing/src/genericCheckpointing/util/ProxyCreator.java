package genericCheckpointing.util;
/**
 * @author Aaditya Sakharam Patil
 *
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import genericCheckpointing.server.StoreRestoreI;

public class ProxyCreator {

	/**
	 * constructor
	 *
	 */
	public ProxyCreator() {
		
	}

	/**
	 * method to create proxy
	 *
	 */
	public StoreRestoreI createProxy(Class<?>[] interfaceArray, InvocationHandler handler){

		try {
			
			StoreRestoreI  serDeserObj =
					(StoreRestoreI)
					Proxy.newProxyInstance(
							getClass().getClassLoader(),
							interfaceArray,
							handler
							);
			return serDeserObj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
