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
		PrimeVisitorImpl other = (PrimeVisitorImpl) obj;
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
		return "PrimeVisitorImpl [pf=" + pf + ", res=" + res + "]";
	}

	
	
}
