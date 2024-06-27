package lt.viko.eif.pvaiciulis.StoreSystemRest.exception;

/**
 * An exception class that throws if something went wrong with Quantifiable product related operations from its controller class.
 */
public class QuantifiableProductException extends RuntimeException {

	public QuantifiableProductException(int id) {
		super("Something went wrong with quantifiable product " + id);
	}
}
