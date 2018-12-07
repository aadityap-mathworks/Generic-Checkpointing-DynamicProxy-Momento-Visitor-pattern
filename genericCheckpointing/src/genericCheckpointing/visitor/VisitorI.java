package genericCheckpointing.visitor;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public interface VisitorI {
	public void visit(MyAllTypesFirst matf);
	public void visit(MyAllTypesSecond mats);
}
