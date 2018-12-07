package genericCheckpointing.visitor;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.PrimeFinder;
import genericCheckpointing.util.Results;
public class PrimeVisitorImpl implements VisitorI{

	private PrimeFinder pf;
	private Results res;
	public PrimeVisitorImpl(Results resIn)
	{
		this.res = resIn;
		this.pf= new PrimeFinder();
		
	}
	
	@Override
	public void visit(MyAllTypesFirst matf) {
		
		boolean prime = pf.isPrime(matf.getMyInt());
		if(prime==true)
		{
			res.addToPrimeResult(matf.getMyInt());
		}
		boolean prime2 = pf.isPrime(matf.getMyOtherInt());
		if(prime2==true)
		{
			res.addToPrimeResult(matf.getMyOtherInt());
		}
		
	}



	@Override
	public void visit(MyAllTypesSecond mats) {
		
		
	}

}
