package lt.viko.eif.pvaiciulis.StoreSystemRest.exception;

/**
 * An exception class that throws if something went wrong with discount card related operations from its controller class.
 */
public class DiscountCardException extends RuntimeException {
    public DiscountCardException(int id) {
        super("Something went wrong with discount card " + id);
    }
}
