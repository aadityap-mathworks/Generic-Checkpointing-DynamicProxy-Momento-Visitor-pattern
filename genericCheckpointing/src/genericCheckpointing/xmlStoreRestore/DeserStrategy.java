package genericCheckpointing.xmlStoreRestore;
/**
 * @author Aaditya Sakharam Patil
 *
 */
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public interface DeserStrategy {

	SerializableObject deSerialize(FileProcessor fp);
}
