package genericCheckpointing.util;

public class SerializeTypes {

	public SerializeTypes() {
		// TODO Auto-generated constructor stub
	}

	
	public String serializeDataType(String fieldName, String value, String className, Class<?> type)
	{
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
