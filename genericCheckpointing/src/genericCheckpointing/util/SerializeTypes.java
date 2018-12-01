package genericCheckpointing.util;

public class SerializeTypes {

	public SerializeTypes() {
		// TODO Auto-generated constructor stub
	}

	
	public String serializeDataType(String fieldName, String value, String className)
	{
		String type;
		
		if(fieldName.contains("Other"))
		{
			type = fieldName.replace("myOther","");
			type= type.toLowerCase();
		}
		else
		{
			type = fieldName.replace("my","");
			type= type.toLowerCase();
		}
		
		String line="";
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
