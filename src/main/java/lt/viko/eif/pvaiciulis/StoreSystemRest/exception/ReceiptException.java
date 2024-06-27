package lt.viko.eif.pvaiciulis.StoreSystemRest.exception;

/**
 * An exception class that throws if something went wrong with Receipt related operations from its controller class.
 */
public class ReceiptException extends RuntimeException {
    public ReceiptException(int id) {
        super("Something went wrong with Receipt." + id);
    }

}
