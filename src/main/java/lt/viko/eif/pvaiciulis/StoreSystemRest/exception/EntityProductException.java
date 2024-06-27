package lt.viko.eif.pvaiciulis.StoreSystemRest.exception;

/**
 * An exception class that throws if something went wrong with entity product related operations from its controller class.
 */
public class EntityProductException extends RuntimeException {
    public EntityProductException(int id) {
        super("Something went wrong with entity product " + id);
    }
}
