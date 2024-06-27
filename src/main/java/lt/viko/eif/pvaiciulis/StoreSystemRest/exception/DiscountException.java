package lt.viko.eif.pvaiciulis.StoreSystemRest.exception;

/**
 * An exception class that throws if something went wrong with discount related operations from its controller class.
 */
public class DiscountException extends RuntimeException {
    public DiscountException(int id) {
        super("Something went wrong with discount" + id);
    }
}
