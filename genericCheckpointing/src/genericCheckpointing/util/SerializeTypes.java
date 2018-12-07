package genericCheckpointing.util;
/**
 * @author Aaditya Sakharam Patil
 *
 */
public class SerializeTypes {

	public SerializeTypes() {
		
	}

	/**
	 * method to construct a line to serialize in XML
	 * @param fieldname , its value, classname and type of data member
	 * @return String line
	 */
	public String serializeDataType(String fieldName, String value, String className, Class<?> type)
	{
		//checks if value is less than 10
		if((type.equals(int.class)&& Integer.parseInt(value)<10)
				|| (type.equals(double.class)&& Double.parseDouble(value)<10)
				|| (type.equals(long.class))&& Long.parseLong(value)<10)
		{
				return null;			
		}
		
		String line="";
		if(type.equals(String.class))
		{	
			line = "		<"+fieldName+" xsi:type=\"xsd:string\">"+value.toString()+"</"+fieldName+">";
			return line;
		}
		
		//constructing string to put in XML file
		if(className.contains("First"))
		{			
			line = "		<"+fieldName+" xsi:type=\"xsd:"+type+"\">"+value.toString()+"</"+fieldName+">";
		}
		else if(className.contains("Second")) 
		{
			line = "		<"+fieldName+"T xsi:type=\"xsd:"+type+"\">"+value.toString()+"</"+fieldName+"T>";
		}
		
		return line;
		
	}
	
	
}
