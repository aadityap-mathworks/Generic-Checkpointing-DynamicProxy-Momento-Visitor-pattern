package genericCheckpointing.visitor;
/**
 * @author Aaditya Sakharam Patil
 *
 */
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.PalindromeFinder;
import genericCheckpointing.util.Results;

public class PalindromVisitorImpl implements VisitorI{

	private Results res;
	private PalindromeFinder pf;
	
	/**
	 * constructor
	 */
	public PalindromVisitorImpl(Results resIn)
	{
		this.res = resIn;
		this.pf = new PalindromeFinder();
	}

	/**
	 * visit method to find prime numbers in MyAllTypesSecond
	 * @param object of MyAllTypesFirst
	 */
	@Override
	public void visit(MyAllTypesFirst matf) {
		boolean ans =pf.isPalindrome(matf.getMyString());
		if(ans==true)
		{
			res.addToPallindromeResult(matf.getMyString());
		}
	}

	@Override
	public void visit(MyAllTypesSecond mats) {
		
		
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pf == null) ? 0 : pf.hashCode());
		result = prime * result + ((res == null) ? 0 : res.hashCode());
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
		PalindromVisitorImpl other = (PalindromVisitorImpl) obj;
		if (pf == null) {
			if (other.pf != null)
				return false;
		} else if (!pf.equals(other.pf))
			return false;
		if (res == null) {
			if (other.res != null)
				return false;
		} else if (!res.equals(other.res))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PalindromVisitorImpl [res=" + res + ", pf=" + pf + "]";
	}

	
	
}
