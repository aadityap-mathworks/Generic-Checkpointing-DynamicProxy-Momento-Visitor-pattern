package genericCheckpointing.visitor;

import java.util.Vector;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.PalindromeFinder;
import genericCheckpointing.util.Results;

public class PalindromVisitorImpl implements VisitorI{

	private Results res;
	private PalindromeFinder pf;
	public PalindromVisitorImpl(Results resIn)
	{
		this.res = resIn;
		this.pf = new PalindromeFinder();
	}

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
