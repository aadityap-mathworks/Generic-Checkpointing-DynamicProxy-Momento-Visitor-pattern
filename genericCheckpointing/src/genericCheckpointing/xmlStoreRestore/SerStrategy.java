package genericCheckpointing.xmlStoreRestore;
/**
 * @author Aaditya Sakharam Patil
 *
 */
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;

public interface SerStrategy {
	void processInput(SerializableObject sObject, Results res);
}
