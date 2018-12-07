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

}
