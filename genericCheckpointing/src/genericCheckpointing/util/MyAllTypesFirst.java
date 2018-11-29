package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject {

	private int myInt=0;
	private long myLong=0;
	private String myString="";
	private boolean myBool=false;
	private int myOtherInt=0;
	
	public MyAllTypesFirst(int myIntIn , long myLongIn, String myStringIn, boolean myBoolIn, int myOtherIntIn ) 
	{
		this.myInt=myIntIn;
		this.myLong = myLongIn;
		this.myString=myStringIn;
		this.myBool= myBoolIn;
		this.myOtherInt= myOtherIntIn;
	
	}

	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	public long getMyLong() {
		return myLong;
	}

	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	public boolean isMyBool() {
		return myBool;
	}

	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
	}

	public int getMyOtherInt() {
		return myOtherInt;
	}

	public void setMyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (myBool ? 1231 : 1237);
		result = prime * result + myInt;
		result = prime * result + (int) (myLong ^ (myLong >>> 32));
		result = prime * result + myOtherInt;
		result = prime * result + ((myString == null) ? 0 : myString.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAllTypesFirst other = (MyAllTypesFirst) obj;
		if (myBool != other.myBool)
			return false;
		if (myInt != other.myInt)
			return false;
		if (myLong != other.myLong)
			return false;
		if (myOtherInt != other.myOtherInt)
			return false;
		if (myString == null) {
			if (other.myString != null)
				return false;
		} else if (!myString.equals(other.myString))
			return false;
		return true;
	}
	
	
	

}