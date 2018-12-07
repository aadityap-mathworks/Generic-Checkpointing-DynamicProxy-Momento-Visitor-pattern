package genericCheckpointing.visitor;
/**
 * @author Aaditya Sakharam Patil
 *
 */
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.PrimeFinder;
import genericCheckpointing.util.Results;
public class PrimeVisitorImpl implements VisitorI{

	private PrimeFinder pf;
	private Results res;
	/**
	 * constructor
	 */
	public PrimeVisitorImpl(Results resIn)
	{
		this.res = resIn;
		this.pf= new PrimeFinder();
		
	}
	
	/**
	 * visit method to find prime numbers in MyAllTypesFirst
	 */
	@Override
	public void visit(MyAllTypesFirst matf) {
		
		//check whether number is prime
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
