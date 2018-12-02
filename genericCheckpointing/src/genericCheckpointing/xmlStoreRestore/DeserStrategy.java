package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public interface DeserStrategy {

	SerializableObject deSerialize(FileProcessor fp);
}
