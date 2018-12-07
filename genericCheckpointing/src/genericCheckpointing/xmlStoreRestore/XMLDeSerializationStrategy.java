package genericCheckpointing.xmlStoreRestore;
/**
 * @author Aaditya Sakharam Patil
 * reference to convert first char to uppercase: https://www.geeksforgeeks.org/
 */
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class XMLDeSerializationStrategy implements DeserStrategy{

	public XMLDeSerializationStrategy() {
		
	}

	/**
	 * method to deserialize objects
	 *
	 */
	@Override
	public SerializableObject deSerialize(FileProcessor fp) {
		String currentLine;
		String className = null;
		Class<?> cls = null;
		Class<?> param = null;
		String methodName= null;
		SerializableObject obj = null;
		try {
			
			while (((currentLine = fp.readInputLine()) != null))
			{
				if(currentLine.equalsIgnoreCase("</DPSerialization>"))
				{
					break;
				}
				
				if(currentLine.equalsIgnoreCase("<DPSerialization>")||currentLine.trim().equalsIgnoreCase("</complexType>"))
				{
					continue;
				}
				
				if(currentLine.contains("MyAllTypesFirst"))
				{
					className = "genericCheckpointing.util.MyAllTypesFirst";
					cls = Class.forName(className);
					obj = (SerializableObject) cls.newInstance();
				}
				else if(currentLine.contains("MyAllTypesSecond"))
				{
					className = "genericCheckpointing.util.MyAllTypesSecond";
					cls = Class.forName(className);
					obj = (SerializableObject) cls.newInstance();
				}
				else
				{
										
					String part[] = currentLine.split("[<>/= :]+");
					String type =part[5].replaceAll("^\"|\"$", "");
					String val = part[6];
					
					Object objVal=null;
					
					switch(type)
					{
					case "int":
						methodName= "set"+convert(part[7]).replace("T", "");
						param= int.class;
						objVal= Integer.parseInt(val);
						break;
						
					case "float":
						methodName= "set"+convert(part[7]).replace("T", "");
						objVal= Float.parseFloat(val);
						param= float.class;
						break;
						
					case "short":
						methodName= "set"+convert(part[7]).replace("T", "");
						objVal= Short.parseShort(val);
						param= short.class;
						break;
						
					case "long":
						methodName= "set"+convert(part[7]).replace("T", "");
						objVal= Long.parseLong(val);
						param= long.class;
						break;
						
					case "double":
						methodName= "set"+convert(part[7]).replace("T", "");
						objVal= Double.parseDouble(val);
						param= double.class;
						break;
						
					case "char":
						methodName= "set"+convert(part[7]).replace("T", "");
						objVal= val.charAt(0);
						param= char.class;
						break;
						
					case "string":
						methodName= "set"+convert(part[7]).replace("T", "");
						objVal = val;
						param= String.class;
						break;
						
					case "boolean":
						methodName= "set"+convert(part[7]).replace("T", "");
						objVal = Boolean.parseBoolean(val);
						param= boolean.class;
						break;
						
						
					}
					
					Method method = cls.getMethod(methodName, param);
					method.invoke(obj, objVal);
				}
				
				
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
			
		}
		finally {}
		return obj;
	}

	/**
	 * method to uppenrcase first character of string
	 *
	 */
	public String convert(String str) 
    { 
  
        // Create a char array of given String 
        char ch[] = str.toCharArray(); 
        for (int i = 0; i < str.length(); i++) 
        { 
            // If first character of a word is found 
            if (i == 0 && ch[i] != ' ' ||  
                ch[i] != ' ' && ch[i - 1] == ' ') 
            { 
                // If it is in lower-case 
                if (ch[i] >= 'a' && ch[i] <= 'z') 
                { 
                    // Convert into Upper-case 
                    ch[i] = (char)(ch[i] - 'a' + 'A'); 
                } 
            }                        
        } 
  
        // Convert the char array to equivalent String 
        String st = new String(ch); 
        return st; 
    } 
	

}
