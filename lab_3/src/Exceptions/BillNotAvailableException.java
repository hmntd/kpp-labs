package Exceptions;

public class BillNotAvailableException extends ATMException {
    public BillNotAvailableException(String message) {
        super(message);
    }
}
