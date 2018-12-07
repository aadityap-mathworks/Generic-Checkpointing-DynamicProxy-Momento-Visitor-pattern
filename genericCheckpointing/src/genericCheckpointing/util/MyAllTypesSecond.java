package genericCheckpointing.util;
/**
 * @author Aaditya Sakharam Patil
 *
 */
import genericCheckpointing.visitor.VisitorI;

public class MyAllTypesSecond extends SerializableObject {

	private double myDouble=0;
	private float myFloat=0;
	private short myShort=0;
	private double myOtherDouble=0;
	private char myChar=0;
	

	/**
	 * Constructor to set default values
	 */
	public MyAllTypesSecond(double myDoubleIn, float myFloatIn, short myShortIn, double myOtherDoubleIn, char myCharIn) 
	{
					
		this.myDouble=myDoubleIn;
		this.myFloat = myFloatIn;
		this.myShort= myShortIn;
		this.myOtherDouble= myOtherDoubleIn;
		this.myChar= myCharIn;
	}


	/**
	 * empty constructor
	 */
	public MyAllTypesSecond()
	{
		
	}
	
	

	/**
	 * getter and setter methods for data members
	 */
	public double getMyDouble() {
		return myDouble;
	}

	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	public float getMyFloat() {
		return myFloat;
	}

	public void setMyFloat(float myFloat) {
		this.myFloat = myFloat;
	}

	public short getMyShort() {
		return myShort;
	}

	public void setMyShort(short myShort) {
		this.myShort = myShort;
	}

	public double getMyOtherDouble() {
		return myOtherDouble;
	}

	public void setMyOtherDouble(double myOtherDouble) {
		this.myOtherDouble = myOtherDouble;
	}

	public char getMyChar() {
		return myChar;
	}

	public void setMyChar(char myChar) {
		this.myChar = myChar;
	}

	
	public void accept(VisitorI visitor)
	{
		visitor.visit(this);
	}
	
	

	/**
	 * to calculate hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + myChar;
		long temp;
		temp = Double.doubleToLongBits(myDouble);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(myFloat);
		temp = Double.doubleToLongBits(myOtherDouble);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + myShort;
		return result;
	}


	/**
	 * equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAllTypesSecond other = (MyAllTypesSecond) obj;
		if (myChar != other.myChar)
			return false;
		if (Double.doubleToLongBits(myDouble) != Double.doubleToLongBits(other.myDouble))
			return false;
		if (Float.floatToIntBits(myFloat) != Float.floatToIntBits(other.myFloat))
			return false;
		if (Double.doubleToLongBits(myOtherDouble) != Double.doubleToLongBits(other.myOtherDouble))
			return false;
		if (myShort != other.myShort)
			return false;
		return true;
	}

	

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "MyAllTypesSecond [myDouble=" + myDouble + ", myFloat=" + myFloat + ", myShort=" + myShort
				+ ", myOtherDouble=" + myOtherDouble + ", myChar=" + myChar + "]";
	}


	
	
}
