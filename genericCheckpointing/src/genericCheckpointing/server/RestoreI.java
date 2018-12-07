package genericCheckpointing.server;
/**
 * @author Aaditya Sakharam Patil
 *
 */
import genericCheckpointing.util.SerializableObject;

public interface RestoreI extends StoreRestoreI {
    SerializableObject readObj(String wireFormat);
}
