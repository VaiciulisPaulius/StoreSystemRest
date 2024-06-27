package lt.viko.eif.pvaiciulis.StoreSystemRest.exception;

/**
 * An exception class that throws if something went wrong with Person related operations from its controller class.
 */
public class PersonException extends RuntimeException {
    public PersonException(int id) {
        super("Something went wrong with person" + id);
    }
}
